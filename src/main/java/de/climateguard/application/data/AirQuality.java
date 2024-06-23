package de.climateguard.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "AirQuality")
public class AirQuality extends SensorData {
    // �ndern des alue Typs auf double (Decimal)
    @Override
    public double getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(double value) {
        super.setValue(value);
    }
}
