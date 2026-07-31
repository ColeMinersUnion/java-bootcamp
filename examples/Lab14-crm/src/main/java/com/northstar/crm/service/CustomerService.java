package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing Customer entities with optional notifications.
 */
public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();
    private final CustomerNotifier notifier;

    /**
     * Constructs a CustomerService with the given notifier.
     *
     * @param notifier the notifier to use for events
     */
    public CustomerService(CustomerNotifier notifier) {
        this.notifier = notifier;
    }

    /**
     * Constructs a CustomerService with a no-op notifier.
     */
    public CustomerService() {
        this((customerId, oldStatus, newStatus) -> {
            // No-op notifier
        });
    }

    public Customer addCustomer(Customer customer) {
        validateCustomerId(customer);
        if (findByCustomerId(customer.getCustomerId()).isPresent()) {
            throw new IllegalStateException("Duplicate customerId: " + customer.getCustomerId());
        }
        if (customer.getCreatedAt() == null) {
            customer.setCreatedAt(LocalDateTime.now());
        }
        if (customer.getStatus() == null) {
            customer.setStatus(CustomerStatus.PROSPECT);
        }
        customers.add(customer);
        // System.out.println("created " + customer.getCustomerId()); // TODO: notify via CustomerNotifier
        return customer;
    }

    /**
     * Creates a new customer with the provided details.
     *
     * @param customerId unique customer identifier (non-blank)
     * @param fullName customer's full name (non-blank)
     * @param email customer's email address (can be null)
     * @param phone customer's phone number (can be null)
     * @param status customer status (defaults to PROSPECT if null)
     * @return the created Customer
     * @throws IllegalArgumentException if customerId or fullName is null/blank
     * @throws IllegalStateException if customerId already exists
     */
    public Customer createCustomer(String customerId, String fullName, String email,
                                   String phone, CustomerStatus status, LocalDateTime time) {
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setStatus(status);
        customer.setCreatedAt(time);
        return addCustomer(customer);
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param customerId the customer's unique identifier
     * @return the Customer
     * @throws IllegalArgumentException if customerId is not found
     */
    public Customer getCustomer(String customerId) {
        return findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
    }

    /*

    public Customer getCustomer(String customerId) {
        Customer found = customersById.get(customerId);
        if (found == null) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId + " correlationId=" + correlationId());
            }
            return found;
     }
     */

    public Optional<Customer> findByCustomerId(String customerId) {
        return customers.stream()
                .filter(c -> c.getCustomerId().equals(customerId))
                .findFirst();
    }

    public Customer updateStatus(String customerId, CustomerStatus status) {
        Customer c = findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
        CustomerStatus oldStatus = c.getStatus();
        c.setStatus(status);
        notifier.notifyStatusChange(customerId, oldStatus, status);
        return c;
    }

    /**
     * Validates that a customer has a non-null and non-blank customerId.
     *
     * @param customer the customer to validate
     * @throws IllegalArgumentException if customer is null or customerId is null/blank
     */
    private void validateCustomerId(Customer customer) {
        if (customer == null || customer.getCustomerId() == null || customer.getCustomerId().isBlank()) {
            throw new IllegalArgumentException("customerId is required");
        }
    }
}

