package com.tiker.model;

import java.util.Random;

/**
 * Represents a vendor thread that adds tickets periodically.
 */
public class Vendor implements Runnable {
    private final int vendorID;
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final Random random = new Random();
    private TicketUpdateCallback callback;

    public Vendor(int vendorID, TicketPool ticketPool, int ticketReleaseRate, TicketUpdateCallback callback) {
        this.vendorID = vendorID;
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.callback = callback;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int ticketsToAdd = random.nextInt(ticketReleaseRate) + 1;
            ticketPool.addTickets(ticketsToAdd, vendorID);
            // Notify via callback
            int currentCount = ticketPool.getTotalTickets();
            String message = "Vendor-" + vendorID + " added " + ticketsToAdd + " tickets. Total: " + currentCount;
            callback.onTicketUpdate(message, currentCount);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * A simple callback interface to notify when tickets are updated.
     */
    public interface TicketUpdateCallback {
        void onTicketUpdate(String message, int currentCount);
    }
}
