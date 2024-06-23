package de.climateguard.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "WeatherStations")
public class WeatherStation {

    @Id
    private String station_id;
    private String name;
    private String description;
    private String mode;

    // Getter und Setter
    public String getStationId() {
        return station_id;
    }

    public void setStationId(String station_id) {
        this.station_id = station_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
