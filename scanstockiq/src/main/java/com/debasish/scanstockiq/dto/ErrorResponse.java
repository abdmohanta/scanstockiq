package com.debasish.scanstockiq.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {

    private boolean success;

    private String errorCode;

    private String message;

    private LocalDateTime timestamp;
}