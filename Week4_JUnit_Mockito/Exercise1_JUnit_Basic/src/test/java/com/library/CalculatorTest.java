package com.library;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;

    // runs before each test - setup
    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
        System.out.println("Setting up Calculator");
    }

    // runs after each test - teardown
    @AfterEach
    public void tearDown() {
        System.out.println("Tearing down Calculator");
    }

    @Test
    public void testAdd() {
        // Arrange
        int a = 5, b = 3;
        // Act
        int result = calculator.add(a, b);
        // Assert
        assertEquals(8, result, "5 + 3 should equal 8");
    }

    @Test
    public void testSubtract() {
        int result = calculator.subtract(10, 4);
        assertEquals(6, result);
    }

    @Test
    public void testMultiply() {
        int result = calculator.multiply(3, 4);
        assertEquals(12, result);
    }

    @Test
    public void testDivide() {
        double result = calculator.divide(10, 2);
        assertEquals(5.0, result);
    }

    @Test
    public void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10, 0);
        });
    }

    @Test
    public void testAssertions() {
        assertEquals(5, 2 + 3);
        assertTrue(5 > 3);
        assertFalse(5 < 3);
        assertNull(null);
        assertNotNull(new Object());
    }
}