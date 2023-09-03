package com.example.vcomp.dto;

import lombok.Builder;

@Builder
public class ResponseDto<T> {
    String message;
    T data;
}
