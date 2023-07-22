package com.icebear2n2.chatroom.chat.domain.entity;

import com.icebear2n2.chatroom.room.domain.entity.Room;
import com.icebear2n2.chatroom.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Room room;

    @ManyToOne
    private User sender;

    private String content;

    private LocalDateTime sendAt;
}
