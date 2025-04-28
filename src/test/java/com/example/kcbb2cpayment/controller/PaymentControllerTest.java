package com.example.kcbb2cpayment.controller;

import com.example.kcbb2cpayment.models.PaymentRequest;
import com.example.kcbb2cpayment.models.PaymentResponse;
import com.example.kcbb2cpayment.models.PaymentStatus;
import com.example.kcbb2cpayment.models.PaymentStatusResponse;
import com.example.kcbb2cpayment.services.payment.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testInitiatePayment() throws Exception {
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .amount(BigDecimal.valueOf(10000))
                .remarks("test remarks")
                .build();
        PaymentResponse paymentResponse = PaymentResponse.builder()
                .status(PaymentStatus.SUCCEEDED)
                .message("Payment successfully")
                .build();

        when(paymentService.processPayment(any(PaymentRequest.class))).thenReturn(paymentResponse);

        mockMvc.perform(post("/api/v1/payment/initiate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paymentRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value("txn12345"))
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void testGetPaymentStatus() throws Exception {
        String transactionId = "txn12345";
        PaymentStatusResponse paymentStatusResponse = PaymentStatusResponse.builder()
                .status(PaymentStatus.SUCCEEDED)
                .message("Payment successful")
                .build();

        when(paymentService.queryPaymentStatus(transactionId)).thenReturn(paymentStatusResponse);

        mockMvc.perform(get("/api/v1/payment/{transactionId}", transactionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value(transactionId))
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

}