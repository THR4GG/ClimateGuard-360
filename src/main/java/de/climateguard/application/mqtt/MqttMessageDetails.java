package de.climateguard.application.mqtt;

public class MqttMessageDetails {
    private String topic;
    private String payload;
    private String timestamp;

    // Getter und Setter

    public MqttMessageDetails(String topic, String payload, String timestamp) {
        this.topic = topic;
        this.payload = payload;
        this.timestamp = timestamp;
    }

    public String getTopic() {
        return topic;   
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
