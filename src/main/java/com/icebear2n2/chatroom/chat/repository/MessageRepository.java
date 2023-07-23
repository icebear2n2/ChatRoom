package com.icebear2n2.chatroom.chat.repository;

import com.icebear2n2.chatroom.chat.domain.entity.Message;
import com.icebear2n2.chatroom.room.domain.entity.Room;
import com.icebear2n2.chatroom.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByRoomAndSender(Room room, User sender);

    List<Message> findByRoom(Room room);

}
