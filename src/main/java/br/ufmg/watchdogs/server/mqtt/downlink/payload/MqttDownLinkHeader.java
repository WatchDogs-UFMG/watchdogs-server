package br.ufmg.watchdogs.server.mqtt.downlink.payload;

import br.ufmg.watchdogs.server.mqtt.protocol.FirmwareVersion;
import br.ufmg.watchdogs.server.mqtt.protocol.ProtocolVersion;

public class MqttDownLinkHeader {

    static final Integer HEADER_LENGTH = 4;

    private final ProtocolVersion protocolVersion;
    private final FirmwareVersion firmwareVersion;
    private final MqttDownLinkFrameType mqttDownLinkFrameType;
    private final Integer payloadLength;

    public MqttDownLinkHeader(
            ProtocolVersion protocolVersion,
            FirmwareVersion firmwareVersion,
            MqttDownLinkFrameType mqttDownLinkFrameType,
            Integer payloadLength
    ) {
        this.protocolVersion = protocolVersion;
        this.firmwareVersion = firmwareVersion;
        this.mqttDownLinkFrameType = mqttDownLinkFrameType;
        this.payloadLength = payloadLength;
    }

    public ProtocolVersion getProtocolVersion() {
        return protocolVersion;
    }

    public FirmwareVersion getFirmwareVersion() {
        return firmwareVersion;
    }

    public MqttDownLinkFrameType getMqttDownLinkFrameType() {
        return mqttDownLinkFrameType;
    }

    public Integer getPayloadLength() {
        return payloadLength;
    }
}
