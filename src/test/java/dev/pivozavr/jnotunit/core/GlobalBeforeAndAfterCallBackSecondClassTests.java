package dev.pivozavr.jnotunit.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GlobalBeforeAndAfterCallBackSecondClassTests extends GlobalTestMethodsTests {
    @Test
    public void countCallBeforeAndAfterSecondTests() {
        Assertions.assertEquals(1, globalBeforeCallCount);
        Assertions.assertEquals(0, globalAfterCallCount);
    }
}
