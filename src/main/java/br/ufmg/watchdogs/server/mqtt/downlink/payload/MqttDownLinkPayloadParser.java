package br.ufmg.watchdogs.server.mqtt.downlink.payload;

public interface MqttDownLinkPayloadParser {

    byte[] toByteArray();
}
