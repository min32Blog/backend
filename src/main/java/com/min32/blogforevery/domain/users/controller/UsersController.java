package com.min32.blogforevery.domain.users.controller;


import com.min32.blogforevery.domain.users.controller.controllerDTO.DeleteUser;
import com.min32.blogforevery.domain.users.controller.controllerDTO.Register;
import com.min32.blogforevery.domain.users.service.dto.serviceDTO.*;
import com.min32.blogforevery.domain.users.service.UserService;
import com.min32.blogforevery.util.UserInfo;
import com.min32.blogforevery.util.UsersInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Long> register (@Valid @RequestBody Register register) {
        userService.registerUser(register.toServiceRequest());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/info")
    public ResponseEntity<UserResponse> userInfo (@UserInfo UsersInfo usersInfo) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserInfo(usersInfo.getId()));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteUser (@UserInfo UsersInfo usersInfo, @Valid @RequestBody DeleteUser deleteUser) {
        userService.deleteUser(deleteUser.toServiceRequest(), usersInfo.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AccessToken> login (@Valid @RequestBody LoginInfo loginInfo) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginInfo));
    }

    @PutMapping("/password")
    public ResponseEntity<Void> updateUserPassword (@Valid @RequestBody Password password,
                                                    @UserInfo UsersInfo usersInfo) {
        userService.updateUserPassword(password, usersInfo.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/info/change")
    public ResponseEntity<Void> updateUserInfo(@Valid @RequestBody UserInfoChange userInfo,
                                               @UserInfo UsersInfo usersInfo) {
        userService.updateUserInfo(userInfo, usersInfo.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
