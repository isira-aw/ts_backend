package com.tiker.dto;

public class ConfigDto {
    private int id;
    private int initialTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public ConfigDto(int id, int initialTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.id = id;
        this.initialTickets = initialTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Getters and setters
    public int getId() { return id; }
    public int getInitialTickets() { return initialTickets; }
    public int getTicketReleaseRate() { return ticketReleaseRate; }
    public int getCustomerRetrievalRate() { return customerRetrievalRate; }
    public int getMaxTicketCapacity() { return maxTicketCapacity; }

    public void setId(int id) { this.id = id; }
    public void setInitialTickets(int initialTickets) { this.initialTickets = initialTickets; }
    public void setTicketReleaseRate(int ticketReleaseRate) { this.ticketReleaseRate = ticketReleaseRate; }
    public void setCustomerRetrievalRate(int customerRetrievalRate) { this.customerRetrievalRate = customerRetrievalRate; }
    public void setMaxTicketCapacity(int maxTicketCapacity) { this.maxTicketCapacity = maxTicketCapacity; }
}
