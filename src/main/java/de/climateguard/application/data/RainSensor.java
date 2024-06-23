package de.climateguard.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "RainSensor")
public class RainSensor extends SensorData {
    private boolean value;

    @Override
    public double getValue() {
        // R�ckgabe 1.0 f�r true und 0.0 f�r false, um die alue-Methode zu �berschreiben
        return value ? 1.0 : 0.0;
    }

    public boolean getBooleanValue() {
        return value;
    }

    @Override
    public void setValue(boolean value) {
        this.value = value;
    }

    public void setBooleanValue(boolean value) {
        this.value = value;
    }
}
