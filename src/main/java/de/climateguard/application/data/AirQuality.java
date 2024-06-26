package de.climateguard.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Represents air quality sensor data.
 * This class extends the {@link SensorData} class to inherit common sensor data
 * properties.
 */
@Entity
@Table(name = "AirQuality")
public class AirQuality extends SensorData {

    /**
     * Gets the value of the air quality sensor data.
     *
     * @return the value of the air quality sensor data
     */
    @Override
    public double getValue() {
        return super.getValue();
    }

    /**
     * Sets the value of the air quality sensor data.
     *
     * @param value the value to set
     */
    @Override
    public void setValue(double value) {
        super.setValue(value);
    }
}
