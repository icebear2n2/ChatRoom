package com.icebear2n2.chatroom.config.domain.entity;

import com.icebear2n2.chatroom.room.domain.entity.Room;
import com.icebear2n2.chatroom.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_room")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Room room;
}
