package com.min32.blogforevery.domain.users.repository;

import com.min32.blogforevery.domain.users.entity.Gender;
import com.min32.blogforevery.domain.users.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@SpringBootTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void findUsersByEmail() {
        // given
        Users user = Users.builder()
                .email("email.01@naver.com")
                .gender(Gender.MAN)
                .name("name")
                .phoneNumber("010-5129-1823")
                .password("password1234")
                .build();

        usersRepository.save(user);

        // when
        Users findUser = usersRepository.findUsersByEmail(user.getEmail()).get();

        // then
        assertThat(findUser).extracting("email", "gender", "name", "phoneNumber")
                .containsExactlyInAnyOrder(user.getEmail(), user.getGender(), user.getName(), user.getPhoneNumber());
    }
}