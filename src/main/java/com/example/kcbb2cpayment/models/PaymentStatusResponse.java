package com.example.kcbb2cpayment.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentStatusResponse {
    private PaymentStatus status;
    private String message;
}
