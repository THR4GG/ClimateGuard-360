package de.climateguard.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Represents temperature sensor data.
 * This class extends the {@link SensorData} class to inherit common sensor data
 * properties.
 */
@Entity
@Table(name = "Temperature")
public class Temperature extends SensorData {
    // All fields and methods are inherited from SensorData
}
