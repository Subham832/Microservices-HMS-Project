
# 🏥 Hotel Management System (HMS) – Microservices Architecture

A fully modular, scalable, and cloud-ready Hotel Management System built with Java, Spring Boot, and Spring Cloud. This project demonstrates a professional-grade microservices architecture, including 11 services, a Netflix Eureka registry, a Spring Cloud Gateway, and distributed tracing with Zipkin, designed for real-world deployment.

---

## 🎯 Key Highlights

- Modular microservices with clean separation of concerns
- Centralized discovery using Netflix Eureka
- Secure, unified entry point with Spring Cloud Gateway
- Externalized configurations via Spring Cloud Config
- Robust tech stack: REST, OAuth2/JWT security, Spring Data (MySQL/MongoDB)
- Kafka-based event-driven communication (e.g., payment, notifications)
- Distributed tracing with Zipkin
- Swagger for API docs, Postman readiness for integration testing
- Docker and basic Kubernetes/EKS support

---

## 🧩 Architecture & Services
/
├── api-gateway/              # Routing, authentication, rate limiting
├── config-server/            # Externalized configuration management
├── eureka-server/            # Service registry
├── department-service/       # Room types and facilities
├── hotel-service/            # Core hotel data: profiles, locations
├── booking-service/          # Room booking and reservation management
├── user-service/             # Users, authentication, wallet system
├── payment-service/          # Transaction handling (payments/refunds)
├── inventory-service/        # Room inventory and availability
├── review-service/           # Ratings and reviews
├── notification-service      # Email/SMS alerts
├── admin-service/            # Admin and dashboard endpoints
├── zipkin-server/            # Distributed tracing and latency analysis
└── README.md

### Microservice Responsibilities

- **API Gateway**: Entry point, routing, JWT validation
- **Config Server**: Central management of `application-{env}.yml`
- **Eureka Server**: Dynamic discovery for all services
- **Department Service**: Manages room types, facilities, and hotel services
- **Hotel Service**: Manages hotel metadata and hotel branches and locations
- **Booking Service**: Room availability, slot booking, booking history
- **User Service**: Authentication, user profiles, wallet & balances
- **Payment Service**: Handles charges, refunds, transaction records
- **Inventory Service**: Tracks room stock and availability
- **Review Service**: Ratings and textual feedback from guests
- **Notification Service**: Sends real-time Email/SMS alerts
- **Zipkin Server**: Enables distributed tracing across microservices for performance monitoring and debugging
- **Admin Service**: Handles hotel management operations, admin dashboards, and service monitoring

---

## 🛠️ Technologies Used

| Component             | Technology                                           |
|-----------------------|------------------------------------------------------|
| Backend Framework     | Java 17, Spring Boot, Spring Cloud                   |
| API Security          | Spring Security, OAuth2, JWT                         |
| Service Discovery     | Netflix Eureka                                       |
| API Gateway           | Spring Cloud Gateway                                 |
| Config Management     | Spring Cloud Config                                  |
| Databases             | MySQL (JPA/Hibernate), MongoDB                       |
| Messaging & Events    | Kafka (used in Payment and Notification services)    |
| Payment Gateway       | Stripe                                               |
| API Docs              | Swagger/OpenAPI                                      |
| Build & Packaging     | Maven                                                |
| CI & Containerization | Docker                                               |
| Observability         | Zipkin, Spring Cloud Sleuth                          |

---

## 🧪 Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- MySQL & MongoDB instances
- Docker for container builds
- Postman or Swagger UI for API testing

### Quick Start

1. **Clone the repo**
   ```bash
   git clone https://github.com/Subham832/Microservices-HMS-Project.git
   cd Microservices-HMS-Project

 2. Start core infra
   - Run Eureka Server
   - Start Config Server
   - Launch API Gateway

3. Launch services
   ```bash
   cd <each-service-folder>
   mvn spring-boot:run
   ```

4. **Access APIs**
   - Eureka Dashboard: http://localhost:8761
   - Swagger UI: http://localhost:<port>/swagger-ui.html
   - Gateway Proxy: http://localhost:8080/<service-endpoint>

---
