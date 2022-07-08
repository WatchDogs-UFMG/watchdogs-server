package br.ufmg.watchdogs.server.mqtt.protocol.uplink.topic;

public interface MqttUpLinkTopic {
    String getTopicName();
    Integer getTopicQoS();
}
