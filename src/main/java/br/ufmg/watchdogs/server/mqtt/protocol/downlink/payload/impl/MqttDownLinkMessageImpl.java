package br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.impl;

import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.MqttDownLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.MqttDownLinkMessage;
import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.parser.MqttDownLinkHeaderParser;
import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.parser.impl.MqttDownLinkHeaderParserImpl;
import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.parser.MqttDownLinkPayloadParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MqttDownLinkMessageImpl implements MqttDownLinkMessage {

    private final MqttDownLinkHeaderParser header;
    private final byte[] payload;

    public MqttDownLinkMessageImpl(MqttDownLinkPayloadParser payload, MqttDownLinkFrameType mqttDownLinkFrameTypeImpl) {
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
    public MqttDownLinkHeaderParser getHeader() {
        return header;
    }

    @Override
    public byte[] getPayload() {
        return payload;
    }
}
