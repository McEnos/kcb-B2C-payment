package com.example.kcbb2cpayment.services;

import com.example.kcbb2cpayment.models.LoginRequest;
import com.example.kcbb2cpayment.models.LoginResponse;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
}
