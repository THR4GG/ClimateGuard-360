package de.climateguard.application.data.repository;

import de.climateguard.application.data.Temperature;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends SensorDataRepository<Temperature> {
    // Zus�tzliche spezialisierte Methoden k�nnen hier hinzugef�gt werden
}
