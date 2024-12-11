package com.tiker.dto;

public class TicketEventDto {
    private String message;
    private int currentCount;

    public TicketEventDto(String message, int currentCount) {
        this.message = message;
        this.currentCount = currentCount;
    }

    public String getMessage() { return message; }
    public int getCurrentCount() { return currentCount; }

    public void setMessage(String message) { this.message = message; }
    public void setCurrentCount(int currentCount) { this.currentCount = currentCount; }
}
