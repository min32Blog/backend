package com.min32.blogforevery.domain.users.controller.controllerDTO;

import com.min32.blogforevery.domain.users.service.dto.serviceDTO.DeleteCheckPassword;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder(toBuilder = true)
public record DeleteUser (

        @NotNull(message = "비밀번호 입력은 필수입니다.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
                message = "비밀번호는 소문자 1개 이상, 대문자 1개 이상, 숫자 1개 이상, 특수문자 1개 이상, 최소 8글자, 최대 20글자이어야 합니다."
        )
        String password
) {

    public DeleteCheckPassword toServiceRequest() {
        return DeleteCheckPassword.builder()
                .password(password)
                .build();
    }
}
