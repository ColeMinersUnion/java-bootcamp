package com.northstar.crm.account;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AccountProfileResilienceTest {

  private WireMockServer wireMockServer;

  @Autowired
  private TestRestTemplate restTemplate;

  @BeforeEach
  void setup() {
    wireMockServer = new WireMockServer(wireMockConfig().port(8080));
    wireMockServer.start();
  }

  @AfterEach
  void teardown() {
    if (wireMockServer != null) {
      wireMockServer.stop();
    }
  }

  @Test
  void healthyCall_returnsAvailable() {
    stubFor(get("/accounts/CUS-1001/summary")
            .inScenario("recovery").whenScenarioStateIs("STARTED")
            .willReturn(aResponse().withStatus(503))
            .willSetStateTo("available"));
    stubFor(get("/accounts/CUS-1001/summary")
            .inScenario("recovery").whenScenarioStateIs("available")
            .willReturn(okJson("{\"customerId\":\"CUS-1001\",\"available\":true,\"note\":\"ok\"}")));
  }

  @Test
  void openCircuit_failsFastWithoutHittingStub() {
    // TODO: force OPEN then assert fast failure / no extra WireMock traffic
  }

  @Test
  void timeout_returnsUnavailableFallback() {
    // TODO: slow stub > time limiter → fallback available=false

  }
}
