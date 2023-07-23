package com.icebear2n2.chatroom.chat.domain.request;

import com.icebear2n2.chatroom.chat.domain.entity.Message;
import com.icebear2n2.chatroom.room.domain.entity.Room;
import com.icebear2n2.chatroom.user.domain.entity.User;

import java.time.LocalDateTime;

public record MessageRequest(Long roomId, Long userId, String content, LocalDateTime sendAt) {
    public Message toEntity(Room room, User sender) {
        return Message.builder()
                .room(room)
                .sender(sender)
                .content(content)
                .sendAt(sendAt)
                .build();
    }
}
