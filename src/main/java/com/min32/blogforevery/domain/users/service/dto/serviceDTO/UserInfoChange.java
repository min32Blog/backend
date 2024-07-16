package com.min32.blogforevery.domain.users.service.dto.serviceDTO;

import lombok.Builder;

@Builder(toBuilder = true)
public record UserInfoChange(
    String phoneNumber,
    String email
) {
}
