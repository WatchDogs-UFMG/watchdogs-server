package br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.parser;

import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.MqttDownLinkFrameType;

public interface MqttDownLinkHeaderParser {

    byte[] toByteArray();
    MqttDownLinkFrameType getMqttDownLinkFrameType();
}
