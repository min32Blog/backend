package com.min32.blogforevery.domain.users.service;

import com.min32.blogforevery.domain.users.entity.Users;
import com.min32.blogforevery.domain.users.repository.UsersRepository;
import com.min32.blogforevery.domain.users.service.dto.serviceDTO.*;
import com.min32.blogforevery.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    // 저장
    public void registerUser (RegisterService registerService) {

        Users user = Users.builder()
                .email(registerService.email())
                .password(passwordEncoder.encode(registerService.password()))
                .phoneNumber(registerService.phone())
                .gender(registerService.gender())
                .build();

        usersRepository.save(user);
    }

    // 단건 조회
    public UserResponse getUserInfo (Long id){
        Users user = usersRepository.findById(id)
                        .orElseThrow(IllegalArgumentException::new);

        return UserResponse.of(user);
    }

    // 사용자 삭제
    public void deleteUser(Long id) {
        Users user = usersRepository.findById(id)
                        .orElseThrow(IllegalArgumentException::new);

        usersRepository.delete(user);
    }

    // 로그인
    public AccessToken login(LoginInfo loginInfo) {
        Users user = usersRepository.findUsersByEmail(loginInfo.email())
                .orElseThrow(IllegalAccessError::new);

        boolean verified = passwordEncoder.matches(loginInfo.password(), user.getPassword());
        if(!verified) {
            throw new Error();
        }

        String accessToken = jwtProvider.createAccessToken(user);
        String refreshToken = jwtProvider.createRefreshToken(user);
        return new AccessToken(accessToken);
    }

    public void updateUserPassword(Password password, Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        boolean verified = passwordEncoder.matches(password.originPassword(), user.getPassword());
        if(!verified) {
            throw new Error();
        }

    }

    public void updateUserInfo(UserInfoChange userInfoChange, Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public AccessToken freshToken(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        String accessToken = jwtProvider.createAccessToken(user);
        String refreshToken = jwtProvider.createRefreshToken(user);
        return new AccessToken(accessToken);
    }

}
