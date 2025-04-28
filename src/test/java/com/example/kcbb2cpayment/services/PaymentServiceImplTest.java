package com.example.kcbb2cpayment.services;

import com.example.kcbb2cpayment.exceptions.PaymentFailedException;
import com.example.kcbb2cpayment.models.PaymentRequest;
import com.example.kcbb2cpayment.models.PaymentResponse;
import com.example.kcbb2cpayment.models.PaymentStatusResponse;
import com.example.kcbb2cpayment.services.notification.NotificationService;
import com.example.kcbb2cpayment.services.payment.MpesaPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class PaymentServiceImplTest {
    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private MpesaPaymentService mpesaPaymentService;

    private PaymentRequest paymentRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentRequest =  PaymentRequest.builder()
                .remarks("remarks")
                .phoneNumber("254700000000")
                .amount(BigDecimal.valueOf(100.00))
                .build();
    }

    @Test
    void testProcessPayment_Success() {
        PaymentResponse paymentResponse = mpesaPaymentService.processPayment(paymentRequest);
        verify(notificationService).sendSms(paymentRequest.getPhoneNumber(), "Payment successful. Transaction ID: " + paymentResponse.getTransactionId());
        assertEquals("SUCCEEDED", paymentResponse.getStatus().name());
        assertNotNull(paymentResponse.getTransactionId());
        assertEquals("Payment completed successfully.", paymentResponse.getMessage());
    }

    @Test
    void testProcessPayment_Failure() {
        doThrow(new PaymentFailedException("Payment processing failed."))
                .when(notificationService).sendSms(paymentRequest.getPhoneNumber(), "Payment failed.");

        PaymentFailedException thrown = assertThrows(PaymentFailedException.class, () -> {
            mpesaPaymentService.processPayment(paymentRequest);
        });

        verify(notificationService).sendSms(paymentRequest.getPhoneNumber(), "Payment failed.");

        assertEquals("Payment processing failed.", thrown.getMessage());
    }

    @Test
    void testQueryPaymentStatus_Succeeded() {
        PaymentStatusResponse response = mpesaPaymentService.queryPaymentStatus(UUID.randomUUID().toString());
        assertEquals("SUCCEEDED", response.getStatus().name());
        assertEquals("Payment completed successfully.", response.getMessage());
    }

    @Test
    void testQueryPaymentStatus_Failed() {
        PaymentStatusResponse response = mpesaPaymentService.queryPaymentStatus(UUID.randomUUID().toString());
        assertEquals("FAILED", response.getStatus().name());
        assertEquals("Payment failed.", response.getMessage());
    }

    @Test
    void testQueryPaymentStatus_Cancelled() {
        PaymentStatusResponse response = mpesaPaymentService.queryPaymentStatus(UUID.randomUUID().toString());
        assertEquals("CANCELLED", response.getStatus().name());
        assertEquals("Payment cancelled.", response.getMessage());
    }

    @Test
    void testQueryPaymentStatus_Pending() {
        PaymentStatusResponse response = mpesaPaymentService.queryPaymentStatus(UUID.randomUUID()
                .toString());
        assertEquals("PENDING", response.getStatus().name());
        assertEquals("Payment pending.", response.getMessage());
    }
}