package com.example.kcbb2cpayment.services.payment;

import com.example.kcbb2cpayment.exceptions.PaymentFailedException;
import com.example.kcbb2cpayment.models.PaymentRequest;
import com.example.kcbb2cpayment.models.PaymentResponse;
import com.example.kcbb2cpayment.models.PaymentStatus;
import com.example.kcbb2cpayment.models.PaymentStatusResponse;
import com.example.kcbb2cpayment.services.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MpesaPaymentService implements PaymentService {
    private final NotificationService notificationService;

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        /**
         * Simulating a random boolean response coming from an external API call
         */
        boolean success = new Random().nextBoolean();
        if (!success) {
            notificationService.sendSms(request.getPhoneNumber(), "Payment failed.");
            throw new PaymentFailedException("Payment processing failed.");
        }

        String transactionId = UUID.randomUUID().toString();
        notificationService.sendSms(request.getPhoneNumber(), "Payment successful. Transaction ID: " + transactionId);

        return PaymentResponse.builder()
                .transactionId(transactionId)
                .status(PaymentStatus.SUCCEEDED)
                .message("Payment completed successfully.")
                .build();
    }

    @Override
    public PaymentStatusResponse queryPaymentStatus(String transactionId) {
        PaymentStatus status = getPaymentStatus(transactionId);
        return switch (status) {
            case SUCCEEDED -> PaymentStatusResponse.builder()
                    .status(PaymentStatus.SUCCEEDED)
                    .message("Payment completed successfully.")
                    .build();
            case FAILED -> PaymentStatusResponse.builder()
                    .status(PaymentStatus.FAILED)
                    .message("Payment failed.")
                    .build();
            case CANCELLED -> PaymentStatusResponse.builder()
                    .status(PaymentStatus.CANCELLED)
                    .message("Payment cancelled.")
                    .build();
            default -> PaymentStatusResponse.builder()
                    .status(PaymentStatus.PENDING)
                    .message("Payment pending.")
                    .build();
        };
    }

    /**
     * A method to simulate external API call
     */
    private PaymentStatus getPaymentStatus(String transactionId) {
        return PaymentStatus.FAILED;
    }


}
