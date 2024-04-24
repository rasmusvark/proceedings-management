# Proceedings Management Application

## Description
This application allows users to initiate proceedings against individuals by capturing essential information such as name, personal ID, email address, and the reason for the proceeding. It utilizes Spring Boot for the backend, PostgreSQL for data storage, and RabbitMQ for messaging between microservices.

## Prerequisites
- JDK 17
- Docker
- Docker Compose
- Gradle (Optional, if not using the Gradle wrapper included in the project)

## Setup and Installation
1. **Clone the repository:**git clone https://github.com/rasmusvark/proceedings-management
2. **Navigate to the project directory:**cd proceedings-management
3. **Start the required services using Docker Compose:**docker-compose up -d

## Running the Application
1. **Build the application:**./gradlew build
2. **Run the application:**./gradlew bootRun

## Accessing the Application
- The application can be accessed at `http://localhost:8080/api/proceedings`
- RabbitMQ Management console can be accessed at `http://localhost:15672`

## Stopping the Application
- To stop the application and services: docker-compose down

## Useful Commands
- Check the status of Docker containers: docker ps
- If the application does not start properly, check the Docker container logs for any error messages: docker logs <container_name>

## Contact Information

For any queries, you can reach out at rasmusvark@gmail.com.

