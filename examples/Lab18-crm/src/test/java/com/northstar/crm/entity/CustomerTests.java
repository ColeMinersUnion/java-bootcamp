package com.northstar.crm.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTests {

	@Test
	public void testNoArgConstructorAndSetters() {
		Customer c = new Customer();
		LocalDateTime now = LocalDateTime.now();

		c.setCustomerId("c-1");
		c.setFullName("John Appleseed");
		c.setEmail("john@apple.com");
		c.setPhone("555-1234");
		c.setStatus(CustomerStatus.PROSPECT);
		c.setCreatedAt(now);

		assertEquals("c-1", c.getCustomerId());
		assertEquals("John Appleseed", c.getFullName());
		assertEquals("john@apple.com", c.getEmail());
		assertEquals("555-1234", c.getPhone());
		assertEquals(CustomerStatus.PROSPECT, c.getStatus());
		assertEquals(now, c.getCreatedAt());
	}

	@Test
	public void testAllArgsConstructor() {
		LocalDateTime created = LocalDateTime.of(2020, 1, 2, 3, 4);
		Customer c = new Customer("id-42", "Jane Doe", "jane@example.com", "555-9999", CustomerStatus.ACTIVE, created);

		assertEquals("id-42", c.getCustomerId());
		assertEquals("Jane Doe", c.getFullName());
		assertEquals("jane@example.com", c.getEmail());
		assertEquals("555-9999", c.getPhone());
		assertEquals(CustomerStatus.ACTIVE, c.getStatus());
		assertEquals(created, c.getCreatedAt());
	}

	@Test
	public void testEqualsAndHashCode_basedOnCustomerId() {
		Customer a = new Customer("same-id", "A", "a@x.com", null, CustomerStatus.ACTIVE, null);
		Customer b = new Customer("same-id", "B", "b@x.com", null, CustomerStatus.SUSPENDED, null);
		Customer c = new Customer("other-id", "C", "c@x.com", null, CustomerStatus.ACTIVE, null);

		assertEquals(a, b, "Customers with same customerId should be equal");
		assertEquals(a.hashCode(), b.hashCode(), "HashCodes should be equal for equal customers");

		assertNotEquals(a, c, "Customers with different customerId should not be equal");
	}

	@Test
	public void testEqualsWithNullAndDifferentType() {
		Customer a = new Customer("id-1", "A", null, null, CustomerStatus.ACTIVE, null);

		assertNotEquals(a, null);
		assertNotEquals(a, "not-a-customer");
	}

	@Test
	public void testEqualsWhenCustomerIdIsNull() {
		Customer a = new Customer(null, "A", null, null, CustomerStatus.ACTIVE, null);
		Customer b = new Customer(null, "B", null, null, CustomerStatus.CLOSED, null);

		// equals compares customerId via Objects.equals -> two null ids are equal
		assertEquals(a, b);
		assertEquals(a.hashCode(), b.hashCode());
	}

	@Test
	public void testToStringContainsKeyFields() {
		Customer c = new Customer("id-7", "Name", "e@x.com", null, CustomerStatus.SUSPENDED, null);
		String s = c.toString();

		assertTrue(s.contains("id-7"));
		assertTrue(s.contains("Name"));
		assertTrue(s.contains("SUSPENDED"));
	}

}


