package com.tiker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * This class configures WebSockets using STOMP protocol.
 *
 * @EnableWebSocketMessageBroker:
 * This tells Spring to enable a WebSocket message broker to handle messaging between clients.
 *
 * We define an endpoint "/ws" that the frontend can connect to, and we set the message broker prefix "/topic".
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // "/topic" is the prefix for messages that will be broadcast to all subscribers.
        config.enableSimpleBroker("/topic");
        // "/app" is prefix for messages that the client might send to the server.
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint that clients will use to connect via WebSocket.
        // With setAllowedOrigins("*") - in production, specify allowed domains.
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }
}
