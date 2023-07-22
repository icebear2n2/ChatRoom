package com.icebear2n2.chatroom.room.repository;

import com.icebear2n2.chatroom.room.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
