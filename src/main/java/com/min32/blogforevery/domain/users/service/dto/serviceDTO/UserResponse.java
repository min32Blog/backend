package com.min32.blogforevery.domain.users.service.dto.serviceDTO;

import com.min32.blogforevery.domain.users.entity.Users;
import lombok.Builder;

@Builder(toBuilder = true)
public record UserResponse(
      String nickname,
      String phone,
      boolean gender
){

    public static UserResponse of (Users user) {
        return UserResponse.builder()
                .phone(user.getPhoneNumber())
                .gender(user.getGender())
                .build();
    }
}
