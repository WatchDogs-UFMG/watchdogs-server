package br.ufmg.watchdogs.server.mqtt.downlink.payload;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MqttDownLinkMessage {

    private final MqttDownLinkHeaderParser header;
    private final byte[] payload;

    public MqttDownLinkMessage(MqttDownLinkPayloadParser payload, MqttDownLinkFrameType mqttDownLinkFrameType) {
        this.payload = payload.toByteArray();
        this.header = new MqttDownLinkHeaderParser(this.payload.length, mqttDownLinkFrameType);
    }

    public byte[] toByteArray() throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(this.header.toByteArray());
        byteArrayOutputStream.write(this.payload);

        return byteArrayOutputStream.toByteArray();
    }

    public MqttDownLinkHeaderParser getHeader() {
        return header;
    }

    public byte[] getPayload() {
        return payload;
    }
}
