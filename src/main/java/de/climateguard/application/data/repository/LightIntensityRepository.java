package de.climateguard.application.data.repository;

import de.climateguard.application.data.LightIntensity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing LightIntensity data.
 * Extends the {@link SensorDataRepository} interface to inherit common data
 * operations.
 */
@Repository
public interface LightIntensityRepository extends SensorDataRepository<LightIntensity> {

    /**
     * Finds a list of LightIntensity records by the specified date.
     *
     * @param date the date to search for
     * @return a list of LightIntensity records for the specified date
     */
    List<LightIntensity> findByDate(LocalDate date);
}
