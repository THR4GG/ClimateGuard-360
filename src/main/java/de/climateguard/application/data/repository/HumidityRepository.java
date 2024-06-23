package de.climateguard.application.data.repository;

import de.climateguard.application.data.Humidity;
import org.springframework.stereotype.Repository;

@Repository
public interface HumidityRepository extends SensorDataRepository<Humidity> {
    // Zus�tzliche spezialisierte Methoden k�nnen hier hinzugef�gt werden
}
