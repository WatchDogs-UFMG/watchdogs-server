package br.ufmg.watchdogs.server.mqtt.uplink.payload;

import java.util.Arrays;

public class MqttUpLinkMessage {

    private final MqttUpLinkHeaderParser header;
    private final byte[] payload;

    public MqttUpLinkMessage(byte[] message) {
        this.header = new MqttUpLinkHeaderParser(Arrays.copyOfRange(
                message,
                0,
                MqttUpLinkHeaderParser.HEADER_LENGTH
        ));
        this.payload = Arrays.copyOfRange(
                message,
                MqttUpLinkHeaderParser.HEADER_LENGTH,
                message.length
        );
    }

    public MqttUpLinkHeaderParser getHeader() {
        return header;
    }

    public byte[] getPayload() {
        return payload;
    }
}
