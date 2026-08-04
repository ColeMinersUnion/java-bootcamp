package com.northstar.crm;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.service.CustomerService;
import com.northstar.crm.repository.CustomerRepository;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import com.northstar.crm.service.CustomerValidator;
import com.northstar.crm.service.DefaultCustomerService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        CustomerRepository repo = new InMemoryCustomerRepository();
        CustomerValidator validator = new CustomerValidator(repo);
        CustomerService service = new DefaultCustomerService(repo, validator);

        Customer amina = new Customer("CUS-1001", "Amina", "amina@pnc.com", null, CustomerStatus.ACTIVE, LocalDateTime.now());
        Customer ravi = new Customer("CUS-1002", "Ravi", "ravi@pnc.com", null, CustomerStatus.PROSPECT, LocalDateTime.now());

        service.addCustomer(amina); // ACTIVE
        service.addCustomer(ravi);  // PROSPECT
        Customer activated = service.changeStatus(
                "CUS-1002", CustomerStatus.ACTIVE, "lab-request-001");
        System.out.printf("activated %s status=%s%n",
                activated.getCustomerId(), activated.getStatus());

        try {
            service.changeStatus("CUS-1001", CustomerStatus.PROSPECT, "lab-request-001");
        } catch (IllegalStateException ex) {
            System.out.println("expected failure: " + ex.getMessage());
        }
        System.out.println("CUS-1001 still: " + service.findById("CUS-1001").orElseThrow().getStatus());





    }
}
