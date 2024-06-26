package de.climateguard.application.data.repository;

import de.climateguard.application.data.Pressure;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing Pressure data.
 * Extends the {@link SensorDataRepository} interface to inherit common data
 * operations.
 */
@Repository
public interface PressureRepository extends SensorDataRepository<Pressure> {

    /**
     * Finds a list of Pressure records by the specified date.
     *
     * @param date the date to search for
     * @return a list of Pressure records for the specified date
     */
    List<Pressure> findByDate(LocalDate date);
}
