package br.ufmg.watchdogs.server.mqtt.uplink.payload;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.impl.MqttUpLinkHeaderParserImpl;

import java.util.Arrays;

public interface MqttUpLinkMessage {

    MqttUpLinkHeaderParserImpl getHeader();
    byte[] getPayload();
}
