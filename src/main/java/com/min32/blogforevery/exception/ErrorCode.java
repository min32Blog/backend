package com.min32.blogforevery.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    EMPTY_FILE_EXCEPTION(HttpStatus.NOT_FOUND, "ACCOUNT-001", "사용자를 찾을 수 없습니다."),
    NO_FILE_EXTENTION(HttpStatus.NOT_FOUND, "ACCOUNT-002", "사용자를 찾을 수 없습니다."),
    INVALID_FILE_EXTENTION(HttpStatus.NOT_FOUND, "ACCOUNT-003", "사용자를 찾을 수 없습니다."),
    PUT_OBJECT_EXCEPTION(HttpStatus.NOT_FOUND, "ACCOUNT-004", "사용자를 찾을 수 없습니다."),
    IO_EXCEPTION_ON_IMAGE_DELETE(HttpStatus.NOT_FOUND, "ACCOUNT-005", "사용자를 찾을 수 없습니다."),
    IO_EXCEPTION_ON_IMAGE_UPLOAD(HttpStatus.NOT_FOUND, "ACCOUNT-006", "사용자를 찾을 수 없습니다."),
    // User
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "USER-001", "비밀번호가 일치하지 않습니다. ")

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
