package com.example.kcbb2cpayment.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorDetails {
    private String message;
    private String details;
    private LocalDateTime timestamp;
}
