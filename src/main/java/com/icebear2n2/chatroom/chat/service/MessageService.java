package com.icebear2n2.chatroom.chat.service;

import com.icebear2n2.chatroom.chat.domain.entity.Message;
import com.icebear2n2.chatroom.chat.domain.request.MessageRequest;
import com.icebear2n2.chatroom.chat.repository.MessageRepository;
import com.icebear2n2.chatroom.room.domain.entity.Room;
import com.icebear2n2.chatroom.room.repository.RoomRepository;
import com.icebear2n2.chatroom.user.domain.entity.User;
import com.icebear2n2.chatroom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    public Message saveMessage(MessageRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("NOT FOUND USER BY " + request.userId()));
        Room room = roomRepository.findById(request.roomId()).orElseThrow(() -> new RuntimeException("NOT FOUND ROOM BY " + request.roomId()));
        return messageRepository.save(request.toEntity(room, user));
    }
}
