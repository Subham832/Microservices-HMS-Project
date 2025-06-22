
# 🏥 Hotel Management System (HMS) – Microservices Architecture

A fully modular, scalable, and cloud-ready **Hotel Management System** built with Java, Spring Boot, and Spring Cloud. This project demonstrates a professional-grade microservices architecture, including **11 services**, a **Netflix Eureka registry**, and a **Spring Cloud Gateway**, designed for real-world deployment.

---

## 🎯 Key Highlights

- Modular microservices with clean separation of concerns
- Centralized discovery using Netflix Eureka
- Secure, unified entry point with Spring Cloud Gateway
- Externalized configurations via Spring Cloud Config
- Robust tech stack: REST, OAuth2/JWT security, Spring Data (MySQL/MongoDB)
- Environment-ready: Docker support available (if configured)
- Swagger for API docs, Postman readiness for integration testing

---

## 🧩 Architecture & Services

```
/
├── api-gateway/         # Routing, authentication, rate limiting
├── config-server/       # Externalized configuration management
├── eureka-server/       # Service registry
├── department-service/  # Room types and facilities
├── hotel-service/    # Core hotel data: profiles, locations
├── booking-service/     # Room booking and reservation management
├── user-service/        # Users, authentication, wallet system
├── payment-service/     # Transaction handling (payments/refunds)
├── inventory-service/   # Room inventory and availability
├── review-service/      # Ratings and reviews
├── notification-service # Email/SMS alerts
├── admin-service/       # Admin and dashboard endpoints
└── README.md
```

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
- **Admin Service**: Handles hotel management operations, admin dashboards, and service monitoring

---

## 🛠️ Technologies Used

| Component             | Technology                          |
|-----------------------|-------------------------------------|
| Backend Framework     | Java 17, Spring Boot, Spring Cloud |
| API Security          | Spring Security, OAuth2, JWT        |
| Service Discovery     | Netflix Eureka                      |
| API Gateway           | Spring Cloud Gateway                |
| Config Management     | Spring Cloud Config                 |
| Databases             | MySQL (JPA/Hibernate), MongoDB      |
| Messaging & Events    | Kafka / RabbitMQ (if integrated)    |
| Payment Gateway        | Stripe                               |
| API Docs              | Swagger/OpenAPI                    |
| Build & Packaging     | Maven                              |
| CI & Containerization | Docker (if Dockerfiles available)   |

---

## 🧪 Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- MySQL & MongoDB instances
- (Optional) Docker for container builds
- Postman or Swagger UI for API testing

### Quick Start

1. **Clone the repo**
   ```bash
   git clone https://github.com/Subham832/Microservices-HMS-Project.git
   cd Microservices-HMS-Project
   ```

2. **Start core infra**
   - Run **Eureka Server**
   - Start **Config Server**
   - Launch **API Gateway**

3. **Launch services**
   ```bash
   cd <each-service-folder>
   mvn spring-boot:run
   ```

4. **Access APIs**
   - Eureka Dashboard: `http://localhost:8761`
   - Swagger UI (if available): `http://localhost:<port>/swagger-ui.html`
   - Gateway Proxy: `http://localhost:8080/<service-endpoint>`

---

## 📄 API Documentation

- Swagger UI is available in services that include Swagger config.
- Postman Collection (optional) can be used for full flow testing:
  - Login → Book Slot → Payment → Review → Notification

---

## ✅ Testing Strategy

- Unit Testing: JUnit, Mockito
- Integration Testing: Spring Boot Test
- End-to-End Testing: Postman Collection (JWT-based APIs)

---

## 🚀 Deployment

> _Add this if you use Docker or CI/CD._

- Docker-based deployment supported via Dockerfiles and Compose (if configured)
- Easily deployable to cloud environments (AWS EC2, ECS, Azure, etc.)

---

## 🛡️ Best Practices Followed

- Layered architecture (Controller → Service → Repository → DTOs)
- JWT-based authentication with role-based access
- Service resilience with proper fallback mechanisms
- RESTful endpoints with proper validations
- Centralized configuration and service registration

---

## 📈 Future Improvements

- Add Kafka/RabbitMQ for async communication
- Introduce circuit breakers (Resilience4j)
- Centralized logging (ELK stack)
- Monitoring with Prometheus + Grafana
- CI/CD automation via GitHub Actions or Jenkins

---

## 🤝 Contributing

Contributions are welcome!

- Fork the repository
- Create a new branch
- Submit a pull request

---

## 📜 License

This project is licensed under the MIT License.  
See the [LICENSE](./LICENSE) file for more information.

---

## 📬 Contact

Project maintained by **Subham Kumar**  
Check more on [GitHub](https://github.com/Subham832)

---
