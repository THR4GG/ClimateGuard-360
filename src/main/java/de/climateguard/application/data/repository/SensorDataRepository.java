package de.climateguard.application.data.repository;

import de.climateguard.application.data.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Generic repository interface for accessing and managing sensor data.
 * Extends the {@link JpaRepository} interface to provide CRUD operations for
 * entities of type {@link SensorData}.
 *
 * @param <T> the type of sensor data
 */
public interface SensorDataRepository<T extends SensorData> extends JpaRepository<T, Long> {
}
