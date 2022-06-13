package br.ufmg.watchdogs.server.mqtt.downlink.payload.impl;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.MqttDownLinkPayloadParser;

public class MqttDownLinkAckPayloadParserImpl implements MqttDownLinkPayloadParser {

    @Override
    public byte[] toByteArray() {
        return new byte[0];
    }
}
