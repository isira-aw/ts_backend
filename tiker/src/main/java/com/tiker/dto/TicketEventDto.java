package com.tiker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for sending ticket events to the frontend via WebSocket.
 */
@Data
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
