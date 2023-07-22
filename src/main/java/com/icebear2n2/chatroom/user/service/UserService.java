package com.icebear2n2.chatroom.user.service;

import com.icebear2n2.chatroom.room.repository.RoomRepository;
import com.icebear2n2.chatroom.user.domain.entity.User;
import com.icebear2n2.chatroom.user.domain.request.LoginRequest;
import com.icebear2n2.chatroom.user.domain.request.SignupRequest;
import com.icebear2n2.chatroom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public void signup(SignupRequest request) {
        userRepository.save(request.toEntity());
    }

    public User login(LoginRequest request) {

        Optional<User> byEmail = userRepository.findByEmail(request.email());
        User user = byEmail.orElseThrow(() -> new RuntimeException("USER NOT FOUND!!"));
        if (!user.getPassword().equals(request.password())) throw new RuntimeException("INVALID PASSWORD!!");
        return user;
    }



}
