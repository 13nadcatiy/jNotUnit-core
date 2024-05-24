package dev.pivozavrus.jnotunit.core;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(GlobalTestMethodsTests.class)
public class GlobalTestMethodsTests implements GlobalBeforeAndAfterCallBack {
    static int globalBeforeCallCount = 0;
    static int globalAfterCallCount = 0;
    static int classBeforeCallCount = 0;
    static int classAfterCallCount = 0;


    @Override
    public void beforeAllTests() {
        assertEquals(0, globalBeforeCallCount);
        assertEquals(0, globalAfterCallCount);
        assertEquals(0, classBeforeCallCount);
        assertEquals(0, classAfterCallCount);
        globalBeforeCallCount++;
    }

    @Override
    public void afterAllTests() {
        globalAfterCallCount++;
        assertEquals(1, globalBeforeCallCount);
        assertEquals(1, globalAfterCallCount);

        assertTrue(classBeforeCallCount > 1);
        assertTrue(classAfterCallCount > 1);
    }

    @BeforeAll
    public static void beforeClass() {
        assertEquals(1, globalBeforeCallCount);
        assertEquals(0, globalAfterCallCount);

        classBeforeCallCount++;

        assertTrue(classBeforeCallCount >= 1);
    }

    @AfterAll
    public static void afterClass(){
        assertEquals(1, globalBeforeCallCount);
        assertEquals(0, globalAfterCallCount);

        classAfterCallCount++;

        assertTrue(classAfterCallCount >= 1);
    }
}
