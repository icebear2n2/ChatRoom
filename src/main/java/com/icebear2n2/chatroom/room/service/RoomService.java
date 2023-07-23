package com.icebear2n2.chatroom.room.service;

import com.icebear2n2.chatroom.chat.domain.entity.Message;
import com.icebear2n2.chatroom.chat.repository.MessageRepository;
import com.icebear2n2.chatroom.config.domain.entity.UserRoom;
import com.icebear2n2.chatroom.config.repository.UserRoomRepository;
import com.icebear2n2.chatroom.room.domain.entity.Room;
import com.icebear2n2.chatroom.room.domain.request.RoomRequest;
import com.icebear2n2.chatroom.room.domain.response.RoomResponse;
import com.icebear2n2.chatroom.room.repository.RoomRepository;
import com.icebear2n2.chatroom.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository repository;
    private final UserRoomRepository userRoomRepository;

    private final MessageRepository messageRepository;

    public Room createRoom(RoomRequest request) {
        return repository.save(request.toEntity());
    }

    public List<RoomResponse> findAll() {
        List<Room> all = repository.findAll();
        return all.stream().map(RoomResponse::new).toList();
    }

    public RoomResponse findById(Long roomId) {
        Optional<Room> byId = repository.findById(roomId);
        Room room = byId.orElseThrow(() -> new RuntimeException("NOT FOUND ROOM BY " + roomId));
        return new RoomResponse(room);
    }

    public void deleteRoom(Long roomId) {
        Room room = repository.findById(roomId).orElseThrow(() -> new RuntimeException("NOT FOUND ROOM BY " + roomId));
        // Delete associated UserRoom entries
        List<UserRoom> userRooms = userRoomRepository.findByRoom(room);
        userRoomRepository.deleteAll(userRooms);
        List<Message> messagesToDelete = messageRepository.findByRoom(room);
        messageRepository.deleteAll(messagesToDelete);

        // Delete the room
        repository.deleteById(roomId);
    }


    public boolean confirmPwd(Long roomId, String password) {
//        String pwd = chatRoomMap.get(roomId).getRoomPwd();
        Room room = repository.findById(roomId).orElseThrow(() -> new RuntimeException("NOT FOUND ROOM BY " + roomId));
        return password.equals(room.getPassword());

    }

    public boolean chkRoomUserCnt(Long roomId) {
        Room room = repository.findById(roomId).orElseThrow(() -> new RuntimeException("NOT FOUND ROOM BY " + roomId));


        if (room.getUserCount() + 1 > room.getMaxUserCount()) {
            return false;
        }

        return true;
    }

}
