package com.icebear2n2.chatroom.room.domain.request;

import com.icebear2n2.chatroom.room.domain.entity.Room;

public record RoomRequest(String roomName, int maxUserCount, String password, boolean isPrivate) {
    public Room toEntity() {
        return Room.builder().roomName(roomName).maxUserCount(maxUserCount).password(password).isPrivate(isPrivate).build();
    }
}
