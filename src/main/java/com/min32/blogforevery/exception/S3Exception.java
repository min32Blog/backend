package com.min32.blogforevery.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class S3Exception extends RuntimeException{
    ErrorCode errorCode;
}
