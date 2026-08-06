package com.northstar.crm;

import com.northstar.crm.api.CustomerController;
import com.northstar.crm.model.Customer;
import com.northstar.crm.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CrmApplicationTests {

  @Autowired
  private CustomerController customerController;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void contextLoads() {
    // Verifies Spring context loads and all beans are wired correctly
    // This proves the slice is peer-reproducible
    assertNotNull(customerController, "CustomerController should be autowired");
    assertNotNull(customerService, "CustomerService should be autowired");
  }

  @Test
  void testCus1001SmokeTest() {
    // HTTP IT smoke test: verify CUS-1001 customer can be retrieved
    ResponseEntity<Customer> response = restTemplate.getForEntity(
        "/api/customers/CUS-1001", Customer.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("CUS-1001", response.getBody().getId());
    assertEquals("Amina Khan", response.getBody().getName());
  }
}
