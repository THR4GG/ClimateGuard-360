package de.climateguard.application.data.repository;

import de.climateguard.application.data.AirQuality;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing AirQuality data.
 * Extends the {@link SensorDataRepository} interface to inherit common data
 * operations.
 */
@Repository
public interface AirQualityRepository extends SensorDataRepository<AirQuality> {

    /**
     * Finds a list of AirQuality records by the specified date.
     *
     * @param date the date to search for
     * @return a list of AirQuality records for the specified date
     */
    List<AirQuality> findByDate(LocalDate date);
}
