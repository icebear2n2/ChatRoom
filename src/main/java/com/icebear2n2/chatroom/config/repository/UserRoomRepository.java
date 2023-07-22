package com.icebear2n2.chatroom.config.repository;

import com.icebear2n2.chatroom.config.domain.entity.UserRoom;
import com.icebear2n2.chatroom.room.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoomRepository extends JpaRepository<UserRoom, Long> {
    List<UserRoom> findByRoom(Room room);
}
