package de.climateguard.application.data.repository;

import de.climateguard.application.data.Pressure;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PressureRepository extends SensorDataRepository<Pressure> {
    List<Pressure> findByDate(LocalDate date);
}
