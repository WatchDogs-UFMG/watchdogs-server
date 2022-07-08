package br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload;

import br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.parser.MqttUpLinkHeaderParser;

public interface MqttUpLinkMessage {

    MqttUpLinkHeaderParser getHeader();
    byte[] getPayload();
}
