package de.climateguard.application.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public abstract class AbstractMQTTManager implements MqttCallback {

    protected MqttClient client;
    private String brokerUrl;
    private String clientId;

    public AbstractMQTTManager() throws MqttException {
        this.clientId = generateClientId();
        this.brokerUrl = loadBrokerUrl();
        connect();
        subscribeToTopic("#");
    }

    private String generateClientId() {
        return "VaadinClient-" + UUID.randomUUID();
    }

    private String loadBrokerUrl() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Unable to find application.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return properties.getProperty("mqtt.broker.url");
    }

    private void connect() throws MqttException {
        if (brokerUrl == null || brokerUrl.isEmpty()) {
            throw new MqttException(new Throwable("Broker URL is not set"));
        }

        if (client != null && client.isConnected()) {
            client.disconnect();
        }

        client = new MqttClient(brokerUrl, clientId, new MemoryPersistence());
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setAutomaticReconnect(true);
        client.setCallback(this);
        client.connect(connOpts);

        System.out.println("Connected to MQTT broker: " + brokerUrl);
    }

    public void subscribeToTopic(String topic) throws MqttException {
        System.out.println("Subscribing to topic: " + topic);
        client.subscribe(topic);
    }

    public void changeSubscription(String newTopic) throws MqttException {
        if (client != null && client.isConnected()) {
            client.unsubscribe("#");
            System.out.println("Unsubscribed from all topics");
            System.out.println("Subscribing to new topic: " + newTopic);
            client.subscribe(newTopic);
        }
    }

    public void publishMessage(String topic, String message) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(2);
        client.publish(topic, mqttMessage);
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost! MqttException: " + cause.getMessage());
        cause.printStackTrace();
        try {
            System.out.println("Attempting to reconnect to MQTT broker...");
            client.reconnect();
        } catch (MqttException e) {
            System.err.println("Reconnection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // Not used
    }

    // Abstrakte Methode für die spezifische Nachrichtenzustellung
    @Override
    public abstract void messageArrived(String topic, MqttMessage message) throws Exception;
}
