package com.min32.blogforevery.domain.users.controller.controllerDTO;

import com.min32.blogforevery.domain.users.service.dto.serviceDTO.RegisterService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
                message = "비밀번호는 소문자 1개 이상, 대문자 1개 이상, 숫자 1개 이상, 특수문자 1개 이상, 최소 8글자, 최대 20글자이어야 합니다."
        )
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
