package com.example.kcbb2cpayment.services.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TwilioSmsService implements NotificationService {
    @Override
    public void sendSms(String phoneNumber, String message) {
        log.info("Sending notification to client with phone number {}", phoneNumber);
    }
}
