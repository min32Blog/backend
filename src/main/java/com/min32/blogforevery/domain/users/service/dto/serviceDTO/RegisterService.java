package com.min32.blogforevery.domain.users.service.dto.serviceDTO;

import lombok.Builder;

@Builder(toBuilder = true)
public record RegisterService(
        String phone,
        String email,
        String password,

        boolean gender
) {
}
