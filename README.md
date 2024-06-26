# ClimateGuard-360

ClimateGuard-360 is an advanced environmental monitoring system that integrates a web interface with an ESP32 microcontroller for real-time data acquisition. The system utilizes MQTT for data communication and MySQL for data storage, with Docker facilitating the deployment of both the MQTT broker and the MySQL database.

## System Overview

- **Web Interface (Vaadin/Spring Boot)**: Offers a dashboard for monitoring and managing environmental data.
- **ESP32 Microcontroller**: Handles real-time data collection and communicates with the server via MQTT.
- **MySQL Database**: Stores sensor data and device information, allowing for historical data analysis and device management.
- **MQTT Broker**: Deployed within a Docker container, it handles messaging between the ESP32 devices and the web server.

## Repository Links

- **Web Interface Code**: [ClimateGuard-360](https://github.com/THR4GG/ClimateGuard-360)
- **ESP32 Firmware**: [ClimateGuard-360-ESP32](https://github.com/THR4GG/ClimateGuard-360-ESP32)

## Running the Web Application

To get the ClimateGuard-360 web interface up and running:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/THR4GG/ClimateGuard-360.git
   ```
2. **Navigate to the project directory:**
   ```bash
   cd ClimateGuard-360
   ```
3. **Start the application using Maven:**
   ```bash
   mvnw (Windows) or ./mvnw (Mac & Linux)
   ```
4. **Access the application:**
   Open `http://localhost:5000` in your browser.

## Configuring MySQL and MQTT

- **MySQL**: Ensure MySQL is set up and running. Configure connection settings in `application.properties`.
- **MQTT Broker**: Deployed via Docker, ensure the broker is configured correctly for the ESP32 to connect.

## Docker Deployment

To build and deploy using Docker, which includes your MQTT broker and MySQL database:

1. **Build the Docker image:**
   ```bash
   docker build . -t climateguard:latest
   ```
2. **Run the Docker container:**
   ```bash
   docker run -p 5000:5000 climateguard:latest
   ```

## Contributing

Contributions to improve the system, whether through enhancing the interface, optimizing the backend, or refining the ESP32 integration, are welcome.

## Additional Resources

- **[Vaadin Documentation](https://vaadin.com/docs)**
- **[MQTT.org](https://mqtt.org/)** for details on MQTT protocol.

## License

This project is licensed under the Unlicense, allowing free use, modification, and distribution.