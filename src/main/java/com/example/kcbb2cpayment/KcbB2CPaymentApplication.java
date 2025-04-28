package com.example.kcbb2cpayment;

import com.example.kcbb2cpayment.models.User;
import com.example.kcbb2cpayment.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class KcbB2CPaymentApplication implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(KcbB2CPaymentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = User.builder()
                .password(passwordEncoder.encode("1234567"))
                .username("admin")
                .build();
        User savedUser = userRepository.save(user);
        log.info("Saved user: {}", savedUser);
    }
}
