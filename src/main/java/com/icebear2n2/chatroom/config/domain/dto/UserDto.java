package com.icebear2n2.chatroom.config.domain.dto;

import com.icebear2n2.chatroom.user.domain.entity.User;
import lombok.Getter;

@Getter
public class UserDto {

    private Long id;
    private String email;
    private String username;
    private String password;

//    public UserDto(Long id, String email, String username, String password) {
//        this.id = id;
//        this.email = email;
//        this.username = username;
//        this.password = password;
//    }

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
