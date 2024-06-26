package de.climateguard.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a weather station entity.
 */
@Entity
@Table(name = "WeatherStations")
public class WeatherStation {

    @Id
    private String station_id;
    private String name;
    private String description;
    private String mode;

    /**
     * Gets the station ID of the weather station.
     *
     * @return the station ID
     */
    public String getStationId() {
        return station_id;
    }

    /**
     * Sets the station ID of the weather station.
     *
     * @param station_id the station ID to set
     */
    public void setStationId(String station_id) {
        this.station_id = station_id;
    }

    /**
     * Gets the name of the weather station.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the weather station.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the weather station.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the weather station.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the mode of the weather station.
     *
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * Sets the mode of the weather station.
     *
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }
}
