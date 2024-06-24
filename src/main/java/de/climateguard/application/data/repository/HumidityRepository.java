package de.climateguard.application.data.repository;

import de.climateguard.application.data.Humidity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface HumidityRepository extends SensorDataRepository<Humidity> {
    List<Humidity> findByDate(LocalDate date);
}
