package br.ufmg.watchdogs.server.mqtt.uplink.payload;

import br.ufmg.watchdogs.server.mqtt.protocol.FirmwareVersion;
import br.ufmg.watchdogs.server.mqtt.protocol.ProtocolVersion;
import br.ufmg.watchdogs.server.mqtt.util.BitWiseUtil;

import java.util.Arrays;

public class MqttUpLinkHeaderParser {

    static final Integer HEADER_LENGTH = 12;
    static final Integer PROTOCOL_VERSION_START_BIT = 0;
    static final Integer PROTOCOL_VERSION_END_BIT = 4;
    static final Integer FIRMWARE_VERSION_START_BIT = 4;
    static final Integer FIRMWARE_VERSION_END_BIT = 8;
    static final Integer FRAME_TYPE_START_BIT = 8;
    static final Integer FRAME_TYPE_END_BIT = 12;
    static final Integer PAYLOAD_COUNT_ID_VERSION_START_BIT = 12;
    static final Integer PAYLOAD_COUNT_ID_VERSION_END_BIT = 24;
    static final Integer PAYLOAD_LENGTH_START_BIT = 24;
    static final Integer PAYLOAD_LENGTH_END_BIT = 48;
    static final Integer SPOT_ID_START_BIT = 48;
    static final Integer SPOT_ID_END_BIT = 96;

    private ProtocolVersion protocolVersion;
    private FirmwareVersion firmwareVersion;
    private MqttUpLinkFrameType mqttUpLinkFrameType;
    private Integer payloadCountID;
    private Integer payloadLength;
    private String spotID;

    public MqttUpLinkHeaderParser(byte[] headerPayload) {
        this.protocolVersion = this.parseProtocolVersion(headerPayload);
        this.firmwareVersion = this.parseFirmwareVersion(headerPayload);
        this.mqttUpLinkFrameType = this.parseMqttUpLinkFrameType(headerPayload);
        this.payloadCountID = this.parsePayloadCountID(headerPayload);
        this.payloadLength = this.parsePayloadLength(headerPayload);
        this.spotID = this.parseSpotID(headerPayload);
    }

    private ProtocolVersion parseProtocolVersion(byte[] headerPayload) {
        Integer protocolVersionNumber = BitWiseUtil.extractIntegerValue(headerPayload, PROTOCOL_VERSION_START_BIT, PROTOCOL_VERSION_END_BIT);
        return Arrays.stream(ProtocolVersion.values())
                .filter(protocolVersion -> protocolVersion.getVersion().equals(protocolVersionNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Protocol version not found: " + protocolVersionNumber));
    }

    private FirmwareVersion parseFirmwareVersion(byte[] headerPayload) {
        Integer firmwareVersionNumber = BitWiseUtil.extractIntegerValue(headerPayload, FIRMWARE_VERSION_START_BIT, FIRMWARE_VERSION_END_BIT);
        return Arrays.stream(FirmwareVersion.values())
                .filter(firmwareVersion -> firmwareVersion.getVersion().equals(firmwareVersionNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Firmware version not found: " + firmwareVersionNumber));
    }

    private MqttUpLinkFrameType parseMqttUpLinkFrameType(byte[] headerPayload) {
        Integer frameTypeNumber = BitWiseUtil.extractIntegerValue(headerPayload, FRAME_TYPE_START_BIT, FRAME_TYPE_END_BIT);
        return Arrays.stream(MqttUpLinkFrameType.values())
                .filter(firmwareVersion -> firmwareVersion.getType().equals(frameTypeNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Frame type not found: " + frameTypeNumber));
    }

    private Integer parsePayloadCountID(byte[] headerPayload) {
        return BitWiseUtil.extractIntegerValue(headerPayload, PAYLOAD_COUNT_ID_VERSION_START_BIT, PAYLOAD_COUNT_ID_VERSION_END_BIT);
    }

    private Integer parsePayloadLength(byte[] headerPayload) {
        return BitWiseUtil.extractIntegerValue(headerPayload, PAYLOAD_LENGTH_START_BIT, PAYLOAD_LENGTH_END_BIT);
    }

    private String parseSpotID(byte[] headerPayload) {
        Long spotIDNumber = BitWiseUtil.extractLongValue(headerPayload, SPOT_ID_START_BIT, SPOT_ID_END_BIT);
        return Long.toHexString(spotIDNumber);
    }

    public ProtocolVersion getProtocolVersion() {
        return protocolVersion;
    }

    public FirmwareVersion getFirmwareVersion() {
        return firmwareVersion;
    }

    public MqttUpLinkFrameType getMqttUpLinkFrameType() {
        return mqttUpLinkFrameType;
    }

    public Integer getPayloadCountID() {
        return payloadCountID;
    }

    public Integer getPayloadLength() {
        return payloadLength;
    }

    public String getSpotID() {
        return spotID;
    }
}
