package de.climateguard.application.data.repository;

import de.climateguard.application.data.WeatherStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherStationRepository extends JpaRepository<WeatherStation, String> {
}
