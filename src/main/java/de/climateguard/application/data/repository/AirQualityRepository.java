package de.climateguard.application.data.repository;

import de.climateguard.application.data.AirQuality;
import org.springframework.stereotype.Repository;

@Repository
public interface AirQualityRepository extends SensorDataRepository<AirQuality> {
    // Zus�tzliche spezialisierte Methoden k�nnen hier hinzugef�gt werden
}
