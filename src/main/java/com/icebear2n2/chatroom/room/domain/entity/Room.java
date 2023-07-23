package com.icebear2n2.chatroom.room.domain.entity;

import com.icebear2n2.chatroom.chat.domain.entity.Message;
import com.icebear2n2.chatroom.config.domain.entity.UserRoom;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    @ColumnDefault("0")
    private int userCount;
    private int maxUserCount;
    private String password;

    @ColumnDefault("false")
    private boolean isPrivate;

    @OneToMany(mappedBy = "room")
    private List<UserRoom> users;
    @OneToMany(mappedBy = "room")
    private List<Message> messages;

}
