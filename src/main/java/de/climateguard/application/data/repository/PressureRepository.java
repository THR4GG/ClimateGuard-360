package de.climateguard.application.data.repository;

import de.climateguard.application.data.Pressure;
import org.springframework.stereotype.Repository;

@Repository
public interface PressureRepository extends SensorDataRepository<Pressure> {
    // Zus�tzliche spezialisierte Methoden k�nnen hier hinzugef�gt werden
}
