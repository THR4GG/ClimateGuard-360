package de.climateguard.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Temperatures")
public class Temperature extends SensorData {
    // Alle Felder und Methoden werden von SensorData geerbt
}