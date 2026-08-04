package com.northstar.crm.service;

import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import com.northstar.crm.exception.BusinessException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class CustomerValidatorParameterizedTest {
    CustomerValidator validator = new CustomerValidator(new InMemoryCustomerRepository());

    @ParameterizedTest
    @CsvSource({
            // TODO: legal rows e.g. PROSPECT,ACTIVE
            "PROSPECT,ACTIVE",
            "PROSPECT,CLOSED",
            "ACTIVE,SUSPENDED",
            "ACTIVE,CLOSED",
            "SUSPENDED,ACTIVE",
            "SUSPENDED,CLOSED"
    })
    void legalTransitions(CustomerStatus from, CustomerStatus to) {
        // TODO: assertDoesNotThrow validateTransition(from, to, "lab-request-001")
        assertDoesNotThrow(() -> validator.validateTransition(from, to, "legal-trans-test"));
    }

    @ParameterizedTest
    @CsvSource({
            // TODO: illegal rows e.g. ACTIVE,PROSPECT and CLOSED,ACTIVE
            "PROSPECT,SUSPENDED",
            "ACTIVE,PROSPECT",
            "SUSPENDED,PROSPECT",
            "CLOSED,PROSPECT",
            "CLOSED,ACTIVE",
            "CLOSED,SUSPENDED",
            "CLOSED,CLOSED",
            "PROSPECT,PROSPECT",
            "ACTIVE,ACTIVE",
            "SUSPENDED,SUSPENDED"
    })
    void illegalTransitions(CustomerStatus from, CustomerStatus to) {
        // TODO: assertThrows BusinessException
        //throw new UnsupportedOperationException("TODO: illegal parameterized");
        assertThrows(BusinessException.class, ()->validator.validateTransition(from, to, "illegal-transition-test"));
    }
}
