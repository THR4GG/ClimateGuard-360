package de.climateguard.application.data.repository;

import de.climateguard.application.data.AirQuality;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface AirQualityRepository extends SensorDataRepository<AirQuality> {
    List<AirQuality> findByDate(LocalDate date);
}
