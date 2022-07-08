package br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload;

public interface MqttDownLinkFrameType {

    Integer getType();
    String name();
}
