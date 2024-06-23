package de.climateguard.application.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import de.climateguard.application.views.climateguard.ClimateGuardView;

public class ClimateGuardMQTTManager extends AbstractMQTTManager {

    private ClimateGuardView climateGuardView;

    public ClimateGuardMQTTManager(ClimateGuardView view) throws MqttException {
        super();
        this.climateGuardView = view;
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String payload = new String(message.getPayload());
        System.out.println("Message received on topic " + topic + ": " + payload);

        if (climateGuardView != null) {
            climateGuardView.updateField(topic, payload);
            climateGuardView.updateGauge(topic, payload);
        }
    }

    public void subscribeToTopics(String baseTopic) throws MqttException {
        String[] topics = {
                baseTopic + "/Temperature",
                baseTopic + "/Humidity",
                baseTopic + "/Pressure",
                baseTopic + "/AirQuality",
                baseTopic + "/LightIntensity",
                baseTopic + "/RainSensor",
                baseTopic + "/Mode"
        };

        for (String topic : topics) {
            System.out.println("Subscribing to topic: " + topic);
            client.subscribe(topic);
        }
    }
}
