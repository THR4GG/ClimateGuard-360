package de.climateguard.application.data.repository;

import de.climateguard.application.data.Humidity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing Humidity data.
 * Extends the {@link SensorDataRepository} interface to inherit common data
 * operations.
 */
@Repository
public interface HumidityRepository extends SensorDataRepository<Humidity> {

    /**
     * Finds a list of Humidity records by the specified date.
     *
     * @param date the date to search for
     * @return a list of Humidity records for the specified date
     */
    List<Humidity> findByDate(LocalDate date);
}
