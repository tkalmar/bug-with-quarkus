package org.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.acme.junitExtension.Unfriendly;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StaticProviderTest {

    @Test
    @Order(1)
    public void testFriendly() {
        assertEquals("my friend!",StaticProvider.getGreeting());
    }

    @Test
    @Order(2)
    @Unfriendly
    public void testUnfriendly() {
        System.out.println("Inside Test CL: " + Thread.currentThread().getContextClassLoader().toString());
        assertEquals("my foe!",StaticProvider.getGreeting());
    }
}
