package com.tiker.dto;

import jakarta.validation.constraints.Min;

public class StartRequestDto {
    private int configId;

    @Min(value = 1, message = "Initial tickets must be greater than 0")
    private int initialTickets;

    @Min(value = 1, message = "Ticket release rate must be greater than 0")
    private int ticketReleaseRate;

    @Min(value = 1, message = "Customer retrieval rate must be greater than 0")
    private int customerRetrievalRate;

    @Min(value = 1, message = "Max ticket capacity must be greater than 0")
    private int maxTicketCapacity;

    public StartRequestDto() {}

    public StartRequestDto(int configId, int initialTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.configId = configId;
        this.initialTickets = initialTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
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


}
