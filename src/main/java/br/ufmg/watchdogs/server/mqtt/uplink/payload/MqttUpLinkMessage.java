package br.ufmg.watchdogs.server.mqtt.uplink.payload;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.MqttUpLinkHeaderParser;

public interface MqttUpLinkMessage {

    MqttUpLinkHeaderParser getHeader();
    byte[] getPayload();
}
