package dev.pivozavr.jnotunit.core;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(GlobalTestMethodsTests.class)
public class GlobalTestMethodsTests implements GlobalBeforeAndAfterCallBack {

    static int globalBeforeCallCount = 0;
    static int globalAfterCallCount = 0;
    static int classBeforeCallCount = 0;
    static int classAfterCallCount = 0;
    static int testCallCount = 0;


    @Override
    public void beforeAllTests() {
        assertEquals(0, globalBeforeCallCount);
        assertEquals(0, globalAfterCallCount);

        assertEquals(0, classBeforeCallCount);
        assertEquals(0, classAfterCallCount);

        assertEquals(0, testCallCount);

        globalBeforeCallCount++;
    }

    @Override
    public void afterAllTests() {
        globalAfterCallCount++;

        assertEquals(1, globalBeforeCallCount);
        assertEquals(1, globalAfterCallCount);

        assertEquals(20, classBeforeCallCount);
        assertEquals(20, classAfterCallCount);

        assertEquals(40, testCallCount);
    }

    @BeforeAll
    public static void beforeClass() {
        assertEquals(1, globalBeforeCallCount);
        assertEquals(0, globalAfterCallCount);
        assertEquals(classAfterCallCount * 2, testCallCount);

        classBeforeCallCount++;
    }

    @AfterAll
    public static void afterClass() {
        assertEquals(1, globalBeforeCallCount);
        assertEquals(0, globalAfterCallCount);
        assertEquals(classBeforeCallCount * 2, testCallCount);

        classAfterCallCount++;
    }

    public void mainAssert() {
        Assertions.assertEquals(1, globalBeforeCallCount);
        Assertions.assertEquals(0, globalAfterCallCount);

        testCallCount++;
    }
}