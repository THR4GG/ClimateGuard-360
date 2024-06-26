package de.climateguard.application.data.repository;

import de.climateguard.application.data.RainSensor;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing RainSensor data.
 * Extends the {@link SensorDataRepository} interface to inherit common data
 * operations.
 */
@Repository
public interface RainSensorRepository extends SensorDataRepository<RainSensor> {
}
