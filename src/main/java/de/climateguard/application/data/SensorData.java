package de.climateguard.application.data;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;

/**
 * Abstract base class for sensor data entities.
 * This class defines common properties and behaviors for different types of
 * sensor data.
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "sensor_type")
public abstract class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String station_id;
    private double value;
    private LocalDate date;
    private LocalTime time;

    /**
     * Gets the ID of the sensor data.
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the sensor data.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the station ID associated with the sensor data.
     *
     * @return the station ID
     */
    public String getStationId() {
        return station_id;
    }

    /**
     * Sets the station ID associated with the sensor data.
     *
     * @param station_id the station ID to set
     */
    public void setStationId(String station_id) {
        this.station_id = station_id;
    }

    /**
     * Gets the value of the sensor data.
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the value of the sensor data.
     *
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Sets the value of the sensor data as a boolean.
     * The value is converted to a double where true is 1.0 and false is 0.0.
     *
     * @param value the boolean value to set
     */
    public void setValue(boolean value) {
        this.value = value ? 1.0 : 0.0;
    }

    /**
     * Gets the date when the sensor data was recorded.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date when the sensor data was recorded.
     *
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the time when the sensor data was recorded.
     *
     * @return the time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Sets the time when the sensor data was recorded.
     *
     * @param time the time to set
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }
}
