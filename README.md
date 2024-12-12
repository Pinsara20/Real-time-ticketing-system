# Ticket Management System

This project is a Ticket Management System implemented in Java. It simulates a ticket pool where vendors can add tickets and customers can retrieve them. The system uses multithreading to handle multiple vendors and customers concurrently.

## Features

- **Configuration Management**: Load and save configuration settings from/to a JSON file.
- **Multithreading**: Simultaneously handle multiple vendors and customers.
- **Ticket Pool Management**: Vendors can add tickets to the pool, and customers can retrieve tickets from the pool.

## Project Structure

- `src/Main.java`: The main entry point of the application.
- `src/Configuration.java`: Handles loading and saving configuration settings.
- `src/TicketPool.java`: Manages the ticket pool.
- `src/Vendor.java`: Represents a vendor that adds tickets to the pool.
- `src/Customer.java`: Represents a customer that retrieves tickets from the pool.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- IntelliJ IDEA or any other Java IDE

### Running the Application

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/ticket-management-system.git
    cd ticket-management-system
    ```

2. Open the project in your IDE.

3. Run the `Main` class.

### Configuration

The application uses a configuration file (`configuration.json`) to store settings. If the file exists, the application will prompt the user to use the existing configuration or enter new settings. If the file does not exist, the user will be prompted to enter new settings, which will then be saved to the file.

### User Input

- **Maximum Ticket Pool Capacity**: The maximum number of tickets the pool can hold.
- **Initial Tickets**: The initial number of tickets in the pool.
- **Ticket Release Rate**: The rate at which vendors add tickets to the pool (tickets per second).
- **Customer Retrieval Rate**: The rate at which customers retrieve tickets from the pool (tickets per second).

### Stopping the Application

To stop the application, enter `1` when prompted.

## Classes

### Main

The `Main` class initializes the application, loads the configuration, and starts the vendor and customer threads.

### Configuration

The `Configuration` class handles loading and saving configuration settings from/to a JSON file.

### TicketPool

The `TicketPool` class manages the ticket pool, allowing vendors to add tickets and customers to retrieve tickets.

### Vendor

The `Vendor` class represents a vendor that adds tickets to the pool.

### Customer

The `Customer` class represents a customer that retrieves tickets from the pool.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Acknowledgments

- [Gson](https://github.com/google/gson) for JSON parsing and serialization.

---
