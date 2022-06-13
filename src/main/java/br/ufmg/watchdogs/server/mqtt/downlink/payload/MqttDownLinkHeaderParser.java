package br.ufmg.watchdogs.server.mqtt.downlink.payload;

import br.ufmg.watchdogs.server.mqtt.protocol.FirmwareVersion;
import br.ufmg.watchdogs.server.mqtt.protocol.ProtocolVersion;
import br.ufmg.watchdogs.server.mqtt.util.BitWiseUtil;

public class MqttDownLinkHeaderParser {

    public static final ProtocolVersion PROTOCOL_VERSION = ProtocolVersion.V1;
    public static final FirmwareVersion FIRMWARE_VERSION = FirmwareVersion.V1;

    public static final Integer HEADER_LENGTH = 3;
    public static final Integer PROTOCOL_VERSION_OFFSET = 4;
    public static final Integer PAYLOAD_LENGTH_OFFSET = 4;

    private final MqttDownLinkFrameType mqttDownLinkFrameType;
    private final Integer payloadLength;

    public MqttDownLinkHeaderParser(Integer payloadLength, MqttDownLinkFrameType frameType) {
        this.mqttDownLinkFrameType = frameType;
        this.payloadLength = payloadLength;
    }

    public byte[] toByteArray() {

        byte byteValue1 = BitWiseUtil.concatBytes(
                (long) MqttDownLinkHeaderParser.FIRMWARE_VERSION.getVersion(),
                (long) MqttDownLinkHeaderParser.PROTOCOL_VERSION.getVersion(),
                MqttDownLinkHeaderParser.PROTOCOL_VERSION_OFFSET
        ).byteValue();

        byte byteValue2 = BitWiseUtil.concatBytes(
                BitWiseUtil.getByteSlice((long) this.payloadLength, 4, 8),
                (long) this.mqttDownLinkFrameType.getType(),
                MqttDownLinkHeaderParser.PAYLOAD_LENGTH_OFFSET
        ).byteValue();

        byte byteValue3 = BitWiseUtil.getByteSlice(
                (long) this.payloadLength,
                8,
                0
        ).byteValue();

        return new byte[]{ byteValue1, byteValue2, byteValue3 };
    }

    public MqttDownLinkFrameType getMqttDownLinkFrameType() {
        return this.mqttDownLinkFrameType;
    }
}
