package com.icebear2n2.chatroom.user.domain.request;

import com.icebear2n2.chatroom.user.domain.entity.User;

public record SignupRequest(String email, String username, String password) {
    public User toEntity() {
        return User.builder()
                .email(email)
                .username(username)
                .password(password)
                .build();
    }
}
