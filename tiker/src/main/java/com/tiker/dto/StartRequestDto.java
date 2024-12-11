package com.tiker.dto;

public class StartRequestDto {
    private int configId;
    private int initialTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public int getConfigId() { return configId; }
    public int getInitialTickets() { return initialTickets; }
    public int getTicketReleaseRate() { return ticketReleaseRate; }
    public int getCustomerRetrievalRate() { return customerRetrievalRate; }
    public int getMaxTicketCapacity() { return maxTicketCapacity; }

    public void setConfigId(int configId) { this.configId = configId; }
    public void setInitialTickets(int initialTickets) { this.initialTickets = initialTickets; }
    public void setTicketReleaseRate(int ticketReleaseRate) { this.ticketReleaseRate = ticketReleaseRate; }
    public void setCustomerRetrievalRate(int customerRetrievalRate) { this.customerRetrievalRate = customerRetrievalRate; }
    public void setMaxTicketCapacity(int maxTicketCapacity) { this.maxTicketCapacity = maxTicketCapacity; }
}
