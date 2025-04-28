package com.example.kcbb2cpayment.controller;


import com.example.kcbb2cpayment.models.PaymentRequest;
import com.example.kcbb2cpayment.models.PaymentResponse;
import com.example.kcbb2cpayment.models.PaymentStatusResponse;
import com.example.kcbb2cpayment.services.payment.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(
        name = "Payment controller",
        description = "Endpoints used to perform payment processes"
)
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Initiate payment request",
            description = "An endpoint used to initiate payment request"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Project created successfully."),
            @ApiResponse(responseCode = "404", description = "Provided endpoint is wrong"),
            @ApiResponse(responseCode = "400", description = "Request sent is in wrong format"),
            @ApiResponse(responseCode = "500", description = "Server experienced challenge processing your request")
    })
    @PostMapping("/initiate")
    public ResponseEntity<PaymentResponse> initiatePayment(@RequestBody @Valid PaymentRequest request) {
        PaymentResponse response = paymentService.processPayment(request);
        return ResponseEntity.ok(response);
    }
    @Operation(
            summary = "Fetch transaction status",
            description = "An endpoint used to query transaction status"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Project created successfully."),
            @ApiResponse(responseCode = "404", description = "Provided endpoint is wrong"),
            @ApiResponse(responseCode = "400", description = "Request sent is in wrong format"),
            @ApiResponse(responseCode = "500", description = "Server experienced challenge processing your request")
    })
    @GetMapping("{transactionId}")
    public ResponseEntity<PaymentStatusResponse> getPayment(@PathVariable String transactionId) {
        return ResponseEntity.ok(paymentService.queryPaymentStatus(transactionId));
    }
}
