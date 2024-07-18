---

# Hadniva Backend

## Overview
Hadniva Backend is a Spring Boot application designed to manage user authentication, bookings, services, and messages for the Hadniva platform.

## Features
- **User Management**: Allows CRUD operations for managing users.
- **Authentication**: Provides endpoints for user registration and login.
- **Booking Management**: Handles bookings for services.
- **Service Management**: Manages various services offered by Hadniva.
- **Messaging**: Supports sending and receiving messages between users.

## Installation
To run this application locally, follow these steps:

1. **Prerequisites**:
   - Java JDK 11 or higher
   - Maven
   - H2 Database (configured in `application.yml`)
   - To use other databases, make sure to properly configure it in your application.properties or  yml 🍠 

2. **Clone the repository**:
   ```
   git clone https://github.com/Makinyidarling/Pre_HadnivaAppBackend.git
   cd hadniva-backend
   ```

3. **Build and run the application**:
   ```
   mvn spring-boot:run
   ```

4. **Access the application**:
   ```
   http://localhost:8080
   ```

## API Endpoints

### Users
- **GET /users**: Get all users
- **GET /users/{id}**: Get user by ID
- **GET /users/email/{email}**: Get user by email
- **PUT /users**: Update user details
- **DELETE /users/{id}**: Delete user by ID

### Authentication
- **POST /auth/register**: Register a new user
- **POST /auth/login**: Authenticate user login

### Bookings
- **GET /bookings/user/{userId}**: Get bookings by user ID
- **POST /bookings**: Create a new booking
- **PUT /bookings/{id}**: Update booking details
- **DELETE /bookings/{id}**: Delete booking by ID
- **GET /bookings/chart**: Get booking data for charting

### Services
- **GET /services**: Get all services
- **GET /services/{id}**: Get service by ID
- **POST /services**: Create a new service
- **PUT /services/{id}**: Update service details
- **DELETE /services/{id}**: Delete service by ID

### Messages
- **GET /messages/user/{userId}**: Get messages by user ID
- **POST /messages**: Create a new message
- **PUT /messages/{id}**: Update message details
- **DELETE /messages/{id}**: Delete message by ID

## License
This project is licensed under the MIT License - see the LICENSE file for details.

---

Feel free to adjust the sections, add more details, or include specific instructions based on your findings and adjustments.
