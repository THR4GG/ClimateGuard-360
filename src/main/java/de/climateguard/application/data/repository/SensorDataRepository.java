package de.climateguard.application.data.repository;

import de.climateguard.application.data.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorDataRepository<T extends SensorData> extends JpaRepository<T, Long> {
}
