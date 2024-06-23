package de.climateguard.application.data.service;

import de.climateguard.application.data.WeatherStation;
import de.climateguard.application.data.repository.WeatherStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherStationService {

    @Autowired
    private WeatherStationRepository weatherStationRepository;

    public WeatherStation save(WeatherStation weatherStation) {
        return weatherStationRepository.save(weatherStation);
    }

    public List<WeatherStation> findAll() {
        return weatherStationRepository.findAll();
    }

    public WeatherStation findById(String id) {
        return weatherStationRepository.findById(id).orElse(null);
    }
}
