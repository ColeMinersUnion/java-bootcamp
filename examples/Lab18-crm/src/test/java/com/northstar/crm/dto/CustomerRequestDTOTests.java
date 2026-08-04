package com.northstar.crm.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerRequestDTOTests {

	private static ValidatorFactory validatorFactory;
	private static Validator validator;

	@BeforeAll
	public static void setupValidatorFactory() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@AfterAll
	public static void closeValidatorFactory() {
		if (validatorFactory != null) {
			validatorFactory.close();
		}
	}

	@Test
	public void testValidCustomerRequestDTO_hasNoViolations() {
		CustomerRequestDTO dto = new CustomerRequestDTO("id-123", "John Doe", "john.doe@example.com", "ACTIVE");
		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(dto);
		assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
	}

	@Test
	public void testGettersAndSetters() {
		CustomerRequestDTO dto = new CustomerRequestDTO();
		dto.setCustomerID("cid-1");
		dto.setFullName("Alice Smith");
		dto.setEmail("alice@example.com");
		dto.setStatus("NEW");

		assertEquals("cid-1", dto.getCustomerID());
		assertEquals("Alice Smith", dto.getFullName());
		assertEquals("alice@example.com", dto.getEmail());
		assertEquals("NEW", dto.getStatus());
	}

	@Test
	public void testCustomerIDNotBlank() {
		CustomerRequestDTO dto = new CustomerRequestDTO("", "John Doe", "john@example.com", "ACTIVE");
		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(dto);
		assertTrue(violations.stream().anyMatch(v -> "customerID".equals(v.getPropertyPath().toString())),
				"Expected a violation on property 'customerID'");
	}

	@Test
	public void testCustomerIDSizeMax() {
		String longId = "a".repeat(33);
		CustomerRequestDTO dto = new CustomerRequestDTO(longId, "John Doe", "john@example.com", "ACTIVE");
		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(dto);
		assertTrue(violations.stream().anyMatch(v -> "customerID".equals(v.getPropertyPath().toString())),
				"Expected a size violation on property 'customerID'");
	}

	@Test
	public void testFullNameNotBlank() {
		CustomerRequestDTO dto = new CustomerRequestDTO("id-1", "", "john@example.com", "ACTIVE");
		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(dto);
		assertTrue(violations.stream().anyMatch(v -> "fullName".equals(v.getPropertyPath().toString())),
				"Expected a violation on property 'fullName'");
	}

	@Test
	public void testFullNameSizeMin() {
		CustomerRequestDTO dto = new CustomerRequestDTO("id-1", "A", "john@example.com", "ACTIVE");
		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(dto);
		assertTrue(violations.stream().anyMatch(v -> "fullName".equals(v.getPropertyPath().toString())),
				"Expected a size(min) violation on property 'fullName'");
	}

	@Test
	public void testEmailNotBlank() {
		CustomerRequestDTO dto = new CustomerRequestDTO("id-1", "John Doe", "", "ACTIVE");
		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(dto);
		assertTrue(violations.stream().anyMatch(v -> "email".equals(v.getPropertyPath().toString())),
				"Expected a violation on property 'email' for NotBlank");
	}

	@Test
	public void testEmailInvalidFormat() {
		CustomerRequestDTO dto = new CustomerRequestDTO("id-1", "John Doe", "not-an-email", "ACTIVE");
		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(dto);
		assertTrue(violations.stream().anyMatch(v -> "email".equals(v.getPropertyPath().toString())),
				"Expected an Email format violation on property 'email'");
	}

	@Test
	public void testEmailSizeMax() {
		// create an email longer than 254 characters
		String local = "a".repeat(255);
		String longEmail = local + "@example.com"; // length > 254
		CustomerRequestDTO dto = new CustomerRequestDTO("id-1", "John Doe", longEmail, "ACTIVE");
		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(dto);
		assertTrue(violations.stream().anyMatch(v -> "email".equals(v.getPropertyPath().toString())),
				"Expected a size(max) violation on property 'email'");
	}

	@Test
	public void testStatusNotBlank() {
		CustomerRequestDTO dto = new CustomerRequestDTO("id-1", "John Doe", "john@example.com", "");
		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(dto);
		assertTrue(violations.stream().anyMatch(v -> "status".equals(v.getPropertyPath().toString())),
				"Expected a violation on property 'status'");
	}

	@Test
	public void testStatusSizeMax() {
		String longStatus = "a".repeat(33);
		CustomerRequestDTO dto = new CustomerRequestDTO("id-1", "John Doe", "john@example.com", longStatus);
		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(dto);
		assertTrue(violations.stream().anyMatch(v -> "status".equals(v.getPropertyPath().toString())),
				"Expected a size(max) violation on property 'status'");
	}

}


