package de.climateguard.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Represents rain sensor data.
 * This class extends the {@link SensorData} class and includes an additional
 * boolean value to indicate rain presence.
 */
@Entity
@Table(name = "RainSensor")
public class RainSensor extends SensorData {
    private boolean booleanValue;

    /**
     * Gets the value of the rain sensor data as a double.
     * Returns 1.0 if it is raining, otherwise returns 0.0.
     *
     * @return the value of the rain sensor data
     */
    @Override
    public double getValue() {
        return booleanValue ? 1.0 : 0.0;
    }

    /**
     * Gets the boolean value indicating whether it is raining.
     *
     * @return true if it is raining, false otherwise
     */
    public boolean getBooleanValue() {
        return booleanValue;
    }

    /**
     * Sets the boolean value indicating whether it is raining.
     * Also updates the super class's value with the corresponding double
     * representation.
     *
     * @param booleanValue true if it is raining, false otherwise
     */
    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
        super.setValue(booleanValue ? 1.0 : 0.0);
    }
}
