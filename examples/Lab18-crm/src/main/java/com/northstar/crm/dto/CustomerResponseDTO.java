package com.northstar.crm.dto;

import java.time.Instant;

public class CustomerResponseDTO {
    private String customerID;
    private String fullName;
    private String email;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;

    public static CustomerResponseDTO of(
            String customerID, String fullName, String email,
            String status, Instant createdAt, Instant updatedAt) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.customerID = customerID;
        dto.fullName = fullName;
        dto.email = email;
        dto.status = status;
        dto.createdAt = createdAt;
        dto.updatedAt = updatedAt;
        return dto;
    }
    // getters only (immutable from caller's perspective)

    public String getCustomerID(){ return customerID; }
    public String getFullName(){ return fullName; }
    public String getEmail(){ return email; }
    public String getStatus(){ return status; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

}