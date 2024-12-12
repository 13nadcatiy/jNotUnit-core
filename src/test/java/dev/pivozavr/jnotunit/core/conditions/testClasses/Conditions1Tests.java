package dev.pivozavr.jnotunit.core.conditions.testClasses;

import dev.pivozavr.jnotunit.core.conditions.GlobalTestMethodsTests;
import org.junit.jupiter.api.RepeatedTest;

class Conditions1Tests extends GlobalTestMethodsTests {

    @RepeatedTest(2)
    public void countCallBeforeAndAfterTest() {
        mainAssert();
    }
}