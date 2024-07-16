package com.min32.blogforevery.domain.users.service;

import com.min32.blogforevery.domain.users.entity.Users;
import com.min32.blogforevery.domain.users.repository.UsersRepository;
import com.min32.blogforevery.domain.users.service.dto.serviceDTO.*;
import com.min32.blogforevery.exception.ErrorCode;
import com.min32.blogforevery.exception.UserException;
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
        Users user = findBy(id);

        return UserResponse.of(user);
    }

    // 사용자 삭제
    public void deleteUser(DeleteCheckPassword deleteCheckPassword, Long id) {
        Users user = findBy(id);

        verifyingPassword(deleteCheckPassword.password(), user);
        usersRepository.delete(user);
    }

    // 로그인
    public AccessToken login(LoginInfo loginInfo) {
        Users user = findByEmail(loginInfo);
        verifyingPassword(loginInfo.password(), user);

        String accessToken = tokenGenerator(user);
        return new AccessToken(accessToken);
    }

    public AccessToken freshToken(Long id) {
        Users user = findBy(id);

        String accessToken = tokenGenerator(user);

        //TODO : refreshToken -> Redis or In Feild

        return new AccessToken(accessToken);
    }

    public void updateUserPassword(Password password, Long id) {
        Users user = findBy(id);

        verifyingPassword(password.originPassword(), user);
        user.changePassword(password.changingPassword());
    }

    public void updateUserInfo(UserInfoChange changeUserInfo, Long id) {
        Users user = findBy(id);
        user.updateUserInfo(changeUserInfo);
    }

    private String tokenGenerator(Users user) {
        String accessToken = jwtProvider.createAccessToken(user);
        String refreshToken = jwtProvider.createRefreshToken(user);
        return accessToken;
    }

    private Users findByEmail(LoginInfo loginInfo) {
        return usersRepository.findUsersByEmail(loginInfo.email())
                .orElseThrow(IllegalAccessError::new);
    }


    private Users findBy(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    private void verifyingPassword(String originPassword, Users user) {
        boolean verified = passwordEncoder.matches(originPassword, user.getPassword());
        isNotMathchedPassword(verified);
    }

    private static void isNotMathchedPassword(boolean verified) {
        if(!verified) {
            throw new UserException(ErrorCode.NOT_MATCH_PASSWORD);
        }
    }
}
