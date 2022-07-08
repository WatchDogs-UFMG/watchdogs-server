package br.ufmg.watchdogs.server.mqtt.client;

public interface MqttClientAdapter {

    void connect();
    void subscribe(String topicName, Integer topicQoS);
    boolean publish(byte[] payload, String topicName, Integer topicQoS);
    void setSubListenerCallback(MqttCallbackAdapter callback);
    void keepSendingTestMessages();
}
