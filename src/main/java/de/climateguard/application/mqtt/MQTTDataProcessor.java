package de.climateguard.application.mqtt;

import de.climateguard.application.data.*;
import de.climateguard.application.data.service.SensorDataService;
import de.climateguard.application.data.repository.WeatherStationRepository;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Service
public class MQTTDataProcessor extends AbstractMQTTManager {

    @Autowired
    private WeatherStationRepository weatherStationRepository;
    @Autowired
    private SensorDataService sensorDataService;

    public MQTTDataProcessor() throws MqttException {
        super();
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String[] topicParts = topic.split("/");
        if (topicParts.length != 2) {
            System.err.println("Invalid topic format. Expected 'station_id/tablename'");
            return;
        }

        if (!Pattern.matches("^([0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}$", topicParts[0])) {
            System.err.println("Invalid station ID format. Expected '00:00:00:00:00:00'");
            return;
        }

        String stationId = topicParts[0];
        String tableName = topicParts[1];
        String payload = new String(message.getPayload());

        weatherStationRepository.findById(stationId).orElseGet(() -> {
            WeatherStation newStation = new WeatherStation();
            newStation.setStationId(stationId);
            newStation.setName("Station " + stationId);
            newStation.setDescription("Auto-created station for ID: " + stationId);
            weatherStationRepository.save(newStation);
            return newStation;
        });

        LocalDateTime now = LocalDateTime.now();

        switch (tableName.toLowerCase()) {
            case "airquality":
                sensorDataService.saveAirQuality(stationId, Double.parseDouble(payload), now);
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
