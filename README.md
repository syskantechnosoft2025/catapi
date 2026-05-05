# Cat API

A Spring Boot RESTful web service for accessing random cat images from [Cataas](https://cataas.com/). This project demonstrates best practices including Test-Driven Development (TDD), Swagger OpenAPI 3.0 documentation, comprehensive JUnit testing, logging, monitoring via Spring Boot Actuator, multi-stage Docker containerization, and GitHub Actions CI/CD pipeline.

## Features

- **RESTful API**: Simple endpoint to fetch random cat images
- **Error Handling**: Global exception handler with structured JSON error responses
- **Documentation**: Swagger OAS3 API documentation
- **Monitoring**: Spring Boot Actuator endpoints for health, metrics, and info
- **Logging**: SLF4J logging with configurable levels
- **Testing**: Comprehensive unit tests with JUnit and Mockito
- **Containerization**: Multi-stage Dockerfile for optimized production builds
- **CI/CD**: GitHub Actions workflow for automated build, test, and deployment

## Prerequisites

- Java 21 or higher
- Maven 3.6+
- Docker (optional, for containerization)

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/syskantechnosoft2025/catapi.git
   cd catapi
   ```

2. Build the project:
   ```bash
   mvn clean compile
   ```

## Usage

### Running Locally

1. Start the application:
   ```bash
   mvn spring-boot:run
   ```

2. The API will be available at `http://localhost:8080`

### API Endpoints

- `GET /api/cat` - Fetch a random cat image (returns JPEG image)
- `GET /api/welcome` - Welcome message
- `GET /actuator/health` - Health check
- `GET /actuator/info` - Application info
- `GET /actuator/metrics` - Application metrics

### API Documentation

Access Swagger UI at: `http://localhost:8080/swagger-ui.html`

## Testing

Run the tests:
```bash
mvn test
```

## Docker

### Build the Docker Image

```bash
docker build -t catapi .
```

### Run the Container

```bash
docker run -p 8080:8080 catapi
```

## CI/CD

The project includes a GitHub Actions workflow that:
- Builds the project on every push and pull request
- Runs unit tests
- Builds and pushes Docker images to GitHub Container Registry

## Project Structure

```
src/
├── main/java/com/example/catapi/
│   ├── CatapiApplication.java          # Main Spring Boot application
│   ├── CatController.java              # REST controller
│   ├── CatService.java                 # Business logic service
│   ├── AppConfig.java                  # Configuration beans
│   ├── GlobalExceptionHandler.java     # Global error handling
│   ├── CatApiException.java            # Custom exception
│   └── ApiErrorResponse.java           # Error response model
├── main/resources/
│   └── application.properties          # Application configuration
└── test/java/com/example/catapi/
    ├── CatControllerTest.java          # Controller unit tests
    └── CatServiceTest.java             # Service unit tests
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.