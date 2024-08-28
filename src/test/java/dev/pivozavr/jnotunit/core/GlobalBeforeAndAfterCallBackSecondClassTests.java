package dev.pivozavr.jnotunit.core;

import org.junit.jupiter.api.Test;

public class GlobalBeforeAndAfterCallBackSecondClassTests extends GlobalTestMethodsTests {

    @Test
    public void countCallBeforeAndAfterTest_1_1() {
        mainAssert();
    }

    @Test
    public void countCallBeforeAndAfterTest_1_2() {
        mainAssert();
    }
}