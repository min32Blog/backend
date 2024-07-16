package com.min32.blogforevery.domain.users.service.dto.controllerDTO;

import com.min32.blogforevery.domain.users.service.dto.serviceDTO.RegisterService;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(toBuilder = true)
public record Register(

        @NotNull(message = "이름은 필수 입력 값입니다.")
        String nickname,

        @NotNull(message = "전화번호는 필수 입력 값입니다.")
        String phone,

        @NotNull(message = "이메일는 필수 입력 값입니다.")
        String email,

        @NotNull(message = "비밀번호는 필수 입력 값입니다.")
        String password,

        @NotNull(message = "성별은 필수 입력 값입니다.")
        boolean gender
) {

    public RegisterService toServiceRequest() {
        return RegisterService.builder()
                .email(email)
                .password(password)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
