package com.northstar.crm.endpoint;

import com.northstar.crm.service.CustomerService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CustomerEndpoint {
  private static final String NAMESPACE = "/ws"; //Endpoint url

  private final CustomerService customerService;
  private final CustomerSoapMapper mapper;

  public CustomerEndpoint(CustomerService customerService, CustomerSoapMapper mapper) {
    this.customerService = customerService;
    this.mapper = mapper;
  }

  @PayloadRoot(namespace = NAMESPACE, localPart = "GetCustomerRequest")
  @ResponsePayload
  public Object getCustomer(@RequestPayload Object request) {
    String id = mapper.customerIdFromGetRequest(request);
    com.northstar.crm.model.Customer customer = customerService.get(id);
    return mapper.toGetCustomerResponse(customer);
  }
}

