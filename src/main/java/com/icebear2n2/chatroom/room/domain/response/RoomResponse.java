package com.icebear2n2.chatroom.room.domain.response;

import com.icebear2n2.chatroom.config.domain.dto.RoomDto;
import com.icebear2n2.chatroom.config.domain.dto.UserDto;
import com.icebear2n2.chatroom.config.domain.entity.UserRoom;
import com.icebear2n2.chatroom.room.domain.entity.Room;
import com.icebear2n2.chatroom.user.domain.entity.User;
import lombok.Getter;

import java.util.List;


public class RoomResponse extends RoomDto {
    private List<UserDto> users;

    public RoomResponse(Room room) {
        super(room);
        users = room.getUsers().stream().map(UserRoom::getUser).map(UserDto::new).toList();
    }
}
