package com.tiker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing an event to be sent via WebSocket.
 * For example, when a vendor adds tickets or a customer retrieves tickets.
 */
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class TicketEventDto {
    private String message;
    private int currentTicketCount;

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
}
