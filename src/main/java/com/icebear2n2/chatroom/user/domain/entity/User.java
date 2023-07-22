package com.icebear2n2.chatroom.user.domain.entity;

import com.icebear2n2.chatroom.config.domain.entity.UserRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<UserRoom> rooms;
}
