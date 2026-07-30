package com.northstar.crm.entity;

import java.time.LocalDateTime;
import java.util.Objects;

// Java entity class Customer in package com.northstar.crm.entity representing a Northstar CRM customer.
// Fields: customerId (String, format "CUS-1001"), fullName (String), email (String), phone (String), status (CustomerStatus enum: PROSPECT, ACTIVE, SUSPENDED, CLOSED), createdAt (LocalDateTime).
// No-args constructor, all-args constructor, getters and setters, equals/hashCode based only on customerId, toString.
public class Customer {
    private String customerId;
    private String fullName;
    private String email;
    private String phone;
    private CustomerStatus status;
    private LocalDateTime createdAt;

    public Customer() {
    }

    public Customer(String customerId, String fullName, String email, String phone,
                    CustomerStatus status, LocalDateTime createdAt) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() {
        return customerId != null ? customerId.hashCode() : 0;
    }
}
