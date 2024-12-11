package com.tiker.dto;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.validation.constraints.Min;

public class NewConfigRequestDto {
    private int initialTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private boolean permissionGranted; // Added field



    public NewConfigRequestDto() {}

    public NewConfigRequestDto(int initialTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, boolean permissionGranted) {
        this.initialTickets = initialTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.permissionGranted = permissionGranted;
    }

    public boolean isPermissionGranted() {
        return permissionGranted;
    }

    public int getInitialTickets() {
        return initialTickets;
    }

    public void setInitialTickets(int initialTickets) {
        this.initialTickets = initialTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public void setPermissionGranted(boolean permissionGranted) {
        this.permissionGranted = permissionGranted;
    }


}
