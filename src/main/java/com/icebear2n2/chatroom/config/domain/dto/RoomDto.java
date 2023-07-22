package com.icebear2n2.chatroom.config.domain.dto;

import com.icebear2n2.chatroom.room.domain.entity.Room;
import lombok.Getter;

@Getter
public class RoomDto {
    private Long id;
    private String roomName;

    private int userCount;
    private int maxUserCount;
    private String password;
    private boolean isPrivate;

    public RoomDto(Room room) {
        this.id = room.getId();
        this.roomName = room.getRoomName();
        this.userCount = room.getUserCount();
        this.maxUserCount = room.getMaxUserCount();
        this.password = room.getPassword();
        this.isPrivate = room.isPrivate();
    }
}
