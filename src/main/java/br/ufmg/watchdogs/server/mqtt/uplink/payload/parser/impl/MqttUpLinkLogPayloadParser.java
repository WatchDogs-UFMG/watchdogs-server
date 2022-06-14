package br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.impl;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.MqttUpLinkPayloadParser;

public class MqttUpLinkLogPayloadParser implements MqttUpLinkPayloadParser {

    private LogTriggerEvent triggerEvent;

    public MqttUpLinkLogPayloadParser(byte[] payload) {
    }
}
