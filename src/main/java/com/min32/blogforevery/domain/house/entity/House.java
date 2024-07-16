package com.min32.blogforevery.domain.house.entity;

import com.min32.blogforevery.domain.users.entity.Users;
import com.min32.blogforevery.util.BaseTimeEntity;
import com.min32.blogforevery.util.Location;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class House extends  Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
