package com.example.kcbb2cpayment.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class PaymentRequest {
    @NotBlank
    private String phoneNumber;
    @NotNull
    private BigDecimal amount;
    private String remarks;
}
