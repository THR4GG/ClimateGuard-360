package de.climateguard.application.data.repository;

import de.climateguard.application.data.LightIntensity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface LightIntensityRepository extends SensorDataRepository<LightIntensity> {
    List<LightIntensity> findByDate(LocalDate date);
}
