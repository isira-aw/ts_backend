package com.tiker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // Clients subscribe to /topic/ticketUpdates
        config.setApplicationDestinationPrefixes("/app"); // Prefix for @MessageMapping
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint the frontend will connect to: ws://localhost:8080/ws
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }
}
