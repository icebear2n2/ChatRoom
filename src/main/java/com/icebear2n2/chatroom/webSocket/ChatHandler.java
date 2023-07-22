package com.icebear2n2.chatroom.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Iterator;
import java.util.LinkedHashSet;

@Component
@Slf4j

// 웹소켓을 사용하여 실시간 채팅 기능을 구현하기 위한 핸들러 클래스

// TextWebSocketHandler : 웹소켓 메시지를 처리하는 기능을 제공

public class ChatHandler extends TextWebSocketHandler {


    // 현재 연결된 웹소켓 세션을 저장하는 컬렉션
    // 중복된 세션을 허용하지 않는다 -> 최대 3개까지 저장 가능
    private static LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>();

    public ChatHandler() {
    }


    // 웹소켓으로부터 수신된 텍스트 메시지를 처리하는 메서드
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        // 현재 연결된 세션의 수가 3개 이상인 경우 가장 오래된 세션을 제거 후,
        // 새로운 세션에게 "채팅 종료" 메시지 전송
//        if (numSet.size() >= 5) {
//            WebSocketSession oldSession = numSet.iterator().next();
//            oldSession.sendMessage(new TextMessage("채팅이 종료되었습니다."));
//            numSet.remove(numSet.iterator().next());
//        }


        boolean isSessionAlive = false;
        Iterator var4 = numSet.iterator();


        WebSocketSession sess;
        while (var4.hasNext()) {
            sess = (WebSocketSession) var4.next();
            if (sess.getId().equals(session.getId())) {
                isSessionAlive = true;
            }
        }

        if (isSessionAlive) {
            var4 = numSet.iterator();

            while (var4.hasNext()) {
                sess = (WebSocketSession) var4.next();
                sess.sendMessage(message);
            }
        }

    }


    // 웹소켓 연결이 성공적인 경우에 호출되는 메서드
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        // 연결된 세션을 numSet 에 추가
        numSet.add(session);
    }


    // 웹소켓 연결이 종료된 후 호출되는 메서드
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        // 종료된 세션을 numSet 에서 제거
        numSet.remove(session);
    }
}
