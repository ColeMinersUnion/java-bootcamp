package com.northstar.crm.dto;

import jakarta.validation.constraints.*;


public class CustomerRequestDTO {

    @NotBlank(message = "customerId is required")
    @Size(max = 32, message = "customerId must be at most 32 characters")
    private String customerID;

    @NotBlank(message = "fullName is required")
    @Size(min = 2, max = 100, message = "fullName must be between 2 and 100 characters")
    private String fullName;

    @NotBlank(message = "email is required")
    @Email(message = "email must be a valid address")
    @Size(max = 254, message = "email must be at most 254 characters")
    private String email;

    @NotBlank(message = "status is required")
    @Size(min = 1, max = 32, message = "status must be between 1 and 32 characters")
    private String status;

    // constructors
    public CustomerRequestDTO(String customerID, String fullName, String email, String status){
        this.customerID = customerID;
        this.fullName = fullName;
        this.email = email;
        this.status = status;
    }

    public CustomerRequestDTO(){
        customerID = null;
        fullName = null;
        email = null;
        status = null;
    }

    //getters
    public String getCustomerID(){ return customerID; }
    public String getFullName(){ return fullName; }
    public String getEmail(){ return email; }
    public String getStatus(){ return status; }

    //setters
    public void setCustomerID(String customerID){ this.customerID = customerID; }
    public void setFullName(String fullName){ this.fullName = fullName; }
    public void setEmail(String email){ this.email = email; }
    public void setStatus(String status){ this.status = status; }

}