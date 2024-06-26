package de.climateguard.application.data.repository;

import de.climateguard.application.data.WeatherStation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and managing WeatherStation data.
 * Extends the {@link JpaRepository} interface to provide CRUD operations for
 * entities of type {@link WeatherStation}.
 */
public interface WeatherStationRepository extends JpaRepository<WeatherStation, String> {
}
