package com.icebear2n2.chatroom.user.service;

import com.icebear2n2.chatroom.config.domain.entity.UserRoom;
import com.icebear2n2.chatroom.config.repository.UserRoomRepository;
import com.icebear2n2.chatroom.room.domain.entity.Room;
import com.icebear2n2.chatroom.room.repository.RoomRepository;
import com.icebear2n2.chatroom.user.domain.entity.User;
import com.icebear2n2.chatroom.user.domain.request.ConnectRequest;
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

    private final UserRoomRepository userRoomRepository;

    public void signup(SignupRequest request) {
        userRepository.save(request.toEntity());
    }

    public User login(LoginRequest request) {

        Optional<User> byEmail = userRepository.findByEmail(request.email());
        User user = byEmail.orElseThrow(() -> new RuntimeException("USER NOT FOUND!!"));
        if (!user.getPassword().equals(request.password())) throw new RuntimeException("INVALID PASSWORD!!");
        return user;
    }

    public void joinRoom(ConnectRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("NOT FOUND USER BY " + request.userId()));
        Room room = roomRepository.findById(request.roomId()).orElseThrow(() -> new RuntimeException("NOT FOUND ROOM BY " + request.roomId()));


        // Create and save a new UserRoom entity
        UserRoom userRoom = new UserRoom();
        userRoom.setUser(user);
        userRoom.setRoom(room);
        userRoomRepository.save(userRoom);

        // Increment the userCount of the room and save it back to the database
        room.setUserCount(room.getUserCount() + 1);
        roomRepository.save(room);
    }


    public void leaveRoom(ConnectRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("NOT FOUND USER BY " + request.userId()));
        Room room = roomRepository.findById(request.roomId()).orElseThrow(() -> new RuntimeException("NOT FOUND ROOM BY " + request.roomId()));
        UserRoom userRoom = new UserRoom();
        userRoom.setUser(user);
        userRoom.setRoom(room);
        userRoomRepository.delete(userRoom);

        if (room.getUserCount() != 0) {
            room.setUserCount(room.getUserCount() - 1);
            roomRepository.save(room);
        }

    }



}
