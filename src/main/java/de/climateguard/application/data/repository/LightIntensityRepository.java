package de.climateguard.application.data.repository;

import de.climateguard.application.data.LightIntensity;
import org.springframework.stereotype.Repository;

@Repository
public interface LightIntensityRepository extends SensorDataRepository<LightIntensity> {
    // Zus�tzliche spezialisierte Methoden k�nnen hier hinzugef�gt werden
}
