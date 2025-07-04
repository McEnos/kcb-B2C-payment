package com.example.kcbb2cpayment.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        security = @SecurityRequirement(
                name = "bearerAuth"
        )
)
@SecuritySchemes({
        @SecurityScheme(
                name = "bearerAuth",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT"
        )
})
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact().name("KCB TECH TEST").email("kcbtechtest@gmail.com").url("https://kcb-tech.example.org");

        License license = new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html");

        Info info = new Info().contact(contact)
                .license(license).version("1.0")
                .description("B2C payment microservice that was submitted by Joshua Odiwuor Enos").title("KCB TECHNICAL ASSESSMENT");

        return new OpenAPI().info(
                info
        );

    }
}
