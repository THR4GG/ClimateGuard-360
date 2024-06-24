package de.climateguard.application.data.repository;

import de.climateguard.application.data.RainSensor;
import org.springframework.stereotype.Repository;

@Repository
public interface RainSensorRepository extends SensorDataRepository<RainSensor> {
}
