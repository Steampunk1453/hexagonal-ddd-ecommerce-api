## About
Ecommerce API Backend project to manage the creation of orders for different types of products (stickers, shirts, books...)

Following Hexagonal Architecture (aka Ports and Adapters) / Clean Architecture / DDD / TDD

## Technical details
It implements a Domain / Hexagonal approach of a common web application with Java 16 and Groovy for unit and integration tests

## Tech stack
- Java 16
- Spring Boot
- Groovy
- Spock
- Gradle
- Swagger
- Java records

## Testing project in local environment

- How to run project:
    * You need to install Gradle
    * From the root of project
    * Execute `gradle bootRun`
    * Project uses Swagger for API documentation you can see endpoints info in http://localhost:8080/swagger-ui.html

- How to test project endpoints:
  * Go to: http://localhost:8080/swagger-ui.html
  * Also, you can use cURL client or Postman
  * Steps:
    * Get products: /products {GET}
    * Create order with a previous productId and quantity: /orders {POST}
    * Add product with a previous orderId, previous productId and quantity: /products {POST}
    * The rest of endpoints follow a similar dynamic receiving as a parameter orderId or/and id
  
- How to run project tests:
  * From the root of project
  * Execute `gradle test`

- How to build project:
  * From the root of project
  * Execute `gradle build`
  * Then you can go to /build/libs
  * Execute this command: `java -jar hexagonal-ecommerce-api-0.0.1-SNAPSHOT.jar` to launch project

## Backlog
- [] Add Spring Security with JWT
- [] Implement JPA with H2
- [] Use GitHub Actions 
- [] Add acceptance tests with Cucumber



