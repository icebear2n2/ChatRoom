package com.icebear2n2.chatroom.room.domain.entity;

import com.icebear2n2.chatroom.chat.domain.entity.Message;
import com.icebear2n2.chatroom.config.domain.entity.UserRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    private int userCount;
    private int maxUserCount;
    private String password;
    private boolean isPrivate;

    @OneToMany(mappedBy = "room")
    private List<UserRoom> users;
    @OneToMany(mappedBy = "room")
    private List<Message> messages;

}
