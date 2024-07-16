package com.min32.blogforevery.domain.users.entity;

import com.min32.blogforevery.domain.house.entity.House;
import com.min32.blogforevery.domain.users.service.dto.serviceDTO.UserInfoChange;
import com.min32.blogforevery.util.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder(toBuilder = true)
    public Users(Long id, String name, String email, String password, String phoneNumber, Boolean gender, String profileImage, LocalDate isDelete, String portfolio, House house) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.profileImage = profileImage;
        this.isDelete = isDelete;
        this.portfolio = portfolio;
        this.house = house;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void updateUserInfo(UserInfoChange changeUserInfo) {
        this.email = changeUserInfo.email();
        this.phoneNumber = changeUserInfo.phoneNumber();
    }
}
