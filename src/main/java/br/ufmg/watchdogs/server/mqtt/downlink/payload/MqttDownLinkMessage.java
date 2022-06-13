package br.ufmg.watchdogs.server.mqtt.downlink.payload;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.impl.MqttDownLinkHeaderParserImpl;

import java.io.IOException;

public interface MqttDownLinkMessage {

    byte[] toByteArray() throws IOException;
    MqttDownLinkHeaderParserImpl getHeader();
    byte[] getPayload();
}
