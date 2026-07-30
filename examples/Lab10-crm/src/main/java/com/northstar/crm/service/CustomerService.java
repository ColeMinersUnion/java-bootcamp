package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Plain Java service for managing customers in-memory.
 * Holds customers in a List and provides methods to add, find, and update them.
 * No Spring / JPA dependencies.
 */
public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();

    /**
     * Adds a new customer to the service.
     * Rejects if customerId is null or blank.
     * Rejects if a customer with the same customerId already exists (throws IllegalStateException).
     * Otherwise, stores the customer and returns it.
     *
     * @param customer the customer to add
     * @return the added customer
     * @throws IllegalArgumentException if customer ID is null or blank
     * @throws IllegalStateException if a customer with this ID already exists
     */
    public Customer addCustomer(Customer customer) {
        if (customer == null || customer.getCustomerId() == null || customer.getCustomerId().isBlank()) {
            throw new IllegalArgumentException("Customer ID cannot be null or blank");
        }
        Optional<Customer> existingCustomer = findByCustomerId(customer.getCustomerId());
        if (existingCustomer.isPresent()) {
            throw new IllegalStateException("Customer with ID " + customer.getCustomerId() + " already exists");
        }
        customers.add(customer);
        return customer;
    }

    /**
     * Finds a customer by their ID.
     *
     * @param customerId the customer ID to search for
     * @return an Optional containing the customer if found, otherwise empty
     */
    public Optional<Customer> findByCustomerId(String customerId) {
        return customers.stream()
                .filter(c -> c.getCustomerId().equals(customerId))
                .findFirst();
    }

    /**
     * Finds all customers with a specific status.
     *
     * @param status the CustomerStatus to filter by
     * @return a list of customers with the specified status
     */
    public List<Customer> findByStatus(CustomerStatus status) {
        return customers.stream()
                .filter(c -> c.getStatus() == status)
                .toList();
    }

    /**
     * Updates the status of a customer.
     * Throws IllegalArgumentException if the customer does not exist.
     * Otherwise, updates the status and returns the customer.
     *
     * @param customerId the customer ID to update
     * @param newStatus the new status to set
     * @return the updated customer
     * @throws IllegalArgumentException if the customer does not exist
     */
    public Customer updateStatus(String customerId, CustomerStatus newStatus) {
        Customer customer = findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer with ID " + customerId + " not found"));
        customer.setStatus(newStatus);
        return customer;
    }

    /**
     * Returns an unmodifiable copy of all customers.
     *
     * @return an unmodifiable list of all customers
     */
    public List<Customer> listAll() {
        return List.copyOf(customers);
    }
}
