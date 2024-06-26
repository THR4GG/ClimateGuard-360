package de.climateguard.application.data.repository;

import de.climateguard.application.data.Temperature;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing Temperature data.
 * Extends the {@link SensorDataRepository} interface to inherit common data
 * operations.
 */
@Repository
public interface TemperatureRepository extends SensorDataRepository<Temperature> {

    /**
     * Finds a list of Temperature records by the specified date.
     *
     * @param date the date to search for
     * @return a list of Temperature records for the specified date
     */
    List<Temperature> findByDate(LocalDate date);
}
