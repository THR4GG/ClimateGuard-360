package de.climateguard.application.data.repository;

import de.climateguard.application.data.Temperature;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends SensorDataRepository<Temperature> {
    List<Temperature> findByDate(LocalDate date);
}
