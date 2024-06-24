package de.climateguard.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "RainSensor")
public class RainSensor extends SensorData {
    private boolean booleanValue;

    @Override
    public double getValue() {
        return booleanValue ? 1.0 : 0.0;
    }

    public boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
        super.setValue(booleanValue ? 1.0 : 0.0);
    }
}
