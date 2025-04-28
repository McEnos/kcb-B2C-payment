# B2C Payment Service

This is a payment microservice that simulates processing of  B2C payments and sends SMS notifications upon success or failure.

## Features
- Initiate B2C payment via REST API
- Integrates mock mobile money APIs
- Send (mock) SMS notifications upon payment success or failure
- OAuth2 secured APIs
- Dockerized deployment
- H2 in-memory database
- Unit Tests

## Setup Instructions

1. Clone the repo
2. Run: `./mvnw clean install`
3. Start: `./mvnw spring-boot:run`
4. For docker deployment: `docker compose up`
5. Swagger documentation: [http://localhost:8080/swagger-ui/index.html]

## Assumptions
- docker engine running locally
- Mocked  APIs and SMS Gateways for simulation.
- Every the application starts, a new user is created in H2 database with username: admin and password: 1234567
   Refer to main class KcbB2CPaymentApplication which runs CommandLineRunner class
- Before making any API call, make sure to call the login endpoint which will generated JWT token.

## Future Enhancements

- Real API integrations
- Actual External SMS gateway integration

