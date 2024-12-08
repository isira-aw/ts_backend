package com.tiker.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * Controller to handle incoming WebSocket messages if needed.
 * Currently empty as we are only pushing updates from the server side.
 *
 * @MessageMapping("/hello") means if client sends message to "/app/hello",
 * this method would handle it.
 */
@Controller
public class TicketEventController {

    @MessageMapping("/hello")
    public void handleMessageFromClient(String message) {
        // Currently, no handling needed. This is just an example.
    }
}
