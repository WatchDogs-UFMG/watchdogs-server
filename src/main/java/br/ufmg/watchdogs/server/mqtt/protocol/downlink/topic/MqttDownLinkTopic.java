package br.ufmg.watchdogs.server.mqtt.protocol.downlink.topic;

public interface MqttDownLinkTopic {
    String getTopicName(String spotID);
    Integer getTopicQoS();
}
