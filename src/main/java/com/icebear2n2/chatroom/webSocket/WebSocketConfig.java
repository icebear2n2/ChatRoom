package com.icebear2n2.chatroom.webSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatHandler chatHandler;

    public WebSocketConfig(final ChatHandler chatHandler) {
        this.chatHandler = chatHandler;
    }

    // Register the ChatHandler as the WebSocket handler
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Use registry.addHandler() to register the ChatHandler
        // with the "/ws/chat" path to handle WebSocket requests
        registry.addHandler(this.chatHandler, "/ws/chat")
                .setAllowedOrigins("*");
    }
}