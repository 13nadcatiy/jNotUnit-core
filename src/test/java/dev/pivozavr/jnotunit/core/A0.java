package dev.pivozavr.jnotunit.core;

import org.junit.jupiter.api.RepeatedTest;

class A0 extends GlobalTestMethodsTests {

    @RepeatedTest(2)
    public void countCallBeforeAndAfterTest() {
        mainAssert();
    }
}