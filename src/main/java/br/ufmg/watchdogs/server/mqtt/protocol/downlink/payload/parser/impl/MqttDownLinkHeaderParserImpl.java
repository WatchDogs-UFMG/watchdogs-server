package br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.parser.impl;

import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.MqttDownLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.parser.MqttDownLinkHeaderParser;
import br.ufmg.watchdogs.server.mqtt.protocol.FirmwareVersion;
import br.ufmg.watchdogs.server.mqtt.protocol.ProtocolVersion;
import br.ufmg.watchdogs.server.util.BitWiseUtil;

public class MqttDownLinkHeaderParserImpl implements MqttDownLinkHeaderParser {

    public static final ProtocolVersion PROTOCOL_VERSION = ProtocolVersion.V1;
    public static final FirmwareVersion FIRMWARE_VERSION = FirmwareVersion.V1;

    public static final Integer HEADER_LENGTH = 3;
    public static final Integer PROTOCOL_VERSION_OFFSET = 4;
    public static final Integer PAYLOAD_LENGTH_OFFSET = 4;

    private final MqttDownLinkFrameType mqttDownLinkFrameType;
    private final Integer payloadLength;

    public MqttDownLinkHeaderParserImpl(Integer payloadLength, MqttDownLinkFrameType frameType) {
        this.mqttDownLinkFrameType = frameType;
        this.payloadLength = payloadLength;
    }

    @Override
    public byte[] toByteArray() {

        byte byteValue1 = BitWiseUtil.concatBytes(
                (long) MqttDownLinkHeaderParserImpl.PROTOCOL_VERSION.getVersion(),
                (long) MqttDownLinkHeaderParserImpl.FIRMWARE_VERSION.getVersion(),
                MqttDownLinkHeaderParserImpl.PROTOCOL_VERSION_OFFSET
        ).byteValue();

        byte byteValue2 = BitWiseUtil.concatBytes(
                (long) this.mqttDownLinkFrameType.getType(),
                BitWiseUtil.getByteSlice((long) this.payloadLength, 4, 8),
                MqttDownLinkHeaderParserImpl.PAYLOAD_LENGTH_OFFSET
        ).byteValue();

        byte byteValue3 = BitWiseUtil.getByteSlice(
                (long) this.payloadLength,
                8,
                0
        ).byteValue();

        return new byte[]{ byteValue1, byteValue2, byteValue3 };
    }

    @Override
    public MqttDownLinkFrameType getMqttDownLinkFrameType() {
        return this.mqttDownLinkFrameType;
    }
}
