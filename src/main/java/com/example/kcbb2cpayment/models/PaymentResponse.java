package com.example.kcbb2cpayment.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentResponse {
    private String transactionId;
    private PaymentStatus status;
    private String message;
}
