package br.ufmg.watchdogs.server.mqtt.uplink.topic;

public interface MqttUpLinkTopic {
    String getTopicName();
    Integer getTopicQoS();
}
