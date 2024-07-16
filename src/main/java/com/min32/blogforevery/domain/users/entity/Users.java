package com.min32.blogforevery.domain.users.entity;

import com.min32.blogforevery.domain.house.entity.House;
import com.min32.blogforevery.util.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(toBuilder = true)
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 7)
    private String name;

    @Column(length = 25, unique = true)
    private String email;

    @NotBlank
    @Column(length = 200)
    private String password;

    @NotBlank
    @Column(length = 13, unique = true)
    private String phoneNumber;

    private Boolean gender;

    private String profileImage;

    private LocalDate isDelete;

    private String portfolio;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private House house;
}
