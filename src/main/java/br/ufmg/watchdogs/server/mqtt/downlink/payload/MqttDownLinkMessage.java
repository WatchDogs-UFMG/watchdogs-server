package br.ufmg.watchdogs.server.mqtt.downlink.payload;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.MqttDownLinkHeaderParser;

import java.io.IOException;

public interface MqttDownLinkMessage {

    byte[] toByteArray() throws IOException;
    MqttDownLinkHeaderParser getHeader();
    byte[] getPayload();
}
