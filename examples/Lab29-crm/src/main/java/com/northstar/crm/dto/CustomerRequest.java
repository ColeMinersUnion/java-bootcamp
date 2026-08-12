package com.northstar.crm.dto;

// TODO: add jakarta.validation annotations:
// @NotBlank on id, name, email, status
// @Email on email
import jakarta.validation.constraints.*;


public class CustomerRequest {
  @NotBlank private String id;
  @NotBlank private String name;
  @NotBlank @Email private String email;
  @NotBlank private String status;

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
}
