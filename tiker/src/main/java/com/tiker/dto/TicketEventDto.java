package com.tiker.dto;

public class TicketEventDto {
    private String message;
    private int currentTicketCount;

    public TicketEventDto() {}

    public TicketEventDto(String message, int currentTicketCount) {
        this.message = message;
        this.currentTicketCount = currentTicketCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCurrentTicketCount() {
        return currentTicketCount;
    }

    public void setCurrentTicketCount(int currentTicketCount) {
        this.currentTicketCount = currentTicketCount;
    }

    // Getters and setters
    // ...
}
