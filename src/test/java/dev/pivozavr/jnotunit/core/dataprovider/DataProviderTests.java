package dev.pivozavr.jnotunit.core.dataprovider;

import dev.pivozavr.jnotunit.core.DataProviderBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataProviderTests {

    public static int expArg1 = 1;
    public static int expArg2 = 99;
    public static int testsCount = 0;
    public static int testsCountExp = 0;

    public static Stream<Arguments> dataProvider() {
        int arg1 = 0;
        int arg2 = 100;
        DataProviderBuilder list = new DataProviderBuilder()
                .add(++arg1, --arg2);
        while (arg1 < 100) {
            list.add(++arg1, --arg2);
        }
        testsCountExp = (int) list.getStream().count();
        return list.getStream();
    }

    @MethodSource("dataProvider")
    @ParameterizedTest()
    public void dataProviderTest(int a1, int a2) {
        testsCount++;
        assertEquals(expArg1, a1);
        assertEquals(expArg2, a2);
        expArg1++;
        expArg2--;
    }

    @AfterAll
    public static void postCondition() {
        assertEquals(testsCountExp, testsCount);
    }
}
