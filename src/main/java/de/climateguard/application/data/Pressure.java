package de.climateguard.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pressure")
public class Pressure extends SensorData {
    // Alle Felder und Methoden werden von SensorData geerbt
}
