package com.example.vcomp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailsDto {
    private Integer id;
    private Integer orderId;
    private Integer amount;
    private String provider;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}