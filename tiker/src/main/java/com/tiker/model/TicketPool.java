package com.tiker.model;



public class TicketPool {
    private int totalTickets;
    private final int maxCapacity;

    public TicketPool(int initialTickets, int maxCapacity) {
        this.totalTickets = initialTickets;
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTickets(int count, int vendorID) {
        if (Thread.currentThread().isInterrupted()) return;

        int addedTickets = Math.min(count, maxCapacity - totalTickets);
        if (addedTickets > 0) {
            totalTickets += addedTickets;
        }
        notifyAll();
    }

    public synchronized void retrieveTickets(int count, int customerID) {
        while (count > totalTickets) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        totalTickets -= count;
    }

    public synchronized int getTotalTickets() {
        return totalTickets;
    }
}
