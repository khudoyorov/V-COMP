package com.example.vcomp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
    private String message;
    private boolean success;
    private T data;
}
