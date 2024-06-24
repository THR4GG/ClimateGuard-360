package de.climateguard.application.data.service;

import org.springframework.stereotype.Service;
import de.climateguard.application.data.*;
import de.climateguard.application.data.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryDataService {

    private final TemperatureRepository temperatureRepository;
    private final HumidityRepository humidityRepository;
    private final PressureRepository pressureRepository;
    private final LightIntensityRepository lightIntensityRepository;
    private final AirQualityRepository airQualityRepository;

    private List<GridItem> items = new ArrayList<>();

    public HistoryDataService(TemperatureRepository temperatureRepository,
            HumidityRepository humidityRepository,
            PressureRepository pressureRepository,
            LightIntensityRepository lightIntensityRepository,
            AirQualityRepository airQualityRepository) {
        this.temperatureRepository = temperatureRepository;
        this.humidityRepository = humidityRepository;
        this.pressureRepository = pressureRepository;
        this.lightIntensityRepository = lightIntensityRepository;
        this.airQualityRepository = airQualityRepository;
    }

    public List<GridItem> getAverageDataForAllTables(LocalDate date) {

        double avgTemperature = temperatureRepository.findByDate(date).stream()
                .collect(Collectors.averagingDouble(Temperature::getValue));
        items.add(new GridItem(date.toString(), "Temperature", avgTemperature));

        double avgHumidity = humidityRepository.findByDate(date).stream()
                .collect(Collectors.averagingDouble(Humidity::getValue));
        items.add(new GridItem(date.toString(), "Humidity", avgHumidity));

        double avgPressure = pressureRepository.findByDate(date).stream()
                .collect(Collectors.averagingDouble(Pressure::getValue));
        items.add(new GridItem(date.toString(), "Pressure", avgPressure));

        double avgLightIntensity = lightIntensityRepository.findByDate(date).stream()
                .collect(Collectors.averagingDouble(LightIntensity::getValue));
        items.add(new GridItem(date.toString(), "Light Intensity", avgLightIntensity));

        double avgAirQuality = airQualityRepository.findByDate(date).stream()
                .collect(Collectors.averagingDouble(AirQuality::getValue));
        items.add(new GridItem(date.toString(), "Air Quality", avgAirQuality));

        return items;
    }

    public List<GridItem> getAverageDataForSpecificTable(String data, LocalDate date) {

        switch (data) {
            case "Temperature":
                double avgTemperature = temperatureRepository.findByDate(date).stream()
                        .collect(Collectors.averagingDouble(Temperature::getValue));
                items.add(new GridItem(date.toString(), data, avgTemperature));
                break;
            case "Humidity":
                double avgHumidity = humidityRepository.findByDate(date).stream()
                        .collect(Collectors.averagingDouble(Humidity::getValue));
                items.add(new GridItem(date.toString(), data, avgHumidity));
                break;
            case "Pressure":
                double avgPressure = pressureRepository.findByDate(date).stream()
                        .collect(Collectors.averagingDouble(Pressure::getValue));
                items.add(new GridItem(date.toString(), data, avgPressure));
                break;
            case "Light Intensity":
                double avgLightIntensity = lightIntensityRepository.findByDate(date).stream()
                        .collect(Collectors.averagingDouble(LightIntensity::getValue));
                items.add(new GridItem(date.toString(), data, avgLightIntensity));
                break;
            case "Air Quality":
                double avgAirQuality = airQualityRepository.findByDate(date).stream()
                        .collect(Collectors.averagingDouble(AirQuality::getValue));
                items.add(new GridItem(date.toString(), data, avgAirQuality));
                break;
        }

        return items;
    }

    public List<GridItem> clearData() {
        items.clear();

        return items;
    }

    public static class GridItem {
        private String date;
        private String data;
        private double average;

        public GridItem(String date, String data, double average) {
            // Adjust date format
            String[] dateParts = date.split("-");
            this.date = dateParts[2] + "." + dateParts[1] + "." + dateParts[0];
            this.data = data;
            this.average = Math.round(average * 100.0) / 100.0;
        }

        public String getDate() {
            return date;
        }

        public String getData() {
            return data;
        }

        public double getAverage() {
            return average;
        }
    }
}
