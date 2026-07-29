package com.library;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {

    @Test
    @Order(1)
    public void firstTest() {
        System.out.println("Executing first test");
        assertTrue(true);
    }

    @Test
    @Order(2)
    public void secondTest() {
        System.out.println("Executing second test");
        assertEquals(4, 2 + 2);
    }

    @Test
    @Order(3)
    public void thirdTest() {
        System.out.println("Executing third test");
        assertNotNull("Hello");
    }
}