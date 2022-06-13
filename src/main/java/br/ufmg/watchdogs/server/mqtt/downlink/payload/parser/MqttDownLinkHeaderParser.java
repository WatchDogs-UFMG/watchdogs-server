package br.ufmg.watchdogs.server.mqtt.downlink.payload.parser;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.MqttDownLinkFrameType;

public interface MqttDownLinkHeaderParser {

    byte[] toByteArray();
    MqttDownLinkFrameType getMqttDownLinkFrameType();
}
