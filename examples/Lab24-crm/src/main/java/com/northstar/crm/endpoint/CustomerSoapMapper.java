package com.northstar.crm.endpoint;

import com.northstar.crm.model.Customer;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Component
public class CustomerSoapMapper {

  private static final String NAMESPACE = "http://northstar.com/crm/customers";

  public String customerIdFromGetRequest(Object request) {
    // Expect a DOM Element representing GetCustomerRequest
    if (request instanceof Element) {
      Element el = (Element) request;
      // Try namespace-aware lookup first
      NodeList list = el.getElementsByTagNameNS(NAMESPACE, "customerId");
      if (list != null && list.getLength() > 0) {
        return list.item(0).getTextContent().trim();
      }
      // Fallback: non-namespace lookup
      list = el.getElementsByTagName("customerId");
      if (list != null && list.getLength() > 0) {
        return list.item(0).getTextContent().trim();
      }
    }
    throw new IllegalArgumentException("Unsupported request payload type or missing customerId: " + request);
  }

  public Object toGetCustomerResponse(Customer customer) {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.newDocument();

      Element resp = doc.createElementNS(NAMESPACE, "GetCustomerResponse");

      Element id = doc.createElementNS(NAMESPACE, "customerId");
      id.setTextContent(customer.getId());
      resp.appendChild(id);

      Element name = doc.createElementNS(NAMESPACE, "name");
      name.setTextContent(customer.getName());
      resp.appendChild(name);

      Element email = doc.createElementNS(NAMESPACE, "email");
      email.setTextContent(customer.getEmail());
      resp.appendChild(email);

      Element status = doc.createElementNS(NAMESPACE, "status");
      status.setTextContent(customer.getStatus());
      resp.appendChild(status);

      return resp;
    } catch (ParserConfigurationException e) {
      throw new RuntimeException("Failed to build SOAP response DOM", e);
    }
  }
}


