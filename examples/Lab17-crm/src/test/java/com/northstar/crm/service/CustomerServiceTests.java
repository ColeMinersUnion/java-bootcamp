package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.exception.BusinessException;
import com.northstar.crm.repository.CustomerRepository;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class CustomerServiceTests {
    DefaultCustomerService service;

    @BeforeEach
    void setUp() {
        // fresh InMemoryCustomerRepository + CustomerValidator + DefaultCustomerService each test
        CustomerRepository repo = new InMemoryCustomerRepository();
        CustomerValidator validator = new CustomerValidator(repo);
        service = new DefaultCustomerService(repo, validator);
    }

    @Test
    void addAndActivateRaviHappyPath() {
        //add Amina ACTIVE + Ravi PROSPECT; changeStatus CUS-1002 → ACTIVE; assert ACTIVE
        Customer amina = new Customer("CUS-1001", "Amina", "amina@pnc.com", null, CustomerStatus.ACTIVE, LocalDateTime.now());
        Customer ravi = new Customer("CUS-1002", "Ravi", "ravi@pnc.com", null, CustomerStatus.PROSPECT, LocalDateTime.now());

        service.addCustomer(amina); // ACTIVE
        service.addCustomer(ravi);  // PROSPECT

        Customer activated = service.changeStatus(
                "CUS-1002", CustomerStatus.ACTIVE, "lab-request-001");

        assert(ravi.getStatus() == CustomerStatus.ACTIVE);
    }

    @Test
    void nullIdThrowsIllegalArgument(){
        Customer amina = new Customer(null, "Amina", "amina@pnc.com", null, CustomerStatus.ACTIVE, LocalDateTime.now());
        assertThrows(IllegalArgumentException.class, () -> service.addCustomer(amina));
    }

    @Test
    void duplicateIdThrowsConflict() {
        Customer amina = new Customer("CUS-1001", "Amina", "amina@pnc.com", null, CustomerStatus.ACTIVE, LocalDateTime.now());
        service.addCustomer(amina);
        assertThrows(BusinessException.class, () -> service.addCustomer(amina));
    }

    @Test
    void duplicateEmailThrowsConflict() {
        Customer amina = new Customer("CUS-1001", "Amina", "amina@pnc.com", null, CustomerStatus.ACTIVE, LocalDateTime.now());
        service.addCustomer(amina);
        Customer ravi = new Customer("CUS-1002", "Ravi", "amina@pnc.com", null, CustomerStatus.ACTIVE, LocalDateTime.now());

        assertThrows(BusinessException.class, () -> service.addCustomer(ravi));
    }

    @Test
    void illegalTransitionThrowsConflict() {
        // ACTIVE → PROSPECT on CUS-1001 → BusinessException; status still ACTIVE
        Customer amina = new Customer("CUS-1001", "Amina", "amina@pnc.com", null, CustomerStatus.ACTIVE, LocalDateTime.now());
        service.addCustomer(amina);

        assertThrows(BusinessException.class, () -> service.changeStatus(
                "CUS-1001", CustomerStatus.PROSPECT, "test-illegal-transition"));
    }


    @Test
    void missingCustomerThrowsNotFound() {
        // changeStatus CUS-9999 → BusinessException with CUSTOMER_NOT_FOUND
        assertThrows(BusinessException.class, () -> service.changeStatus(
                "CUS-9999", CustomerStatus.ACTIVE, "test-not-found"));
    }

    @Test
    void validNewCustomer(){
        Customer amina = new Customer("CUS-1001", "Amina", "amina@pnc.com", null, CustomerStatus.ACTIVE, LocalDateTime.now());
        assertDoesNotThrow(()->service.addCustomer(amina));
    }

    @Test
    void validTransition(){
        Customer amina = new Customer("CUS-1001", "Amina", "amina@pnc.com", null, CustomerStatus.ACTIVE, LocalDateTime.now());
        service.addCustomer(amina);

        assertDoesNotThrow(()->service.changeStatus(
                "CUS-1001", CustomerStatus.SUSPENDED, "test-illegal-transition"));

    }

    @Test
    void listAllTest(){
        Customer amina = new Customer("CUS-1001", "Amina", "amina@pnc.com", null, CustomerStatus.ACTIVE, LocalDateTime.now());
        Customer ravi = new Customer("CUS-1002", "Ravi", "ravi@pnc.com", null, CustomerStatus.PROSPECT, LocalDateTime.now());

        service.addCustomer(amina);
        service.addCustomer(ravi);

        List<Customer> allCustomers = service.listAll();
        assert(allCustomers.contains(amina));
        assert(allCustomers.contains(ravi));
        assert(!allCustomers.contains(new Customer("CUS-9999", "Abdul", "abdul@pnc.com",
                                                    null, CustomerStatus.SUSPENDED, LocalDateTime.now())));
    }
}
