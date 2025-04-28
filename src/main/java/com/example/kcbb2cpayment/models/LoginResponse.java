package com.example.kcbb2cpayment.models;

import lombok.Builder;

@Builder
public record LoginResponse(String accessToken,String expiresIn) {
}
