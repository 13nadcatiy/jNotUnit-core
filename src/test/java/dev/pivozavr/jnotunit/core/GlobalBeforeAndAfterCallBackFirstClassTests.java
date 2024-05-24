package dev.pivozavr.jnotunit.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class GlobalBeforeAndAfterCallBackFirstClassTests extends GlobalTestMethodsTests {

    @Test
    public void countCallBeforeAndAfterFirstTests() {
        Assertions.assertEquals(1, globalBeforeCallCount);
        Assertions.assertEquals(0, globalAfterCallCount);
    }
}