package com.northstar.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;
// TODO: add @Service (or @Component) stereotype
@Service
public class NotificationService {
  private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

  public void notifyCreated(String customerId, String correlationId) {
//    LocalDateTime start = LocalDateTime.now();
//    try{
//      TimeUnit.SECONDS.sleep(1);
//    } catch (InterruptedException e) {
//      throw new RuntimeException(e);
//    }
    log.info("customer.created id={} correlationId={}", customerId, correlationId);
    //log.info("This function took {} seconds to execute", LocalDateTime.now().getSecond() - start.getSecond());

  }
}
