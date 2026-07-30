package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        // Create a fresh CustomerService instance before each test
        customerService = new CustomerService();
    }

    @Test
    void addCustomerStoresNewCustomer() {
        // Test that addCustomer stores a new customer and returns it
        Customer customer = new Customer(
            "CUS-1001",
            "Amina Khan",
            "amina@example.com",
            "555-0001",
            CustomerStatus.ACTIVE,
            LocalDateTime.now()
        );

        Customer addedCustomer = customerService.addCustomer(customer);

        assertNotNull(addedCustomer, "addCustomer should return the added customer");
        assertEquals("CUS-1001", addedCustomer.getCustomerId(), "customerId should match");
        assertEquals("Amina Khan", addedCustomer.getFullName(), "fullName should match");

        // Verify the customer can be found
        var foundCustomer = customerService.findByCustomerId("CUS-1001");
        assertTrue(foundCustomer.isPresent(), "Customer should be findable after adding");
        assertEquals(addedCustomer, foundCustomer.get(), "Found customer should match added customer");
    }

    @Test
    void addCustomerWithDuplicateCustomerIdThrowsIllegalStateException() {
        // Test that adding a customer with a duplicate customerId throws IllegalStateException
        Customer customer1 = new Customer(
            "CUS-1001",
            "Amina Khan",
            "amina@example.com",
            "555-0001",
            CustomerStatus.ACTIVE,
            LocalDateTime.now()
        );

        // Add the first customer successfully
        customerService.addCustomer(customer1);

        // Attempt to add another customer with the same customerId
        Customer customer2 = new Customer(
            "CUS-1001",
            "Different Name",
            "different@example.com",
            "555-0002",
            CustomerStatus.PROSPECT,
            LocalDateTime.now()
        );

        // Should throw IllegalStateException for duplicate customerId
        assertThrows(
            IllegalStateException.class,
            () -> customerService.addCustomer(customer2),
            "Adding customer with duplicate customerId should throw IllegalStateException"
        );
    }

    @Test
    void updateStatusChangesExistingCustomerStatus() {
        // Test that updateStatus successfully changes an existing customer's status
        Customer customer = new Customer(
            "CUS-1002",
            "Bob Smith",
            "bob@example.com",
            "555-0002",
            CustomerStatus.PROSPECT,
            LocalDateTime.now()
        );

        customerService.addCustomer(customer);

        // Update the status from PROSPECT to ACTIVE
        Customer updatedCustomer = customerService.updateStatus("CUS-1002", CustomerStatus.ACTIVE);

        assertNotNull(updatedCustomer, "updateStatus should return the updated customer");
        assertEquals("CUS-1002", updatedCustomer.getCustomerId(), "customerId should remain unchanged");
        assertEquals(CustomerStatus.ACTIVE, updatedCustomer.getStatus(), "Status should be updated to ACTIVE");

        // Verify the change persists when finding the customer again
        var foundCustomer = customerService.findByCustomerId("CUS-1002");
        assertTrue(foundCustomer.isPresent(), "Updated customer should still be found");
        assertEquals(CustomerStatus.ACTIVE, foundCustomer.get().getStatus(), "Status should persist as ACTIVE");
    }

    @Test
    void updateStatusOnUnknownCustomerIdThrowsIllegalArgumentException() {
        // Test that updateStatus throws IllegalArgumentException for unknown customerId
        assertThrows(
            IllegalArgumentException.class,
            () -> customerService.updateStatus("CUS-9999", CustomerStatus.ACTIVE),
            "Updating status for unknown customer should throw IllegalArgumentException"
        );
    }


    //Weak Test..?
    @Test
    void addCustomerWithNullOrBlankCustomerIdThrowsIllegalArgumentException() {
        // Test that addCustomer with null customerId throws IllegalArgumentException
        Customer customerWithNullId = new Customer(
            null,
            "John Doe",
            "john@example.com",
            "555-0001",
            CustomerStatus.ACTIVE,
            LocalDateTime.now()
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> customerService.addCustomer(customerWithNullId),
            "Adding customer with null customerId should throw IllegalArgumentException"
        );

        // Test that addCustomer with blank customerId throws IllegalArgumentException
        Customer customerWithBlankId = new Customer(
            "   ",
            "Jane Doe",
            "jane@example.com",
            "555-0002",
            CustomerStatus.ACTIVE,
            LocalDateTime.now()
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> customerService.addCustomer(customerWithBlankId),
            "Adding customer with blank customerId should throw IllegalArgumentException"
        );
    }
}
