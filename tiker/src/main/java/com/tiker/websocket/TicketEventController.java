package com.tiker.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class TicketEventController {

    @MessageMapping("/hello")
    public void handleMessageFromClient(String message) {
        // Not used currently. Just a placeholder.
    }
}
