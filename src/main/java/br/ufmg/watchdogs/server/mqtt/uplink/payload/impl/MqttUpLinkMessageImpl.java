package br.ufmg.watchdogs.server.mqtt.uplink.payload.impl;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkMessage;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.MqttUpLinkHeaderParser;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.impl.MqttUpLinkHeaderParserImpl;

import java.util.Arrays;

public class MqttUpLinkMessageImpl implements MqttUpLinkMessage {

    private final MqttUpLinkHeaderParser header;
    private final byte[] payload;

    public MqttUpLinkMessageImpl(byte[] message) {
        this.header = new MqttUpLinkHeaderParserImpl(Arrays.copyOfRange(
                message,
                0,
                MqttUpLinkHeaderParserImpl.HEADER_LENGTH
        ));
        this.payload = Arrays.copyOfRange(
                message,
                MqttUpLinkHeaderParserImpl.HEADER_LENGTH,
                message.length
        );
    }

    @Override
    public MqttUpLinkHeaderParser getHeader() {
        return header;
    }

    @Override
    public byte[] getPayload() {
        return payload;
    }
}
