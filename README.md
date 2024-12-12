
# Ticket Simulation System

This project is a Spring Boot application that simulates ticket generation and retrieval using multi-threading. Vendors generate tickets, and customers retrieve them, while configurations control the simulation parameters. The system supports dynamic configuration and provides real-time updates via WebSocket.

## Features

1. **Simulation Control**:
   - Start and stop simulations dynamically.
   - Multi-threaded vendors and customers interact with a shared ticket pool.
   - This out put show in console

2. **Configuration Management**:
   - Create, retrieve, and manage configurations.
   - Configurations include ticket initialization, release rate, retrieval rate, and max capacity.

3. **Real-Time Updates**:
   - WebSocket integration sends ticket updates to connected clients.

## Components

### 1. Controller
- **`TicketEventController`**: Handles WebSocket interactions.

### 2. Services
- **`ConfigService`**: Manages configuration CRUD operations.
- **`TicketService`**: Controls the ticket simulation lifecycle.

### 3. Service Implementations
- **`ConfigServiceImpl`**: Implements configuration management.
- **`TicketServiceImpl`**: Manages the simulation logic and threads.

### 4. Models
- **`TicketPool`**: Synchronized management of ticket storage.
- **`Vendor`**: Adds tickets to the pool.
- **`Customer`**: Retrieves tickets from the pool.

### 5. Entities
- **`ConfigEntity`**: Database representation of configurations.

### 6. Repositories
- **`ConfigRepository`**: JPA repository for `ConfigEntity`.

## Usage

### Prerequisites
- Java 17+
- Spring Boot 3.0+
- Maven
- WebSocket-compatible client for real-time updates.

### Running the Application
1. Clone the repository:
   ```bash
   git clone <repository_url>
   ```
2. Navigate to the project directory:
   ```bash
   cd ts_backend/tiker
   ```
3. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```

### WebSocket Endpoint
- `/topic/ticketUpdates`: Receives real-time ticket updates.

## Project Structure
- **`controller`**: WebSocket controller.
- **`service`**: Interfaces for configuration and simulation management.
- **`service.impl`**: Implementation of services.
- **`model`**: Core logic for vendors, customers, and ticket pool.
- **`entity`**: JPA entities for database persistence.
- **`repo`**: JPA repositories for data access.

## Future Enhancements
- Add a UI for real-time visualization of ticket updates.
- Extend WebSocket functionality to handle bi-directional client-server communication.
- Add unit and integration tests.

## License
This project is licensed under the MIT License.
