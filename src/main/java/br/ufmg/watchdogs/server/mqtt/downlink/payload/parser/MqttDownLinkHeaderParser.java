package br.ufmg.watchdogs.server.mqtt.downlink.payload.parser;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.impl.MqttDownLinkFrameTypeImpl;

public interface MqttDownLinkHeaderParser {

    byte[] toByteArray();
    MqttDownLinkFrameTypeImpl getMqttDownLinkFrameType();
}
