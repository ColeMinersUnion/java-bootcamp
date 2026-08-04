package com.northstar.crm.dto;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerResponseDTOTests {

	@Test
	public void testOfCreatesDtoWithAllFields() {
		Instant now = Instant.now();
		Instant later = now.plusSeconds(60);

		CustomerResponseDTO dto = CustomerResponseDTO.of("cid-123", "Jane Roe", "jane@example.com", "ACTIVE", now, later);

		assertEquals("cid-123", dto.getCustomerID());
		assertEquals("Jane Roe", dto.getFullName());
		assertEquals("jane@example.com", dto.getEmail());
		assertEquals("ACTIVE", dto.getStatus());
		assertEquals(now, dto.getCreatedAt());
		assertEquals(later, dto.getUpdatedAt());

		// verify references are preserved (Instant is immutable but check same instance)
		assertSame(now, dto.getCreatedAt());
		assertSame(later, dto.getUpdatedAt());
	}

	@Test
	public void testOfAllowsNullTimestampsAndFields() {
		CustomerResponseDTO dto = CustomerResponseDTO.of(null, null, null, null, null, null);

		assertNull(dto.getCustomerID());
		assertNull(dto.getFullName());
		assertNull(dto.getEmail());
		assertNull(dto.getStatus());
		assertNull(dto.getCreatedAt());
		assertNull(dto.getUpdatedAt());
	}

}


