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

/**
 * Abstract class for managing MQTT connections and handling messages.
 * Implements the {@link MqttCallback} interface to handle MQTT events.
 */
public abstract class AbstractMQTTManager implements MqttCallback {

    protected MqttClient client;
    private String brokerUrl;
    private String clientId;

    /**
     * Constructs a new AbstractMQTTManager.
     * Initializes the MQTT client, loads broker URL, and connects to the broker.
     *
     * @throws MqttException if there is an issue with MQTT connection
     */
    public AbstractMQTTManager() throws MqttException {
        this.clientId = generateClientId();
        this.brokerUrl = loadBrokerUrl();
        connect();
        subscribeToTopic("#");
    }

    /**
     * Generates a unique client ID for the MQTT client.
     *
     * @return a unique client ID
     */
    private String generateClientId() {
        return "VaadinClient-" + UUID.randomUUID();
    }

    /**
     * Loads the broker URL from the application properties file.
     *
     * @return the broker URL
     */
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

    /**
     * Connects to the MQTT broker using the broker URL and client ID.
     *
     * @throws MqttException if there is an issue with MQTT connection
     */
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

    /**
     * Subscribes to the specified MQTT topic.
     *
     * @param topic the MQTT topic to subscribe to
     * @throws MqttException if there is an issue with MQTT subscription
     */
    public void subscribeToTopic(String topic) throws MqttException {
        System.out.println("Subscribing to topic: " + topic);
        client.subscribe(topic);
    }

    /**
     * Changes the subscription to a new MQTT topic.
     *
     * @param newTopic the new MQTT topic to subscribe to
     * @throws MqttException if there is an issue with changing the MQTT
     *                       subscription
     */
    public void changeSubscription(String newTopic) throws MqttException {
        if (client != null && client.isConnected()) {
            client.unsubscribe("#");
            System.out.println("Unsubscribed from all topics");
            System.out.println("Subscribing to new topic: " + newTopic);
            client.subscribe(newTopic);
        }
    }

    /**
     * Publishes a message to the specified MQTT topic.
     *
     * @param topic   the MQTT topic to publish to
     * @param message the message to publish
     * @throws MqttException if there is an issue with MQTT publishing
     */
    public void publishMessage(String topic, String message) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(2);
        client.publish(topic, mqttMessage);
    }

    /**
     * Handles the event when the connection to the MQTT broker is lost.
     *
     * @param cause the cause of the connection loss
     */
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

    /**
     * Handles the event when a message delivery is complete.
     *
     * @param token the delivery token
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // No implementation needed for this example
    }

    /**
     * Handles the event when a message arrives from the MQTT broker.
     *
     * @param topic   the topic on which the message was received
     * @param message the message that arrived
     * @throws Exception if there is an issue processing the message
     */
    @Override
    public abstract void messageArrived(String topic, MqttMessage message) throws Exception;
}
