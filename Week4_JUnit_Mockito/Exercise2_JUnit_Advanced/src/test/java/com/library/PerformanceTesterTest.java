package com.library;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

public class PerformanceTesterTest {

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    public void testPerformTask() throws InterruptedException {
        PerformanceTester tester = new PerformanceTester();
        tester.performTask();
    }
}