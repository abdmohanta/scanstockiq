package com.debasish.scanstockiq.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SuccessResponse<T> {

    private boolean success;

    private String message;

    private T data;
}