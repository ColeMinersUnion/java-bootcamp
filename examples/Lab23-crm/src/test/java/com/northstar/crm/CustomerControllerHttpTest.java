package com.northstar.crm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerHttpTest {
    @LocalServerPort int port;
    @Autowired TestRestTemplate rest;

    @Test
    void createAndGetCus1001() {
        var headers = new HttpHeaders();
        headers.set("X-Correlation-Id", "lab-request-001");
        headers.setContentType(MediaType.APPLICATION_JSON);
        Customer body = new Customer("CUS-1001", "Amina Khan", "amina.khan@example.com", "ACTIVE");
        var created = rest.postForEntity(
                "http://localhost:" + port + "/api/customers",
                new HttpEntity<>(body, headers),
                Customer.class);
        assertEquals(HttpStatus.CREATED, created.getStatusCode());
        assertEquals("CUS-1001",
                rest.getForEntity("http://localhost:" + port + "/api/customers/CUS-1001", Customer.class)
                        .getBody().getId());
    }
}