package de.climateguard.application.mqtt;

/**
 * Represents the details of an MQTT message.
 */
public class MqttMessageDetails {
    private String topic;
    private String payload;
    private String timestamp;

    /**
     * Constructs a new MqttMessageDetails object with the specified topic, payload,
     * and timestamp.
     *
     * @param topic     the topic of the MQTT message
     * @param payload   the payload of the MQTT message
     * @param timestamp the timestamp when the MQTT message was received
     */
    public MqttMessageDetails(String topic, String payload, String timestamp) {
        this.topic = topic;
        this.payload = payload;
        this.timestamp = timestamp;
    }

    /**
     * Returns the topic of the MQTT message.
     *
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Sets the topic of the MQTT message.
     *
     * @param topic the topic to set
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Returns the payload of the MQTT message.
     *
     * @return the payload
     */
    public String getPayload() {
        return payload;
    }

    /**
     * Sets the payload of the MQTT message.
     *
     * @param payload the payload to set
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

    /**
     * Returns the timestamp when the MQTT message was received.
     *
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp when the MQTT message was received.
     *
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
