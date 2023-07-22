package com.icebear2n2.chatroom.config.repository;

import com.icebear2n2.chatroom.config.domain.entity.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoomRepository extends JpaRepository<UserRoom, Long> {
}
