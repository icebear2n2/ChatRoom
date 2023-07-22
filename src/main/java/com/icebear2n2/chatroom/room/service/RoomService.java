package com.icebear2n2.chatroom.room.service;

import com.icebear2n2.chatroom.room.domain.entity.Room;
import com.icebear2n2.chatroom.room.domain.request.RoomRequest;
import com.icebear2n2.chatroom.room.domain.response.RoomResponse;
import com.icebear2n2.chatroom.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository repository;

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
       repository.deleteById(roomId);
    }
}
