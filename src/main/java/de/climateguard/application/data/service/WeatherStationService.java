package de.climateguard.application.data.service;

import de.climateguard.application.data.WeatherStation;
import de.climateguard.application.data.repository.WeatherStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing WeatherStation entities.
 */
@Service
public class WeatherStationService {

    @Autowired
    private WeatherStationRepository weatherStationRepository;

    /**
     * Saves a WeatherStation entity to the repository.
     *
     * @param weatherStation the WeatherStation entity to save
     * @return the saved WeatherStation entity
     */
    public WeatherStation save(WeatherStation weatherStation) {
        return weatherStationRepository.save(weatherStation);
    }

    /**
     * Retrieves all WeatherStation entities from the repository.
     *
     * @return a list of all WeatherStation entities
     */
    public List<WeatherStation> findAll() {
        return weatherStationRepository.findAll();
    }

    /**
     * Finds a WeatherStation entity by its ID.
     *
     * @param id the ID of the WeatherStation entity
     * @return the found WeatherStation entity, or null if not found
     */
    public WeatherStation findById(String id) {
        return weatherStationRepository.findById(id).orElse(null);
    }
}
