package com.icebear2n2.chatroom.user.domain.response;

import com.icebear2n2.chatroom.config.domain.dto.RoomDto;
import com.icebear2n2.chatroom.config.domain.dto.UserDto;
import com.icebear2n2.chatroom.config.domain.entity.UserRoom;
import com.icebear2n2.chatroom.user.domain.entity.User;

import java.util.List;

public class UserResponse extends UserDto {

    private List<RoomDto> rooms;

    public UserResponse(User user) {
        super(user);
        rooms = user.getRooms().stream().map(UserRoom::getRoom).map(RoomDto::new).toList();
    }
}
