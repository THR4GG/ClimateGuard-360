package de.climateguard.application.mqtt;

import de.climateguard.application.data.*;
import de.climateguard.application.data.service.WeatherStationService;
import de.climateguard.application.data.service.SensorDataService;
import de.climateguard.application.data.repository.WeatherStationRepository;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MQTTDataProcessor extends AbstractMQTTManager {

    @Autowired
    private WeatherStationRepository weatherStationRepository;
    @Autowired
    private SensorDataService sensorDataService;
    @Autowired
    private WeatherStationService weatherStationService;

    public MQTTDataProcessor() throws MqttException {
        super();
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // Split the topic to get station_id and table name
        String[] topicParts = topic.split("/");
        if (topicParts.length != 2) {
            System.err.println("Invalid topic format. Expected 'station_id/tablename'");
            return;
        }

        String stationId = topicParts[0];
        String tableName = topicParts[1];
        String payload = new String(message.getPayload());

        // Check if the weather station exists, if not create it
        weatherStationRepository.findById(stationId).orElseGet(() -> {
            WeatherStation newStation = new WeatherStation();
            newStation.setStationId(stationId);
            newStation.setName("Station " + stationId); // Default name, can be changed later
            newStation.setDescription("Auto-created station for ID: " + stationId);
            weatherStationRepository.save(newStation);
            return newStation;
        });

        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Save the data based on the table name using the service layer
        switch (tableName.toLowerCase()) {
            case "airquality":
                sensorDataService.saveAirQuality(stationId, Integer.parseInt(payload), now);
                break;
            case "humidity":
                sensorDataService.saveHumidity(stationId, Double.parseDouble(payload), now);
                break;
            case "lightintensity":
                sensorDataService.saveLightIntensity(stationId, Double.parseDouble(payload), now);
                break;
            case "pressure":
                sensorDataService.savePressure(stationId, Double.parseDouble(payload), now);
                break;
            case "rainsensor":
                sensorDataService.saveRainSensor(stationId, Boolean.parseBoolean(payload), now);
                break;
            case "temperature":
                sensorDataService.saveTemperature(stationId, Double.parseDouble(payload), now);
                break;
            default:
                System.err.println("Unknown table name: " + tableName);
                break;
        }
    }
}
