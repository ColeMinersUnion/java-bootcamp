package com.northstar.crm.service;

import com.northstar.crm.entity.CustomerStatus;

/** Extracted collaborator for Lab 11 — verify with Mockito. */
public interface CustomerNotifier {
    // void notifyCreated(String customerId, String correlationId);

    /**
     * Notifies that a customer's status has changed.
     *
     * @param customerId the customer ID
     * @param oldStatus  the previous status
     * @param newStatus  the new status
     */
    void notifyStatusChange(String customerId, CustomerStatus oldStatus, CustomerStatus newStatus);
}
