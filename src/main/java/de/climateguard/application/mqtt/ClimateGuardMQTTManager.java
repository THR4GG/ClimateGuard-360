package de.climateguard.application.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import de.climateguard.application.views.climateguard.ClimateGuardView;

/**
 * Manages MQTT communication for the ClimateGuard application.
 * Extends the {@link AbstractMQTTManager} to handle specific topics and
 * messages.
 */
public class ClimateGuardMQTTManager extends AbstractMQTTManager {

    private ClimateGuardView climateGuardView;

    /**
     * Constructs a new ClimateGuardMQTTManager.
     * Initializes the MQTT manager and sets the associated ClimateGuard view.
     *
     * @param view the ClimateGuard view
     * @throws MqttException if there is an issue with MQTT connection
     */
    public ClimateGuardMQTTManager(ClimateGuardView view) throws MqttException {
        super();
        this.climateGuardView = view;
    }

    /**
     * Handles incoming MQTT messages.
     * Updates the ClimateGuard view with the received message data.
     *
     * @param topic   the topic on which the message was received
     * @param message the received message
     * @throws Exception if there is an issue processing the message
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String payload = new String(message.getPayload());
        System.out.println("Message received on topic " + topic + ": " + payload);

        if (climateGuardView != null) {
            climateGuardView.updateField(topic, payload);
            climateGuardView.updateGauge(topic, payload);
        }
    }

    /**
     * Subscribes to the specified base topic and its related topics.
     *
     * @param baseTopic the base topic to subscribe to
     * @throws MqttException if there is an issue with MQTT subscription
     */
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
