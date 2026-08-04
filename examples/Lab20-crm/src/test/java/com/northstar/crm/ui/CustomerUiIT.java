package com.northstar.crm.ui;

import com.northstar.crm.ui.pages.CustomerFormPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerUiIT {

    @LocalServerPort
    int port;
    @Autowired TestRestTemplate rest;

    WebDriver driver;
    private WebDriverWait wait;

    // Derive baseUrl from @LocalServerPort when the UI test starts Boot.
    // Don't initialize here because `port` is injected after field initializers run.
    String baseUrl;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--window-size=1280,900");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // compute baseUrl here after Spring has injected the random port
        baseUrl = "http://localhost:" + port;

    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    void createCustomerViaUi() {
        //System.out.println(baseUrl);
        var page = new CustomerFormPage(driver, wait).open(baseUrl);
        page.fill("CUS-1001", "Amina Khan", "ACTIVE").submit();
        assert(page.resultText().contains("CUS-1001"));
        assert(page.resultText().contains("Amina Khan"));
    }

    @Test
    void blankNameShowsValidationMessage() {
        var page = new CustomerFormPage(driver, wait).open(baseUrl);
        page.fill("CUS-1002", "", "PROSPECT").submit();
        assert(page.resultText().toLowerCase().contains("full name"));
    }

    @Test
    void getMissingCustomerReturns404() {
        var response = rest.getForEntity("/api/customers/CUS-MISSING", String.class);
        assert(response.getStatusCode() == HttpStatus.NOT_FOUND);
    }
}
