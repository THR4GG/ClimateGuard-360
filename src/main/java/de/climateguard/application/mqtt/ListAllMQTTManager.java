package de.climateguard.application.mqtt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import de.climateguard.application.views.listall.ListAllView;

public class ListAllMQTTManager extends AbstractMQTTManager {

    private ListAllView listAllView;

    public ListAllMQTTManager(ListAllView view) throws MqttException {
        super();
        this.listAllView = view;
    }

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
