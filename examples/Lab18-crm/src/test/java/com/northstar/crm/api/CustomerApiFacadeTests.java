package com.northstar.crm.api;

import com.northstar.crm.dto.CustomerRequestDTO;
import com.northstar.crm.dto.CustomerResponseDTO;
import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.mapper.CustomerMapper;
import com.northstar.crm.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerApiFacadeTests {

	@Mock
	private CustomerService mockService;

	private CustomerApiFacade facade;

	@BeforeEach
	public void setup() {
		facade = new CustomerApiFacade(mockService);
	}

	@Test
	public void testCreateWithValidRequestSucceeds() {
		try (MockedStatic<CustomerMapper> mapperMock = mockStatic(CustomerMapper.class)) {
			CustomerRequestDTO request = new CustomerRequestDTO(
					"cid-123", "John Doe", "john@example.com", "ACTIVE"
			);
			Customer entity = new Customer("cid-123", "John Doe", "john@example.com", null,
					CustomerStatus.ACTIVE, LocalDateTime.now());
			CustomerResponseDTO response = CustomerResponseDTO.of(
					"cid-123", "John Doe", "john@example.com", "ACTIVE",
					Instant.now(), null
			);

			mapperMock.when(() -> CustomerMapper.toEntity(request)).thenReturn(entity);
			when(mockService.addCustomer(entity)).thenReturn(entity);
			mapperMock.when(() -> CustomerMapper.toResponse(entity)).thenReturn(response);

			CustomerResponseDTO result = facade.create(request, "corr-id-1");

			assertNotNull(result);
			assertEquals("cid-123", result.getCustomerID());
			assertEquals("John Doe", result.getFullName());
			assertEquals("john@example.com", result.getEmail());
			assertEquals("ACTIVE", result.getStatus());
			verify(mockService).addCustomer(entity);
		}
	}

	@Test
	public void testCreateWithBlankCustomerIDThrowsException() {
		CustomerRequestDTO request = new CustomerRequestDTO(
				"", "John Doe", "john@example.com", "ACTIVE"
		);

		assertThrows(IllegalArgumentException.class,
				() -> facade.create(request, "corr-id-2"));
	}

	@Test
	public void testCreateWithBlankFullNameThrowsException() {
		CustomerRequestDTO request = new CustomerRequestDTO(
				"cid-1", "", "john@example.com", "ACTIVE"
		);

		assertThrows(IllegalArgumentException.class,
				() -> facade.create(request, "corr-id-3"));
	}

	@Test
	public void testCreateWithFullNameTooShortThrowsException() {
		CustomerRequestDTO request = new CustomerRequestDTO(
				"cid-1", "A", "john@example.com", "ACTIVE"
		);

		assertThrows(IllegalArgumentException.class,
				() -> facade.create(request, "corr-id-4"));
	}

	@Test
	public void testCreateWithInvalidEmailThrowsException() {
		CustomerRequestDTO request = new CustomerRequestDTO(
				"cid-1", "John Doe", "not-an-email", "ACTIVE"
		);

		assertThrows(IllegalArgumentException.class,
				() -> facade.create(request, "corr-id-5"));
	}

	@Test
	public void testCreateWithBlankEmailThrowsException() {
		CustomerRequestDTO request = new CustomerRequestDTO(
				"cid-1", "John Doe", "", "ACTIVE"
		);

		assertThrows(IllegalArgumentException.class,
				() -> facade.create(request, "corr-id-6"));
	}

	@Test
	public void testCreateWithBlankStatusThrowsException() {
		CustomerRequestDTO request = new CustomerRequestDTO(
				"cid-1", "John Doe", "john@example.com", ""
		);

		assertThrows(IllegalArgumentException.class,
				() -> facade.create(request, "corr-id-7"));
	}

	@Test
	public void testCreateWithMultipleValidationErrorsThrowsException() {
		CustomerRequestDTO request = new CustomerRequestDTO(
				"", "A", "invalid-email", ""
		);

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> facade.create(request, "corr-id-8"));
		// Verify the correlation ID is in the message
		assertTrue(ex.getMessage().contains("corr-id-8"));
	}

	@Test
	public void testGetByIdSucceeds() {
		try (MockedStatic<CustomerMapper> mapperMock = mockStatic(CustomerMapper.class)) {
			Customer entity = new Customer("cid-456", "Jane Roe", "jane@example.com", null,
					CustomerStatus.SUSPENDED, LocalDateTime.now());
			CustomerResponseDTO response = CustomerResponseDTO.of(
					"cid-456", "Jane Roe", "jane@example.com", "SUSPENDED",
					Instant.now(), null
			);

			when(mockService.findById("cid-456")).thenReturn(Optional.of(entity));
			mapperMock.when(() -> CustomerMapper.toResponse(entity)).thenReturn(response);

			CustomerResponseDTO result = facade.getById("cid-456", "corr-id-9");

			assertNotNull(result);
			assertEquals("cid-456", result.getCustomerID());
			assertEquals("Jane Roe", result.getFullName());
			assertEquals("jane@example.com", result.getEmail());
			assertEquals("SUSPENDED", result.getStatus());
			verify(mockService).findById("cid-456");
		}
	}

	@Test
	public void testGetByIdNotFoundThrowsException() {
		when(mockService.findById("non-existent-id")).thenReturn(Optional.empty());

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> facade.getById("non-existent-id", "corr-id-10"));

		assertTrue(ex.getMessage().contains("customer not found"));
		assertTrue(ex.getMessage().contains("corr-id-10"));
		assertTrue(ex.getMessage().contains("non-existent-id"));
	}

	@Test
	public void testGetByIdWithNullIdThrowsException() {
		when(mockService.findById(null)).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class,
				() -> facade.getById(null, "corr-id-11"));
	}

}


