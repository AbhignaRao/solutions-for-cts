package com.library;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MyServiceTest {

    @Mock
    private ExternalApi mockApi;

    @InjectMocks
    private MyService service;

    // Exercise 1: Mocking and Stubbing
    @Test
    public void testMockingAndStubbing() {
        when(mockApi.getData()).thenReturn("Mock Data");
        String result = service.fetchData();
        assertEquals("Mock Data", result);
        System.out.println("Ex1 - fetchData: " + result);
    }

    // Exercise 2: Verifying Interactions
    @Test
    public void testVerifyInteraction() {
        when(mockApi.getData()).thenReturn("Mock Data");
        service.fetchData();
        verify(mockApi).getData();
        System.out.println("Ex2 - Interaction verified");
    }

    // Exercise 3: Argument Matching
    @Test
    public void testArgumentMatching() {
        when(mockApi.getDataById(anyInt())).thenReturn("Data for ID");
        String result = service.fetchDataById(42);
        assertEquals("Data for ID", result);
        verify(mockApi).getDataById(42);
        System.out.println("Ex3 - Argument matching: " + result);
    }

    // Exercise 4: Handling Void Methods
    @Test
    public void testVoidMethod() {
        doNothing().when(mockApi).sendNotification(anyString());
        service.notifyUser("Hello!");
        verify(mockApi).sendNotification("Hello!");
        System.out.println("Ex4 - Void method verified");
    }

    // Exercise 5: Multiple Returns
    @Test
    public void testMultipleReturns() {
        when(mockApi.getData())
            .thenReturn("First Call")
            .thenReturn("Second Call")
            .thenReturn("Third Call");

        assertEquals("First Call", service.fetchData());
        assertEquals("Second Call", service.fetchData());
        assertEquals("Third Call", service.fetchData());
        System.out.println("Ex5 - Multiple returns verified");
    }

    // Exercise 6: Verifying Interaction Order
    @Test
    public void testInteractionOrder() {
        when(mockApi.getData()).thenReturn("Data");
        when(mockApi.getDataById(1)).thenReturn("Data 1");

        service.fetchData();
        service.fetchDataById(1);

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).getDataById(1);
        System.out.println("Ex6 - Interaction order verified");
    }

    // Exercise 7: Void Method with Exception
    @Test
    public void testVoidMethodWithException() {
        doThrow(new RuntimeException("Notification failed!"))
            .when(mockApi).sendNotification("ERROR");

        assertThrows(RuntimeException.class, () -> {
            service.notifyUser("ERROR");
        });
        System.out.println("Ex7 - Void method exception verified");
    }
}