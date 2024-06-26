package de.climateguard.application.mqtt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import de.climateguard.application.views.listall.ListAllView;

/**
 * Manages MQTT communication for the ListAll view.
 * Extends the {@link AbstractMQTTManager} to handle specific topics and
 * messages for the ListAll view.
 */
public class ListAllMQTTManager extends AbstractMQTTManager {

    private ListAllView listAllView;

    /**
     * Constructs a new ListAllMQTTManager.
     * Initializes the MQTT manager and sets the associated ListAll view.
     *
     * @param view the ListAll view
     * @throws MqttException if there is an issue with MQTT connection
     */
    public ListAllMQTTManager(ListAllView view) throws MqttException {
        super();
        this.listAllView = view;
    }

    /**
     * Handles incoming MQTT messages.
     * Updates the ListAll view with the received message data.
     *
     * @param topic   the topic on which the message was received
     * @param message the received message
     * @throws Exception if there is an issue processing the message
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String payload = new String(message.getPayload());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String timestamp = LocalDateTime.now().format(formatter);

        System.out.println("Message received on topic " + topic + ": " + payload);

        if (listAllView != null) {
            MqttMessageDetails details = new MqttMessageDetails(topic, payload, timestamp);
            listAllView.addMessageToGrid(details);
        }
    }
}
