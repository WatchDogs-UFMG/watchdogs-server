package br.ufmg.watchdogs.server.mqtt.uplink.payload;

import java.util.Arrays;

public class MqttUplinkMessage {

    private final MqttUpLinkHeader header;
    private final byte[] payload;

    public MqttUplinkMessage(byte[] message) {
        this.header = new MqttUpLinkHeader(Arrays.copyOfRange(
                message,
                0,
                MqttUpLinkHeader.HEADER_LENGTH
        ));
        this.payload = Arrays.copyOfRange(
                message,
                MqttUpLinkHeader.HEADER_LENGTH,
                message.length
        );
    }

    public MqttUpLinkHeader getHeader() {
        return header;
    }

    public byte[] getPayload() {
        return payload;
    }
}
