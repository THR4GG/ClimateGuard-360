package de.climateguard.application.data.service;

import org.springframework.stereotype.Service;
import de.climateguard.application.data.*;
import de.climateguard.application.data.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for retrieving and processing historical sensor data.
 */
@Service
public class HistoryDataService {

    private final TemperatureRepository temperatureRepository;
    private final HumidityRepository humidityRepository;
    private final PressureRepository pressureRepository;
    private final LightIntensityRepository lightIntensityRepository;
    private final AirQualityRepository airQualityRepository;

    private List<GridItem> items = new ArrayList<>();

    /**
     * Constructs a new HistoryDataService.
     *
     * @param temperatureRepository    the repository for temperature data
     * @param humidityRepository       the repository for humidity data
     * @param pressureRepository       the repository for pressure data
     * @param lightIntensityRepository the repository for light intensity data
     * @param airQualityRepository     the repository for air quality data
     */
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

    /**
     * Retrieves the average data for all sensor types on a specific date.
     *
     * @param date the date to retrieve the data for
     * @return a list of GridItem objects containing the average data
     */
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

    /**
     * Retrieves the average data for a specific sensor type on a specific date.
     *
     * @param data the type of sensor data to retrieve
     * @param date the date to retrieve the data for
     * @return a list of GridItem objects containing the average data
     */
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
            default:
                System.err.println("Unknown table name: " + data);
                break;
        }

        return items;
    }

    /**
     * Clears the current data.
     *
     * @return an empty list of GridItem objects
     */
    public List<GridItem> clearData() {
        items.clear();
        return items;
    }

    /**
     * Represents a data item to be displayed in a grid.
     */
    public static class GridItem {
        private String date;
        private String data;
        private double average;

        /**
         * Constructs a new GridItem.
         *
         * @param date    the date of the data
         * @param data    the type of data
         * @param average the average value of the data
         */
        public GridItem(String date, String data, double average) {
            // Adjust date format
            String[] dateParts = date.split("-");
            this.date = dateParts[2] + "." + dateParts[1] + "." + dateParts[0];
            this.data = data;
            this.average = Math.round(average * 100.0) / 100.0;
        }

        /**
         * Returns the date of the data.
         *
         * @return the date
         */
        public String getDate() {
            return date;
        }

        /**
         * Returns the type of data.
         *
         * @return the data type
         */
        public String getData() {
            return data;
        }

        /**
         * Returns the average value of the data.
         *
         * @return the average value
         */
        public double getAverage() {
            return average;
        }
    }
}
