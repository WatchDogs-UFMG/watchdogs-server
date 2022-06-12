package br.ufmg.watchdogs.server.mqtt.downlink.topic;

public interface MqttDownLinkTopic {
    String getTopicName(String spotID);
    Integer getTopicQoS();
}
