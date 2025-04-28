package com.example.kcbb2cpayment.services.payment;

import com.example.kcbb2cpayment.models.PaymentRequest;
import com.example.kcbb2cpayment.models.PaymentResponse;
import com.example.kcbb2cpayment.models.PaymentStatusResponse;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);

    PaymentStatusResponse queryPaymentStatus(String transactionId);

}
