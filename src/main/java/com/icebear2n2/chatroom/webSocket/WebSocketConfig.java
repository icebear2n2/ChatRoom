package com.icebear2n2.chatroom.webSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;



@Configuration
@EnableWebSocket

// 웹소켓을 활성화하고, 웹소켓 핸들러를 등록하는 웹소켓 구성 클래스
public class WebSocketConfig implements WebSocketConfigurer {
    private final ChatHandler chatHandler;


    // WebSocketHandlerRegistry 를 통해 웹소켓 핸들러를 등록
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        // registry.addHandler() 를 사용하여 ChatHandler 를 등록

        //  new String[]{"ws/chat"} 를 통해 해당 핸들러가 "/ws/chat" 경로로 요청을 처리할 수 있도록 설정
        // setAllowedOrigins(new String[]{"*"}) : 모든 도메인에서의 접근 허용 -> Croos-Origin 요청 가능
        registry.addHandler(this.chatHandler, new String[]{"ws/chat"}).setAllowedOrigins(new String[]{"*"});
    }

    public WebSocketConfig(final ChatHandler chatHandler) {
        this.chatHandler = chatHandler;
    }
}

