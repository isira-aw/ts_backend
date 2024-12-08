package com.tiker.model;

import java.util.Random;

/**
 * Represents a customer thread that retrieves tickets periodically.
 */
public class Customer implements Runnable {
    private final int customerID;
    private final TicketPool ticketPool;
    private final int retrievalRate;
    private final Random random = new Random();
    private Vendor.TicketUpdateCallback callback;

    public Customer(int customerID, TicketPool ticketPool, int retrievalRate, Vendor.TicketUpdateCallback callback) {
        this.customerID = customerID;
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
        this.callback = callback;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int ticketsToRetrieve = random.nextInt(retrievalRate) + 1;
            ticketPool.retrieveTickets(ticketsToRetrieve, customerID);
            int currentCount = ticketPool.getTotalTickets();
            String message = "Customer-" + customerID + " retrieved " + ticketsToRetrieve + " tickets. Remaining: " + currentCount;
            callback.onTicketUpdate(message, currentCount);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
