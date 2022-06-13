package br.ufmg.watchdogs.server.mqtt.downlink.payload.impl;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.MqttDownLinkMessage;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.impl.MqttDownLinkHeaderParserImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.MqttDownLinkPayloadParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MqttDownLinkMessageImpl implements MqttDownLinkMessage {

    private final MqttDownLinkHeaderParserImpl header;
    private final byte[] payload;

    public MqttDownLinkMessageImpl(MqttDownLinkPayloadParser payload, MqttDownLinkFrameTypeImpl mqttDownLinkFrameTypeImpl) {
        this.payload = payload.toByteArray();
        this.header = new MqttDownLinkHeaderParserImpl(this.payload.length, mqttDownLinkFrameTypeImpl);
    }

    @Override
    public byte[] toByteArray() throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(this.header.toByteArray());
        byteArrayOutputStream.write(this.payload);

        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public MqttDownLinkHeaderParserImpl getHeader() {
        return header;
    }

    @Override
    public byte[] getPayload() {
        return payload;
    }
}
