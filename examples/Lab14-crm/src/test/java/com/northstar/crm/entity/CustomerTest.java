package com.northstar.crm.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    void equalsUsesCustomerIdOnly() {
        // Test that two customers with the same customerId are equal despite different other fields
        Customer customer1 = new Customer(
            "CUS-1001",
            "John Doe",
            "john@example.com",
            "555-1234",
            CustomerStatus.ACTIVE,
            LocalDateTime.now()
        );

        Customer customer2 = new Customer(
            "CUS-1001",
            "Jane Smith",
            "jane@example.com",
            "555-5678",
            CustomerStatus.PROSPECT,
            LocalDateTime.now()
        );

        // Two customers with same ID but different other fields should be equal
        assertEquals(customer1, customer2, "Customers with same ID should be equal regardless of other fields");

        // Test that customers with different IDs are not equal
        Customer customer3 = new Customer(
            "CUS-1002",
            "John Doe",
            "john@example.com",
            "555-1234",
            CustomerStatus.ACTIVE,
            LocalDateTime.now()
        );

        assertNotEquals(customer1, customer3, "Customers with different IDs should not be equal");
    }

    @Test
    void testToStringContainsCustomerId() {
        // Test that toString() output contains the customerId
        String customerId = "CUS-1001";
        Customer customer = new Customer(
            customerId,
            "John Doe",
            "john@example.com",
            "555-1234",
            CustomerStatus.ACTIVE,
            LocalDateTime.now()
        );

        String toStringOutput = customer.toString();
        assertTrue(
            toStringOutput.contains(customerId),
            "toString() output should contain customerId: " + customerId
        );
    }
}
