package com.tiker.dto;

public class NewConfigRequestDto {
    private int initialTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private boolean permissionGranted;

    public NewConfigRequestDto(int initialTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, boolean permissionGranted) {
        this.initialTickets = initialTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.permissionGranted = permissionGranted;
    }

    public int getInitialTickets() { return initialTickets; }
    public int getTicketReleaseRate() { return ticketReleaseRate; }
    public int getCustomerRetrievalRate() { return customerRetrievalRate; }
    public int getMaxTicketCapacity() { return maxTicketCapacity; }
    public boolean isPermissionGranted() { return permissionGranted; }

    public void setInitialTickets(int initialTickets) { this.initialTickets = initialTickets; }
    public void setTicketReleaseRate(int ticketReleaseRate) { this.ticketReleaseRate = ticketReleaseRate; }
    public void setCustomerRetrievalRate(int customerRetrievalRate) { this.customerRetrievalRate = customerRetrievalRate; }
    public void setMaxTicketCapacity(int maxTicketCapacity) { this.maxTicketCapacity = maxTicketCapacity; }
    public void setPermissionGranted(boolean permissionGranted) { this.permissionGranted = permissionGranted; }
}
