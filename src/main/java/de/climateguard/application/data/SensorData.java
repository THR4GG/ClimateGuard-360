package de.climateguard.application.data;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;

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

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationId() {
        return station_id;
    }

    public void setStationId(String station_id) {
        this.station_id = station_id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setValue(boolean value) {
        this.value = value ? 1.0 : 0.0;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
