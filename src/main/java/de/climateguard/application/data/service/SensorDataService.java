package de.climateguard.application.data.service;

import de.climateguard.application.data.*;
import de.climateguard.application.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SensorDataService {

    @Autowired
    private AirQualityRepository airQualityRepository;
    @Autowired
    private HumidityRepository humidityRepository;
    @Autowired
    private LightIntensityRepository lightIntensityRepository;
    @Autowired
    private PressureRepository pressureRepository;
    @Autowired
    private RainSensorRepository rainSensorRepository;
    @Autowired
    private TemperatureRepository temperatureRepository;

    public void saveAirQuality(String stationId, double value, LocalDateTime timestamp) {
        AirQuality airQuality = new AirQuality();
        airQuality.setStationId(stationId);
        airQuality.setValue(value);
        airQuality.setDate(timestamp.toLocalDate());
        airQuality.setTime(timestamp.toLocalTime());
        airQualityRepository.save(airQuality);
    }

    public void saveHumidity(String stationId, double value, LocalDateTime timestamp) {
        Humidity humidity = new Humidity();
        humidity.setStationId(stationId);
        humidity.setValue(value);
        humidity.setDate(timestamp.toLocalDate());
        humidity.setTime(timestamp.toLocalTime());
        humidityRepository.save(humidity);
    }

    public void saveLightIntensity(String stationId, double value, LocalDateTime timestamp) {
        LightIntensity lightIntensity = new LightIntensity();
        lightIntensity.setStationId(stationId);
        lightIntensity.setValue(value);
        lightIntensity.setDate(timestamp.toLocalDate());
        lightIntensity.setTime(timestamp.toLocalTime());
        lightIntensityRepository.save(lightIntensity);
    }

    public void savePressure(String stationId, double value, LocalDateTime timestamp) {
        Pressure pressure = new Pressure();
        pressure.setStationId(stationId);
        pressure.setValue(value);
        pressure.setDate(timestamp.toLocalDate());
        pressure.setTime(timestamp.toLocalTime());
        pressureRepository.save(pressure);
    }

    public void saveRainSensor(String stationId, boolean value, LocalDateTime timestamp) {
        RainSensor rainSensor = new RainSensor();
        rainSensor.setStationId(stationId);
        rainSensor.setValue(value);
        rainSensor.setDate(timestamp.toLocalDate());
        rainSensor.setTime(timestamp.toLocalTime());
        rainSensorRepository.save(rainSensor);
    }

    public void saveTemperature(String stationId, double value, LocalDateTime timestamp) {
        Temperature temperature = new Temperature();
        temperature.setStationId(stationId);
        temperature.setValue(value);
        temperature.setDate(timestamp.toLocalDate());
        temperature.setTime(timestamp.toLocalTime());
        temperatureRepository.save(temperature);
    }
}
