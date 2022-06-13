package br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.impl;

import br.ufmg.watchdogs.server.mqtt.protocol.FirmwareVersion;
import br.ufmg.watchdogs.server.mqtt.protocol.ProtocolVersion;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.MqttUpLinkHeaderParser;
import br.ufmg.watchdogs.server.mqtt.util.BitWiseUtil;

import java.util.Arrays;

public class MqttUpLinkHeaderParserImpl implements MqttUpLinkHeaderParser {

    public static final Integer HEADER_LENGTH = 12;
    public static final Integer PROTOCOL_VERSION_START_BIT = 0;
    public static final Integer PROTOCOL_VERSION_END_BIT = 4;
    public static final Integer FIRMWARE_VERSION_START_BIT = 4;
    public static final Integer FIRMWARE_VERSION_END_BIT = 8;
    public static final Integer FRAME_TYPE_START_BIT = 8;
    public static final Integer FRAME_TYPE_END_BIT = 12;
    public static final Integer PAYLOAD_COUNT_ID_VERSION_START_BIT = 12;
    public static final Integer PAYLOAD_COUNT_ID_VERSION_END_BIT = 24;
    public static final Integer PAYLOAD_LENGTH_START_BIT = 24;
    public static final Integer PAYLOAD_LENGTH_END_BIT = 48;
    public static final Integer SPOT_ID_START_BIT = 48;
    public static final Integer SPOT_ID_END_BIT = 96;

    private ProtocolVersion protocolVersion;
    private FirmwareVersion firmwareVersion;
    private MqttUpLinkFrameTypeImpl mqttUpLinkFrameTypeImpl;
    private Integer payloadCountID;
    private Integer payloadLength;
    private String spotID;

    public MqttUpLinkHeaderParserImpl(byte[] headerPayload) {
        this.protocolVersion = this.parseProtocolVersion(headerPayload);
        this.firmwareVersion = this.parseFirmwareVersion(headerPayload);
        this.mqttUpLinkFrameTypeImpl = this.parseMqttUpLinkFrameType(headerPayload);
        this.payloadCountID = this.parsePayloadCountID(headerPayload);
        this.payloadLength = this.parsePayloadLength(headerPayload);
        this.spotID = this.parseSpotID(headerPayload);
    }

    @Override
    public ProtocolVersion parseProtocolVersion(byte[] headerPayload) {
        Integer protocolVersionNumber = BitWiseUtil.extractIntegerValue(headerPayload, PROTOCOL_VERSION_START_BIT, PROTOCOL_VERSION_END_BIT);
        return Arrays.stream(ProtocolVersion.values())
                .filter(protocolVersion -> protocolVersion.getVersion().equals(protocolVersionNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Protocol version not found: " + protocolVersionNumber));
    }

    @Override
    public FirmwareVersion parseFirmwareVersion(byte[] headerPayload) {
        Integer firmwareVersionNumber = BitWiseUtil.extractIntegerValue(headerPayload, FIRMWARE_VERSION_START_BIT, FIRMWARE_VERSION_END_BIT);
        return Arrays.stream(FirmwareVersion.values())
                .filter(firmwareVersion -> firmwareVersion.getVersion().equals(firmwareVersionNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Firmware version not found: " + firmwareVersionNumber));
    }

    @Override
    public MqttUpLinkFrameTypeImpl parseMqttUpLinkFrameType(byte[] headerPayload) {
        Integer frameTypeNumber = BitWiseUtil.extractIntegerValue(headerPayload, FRAME_TYPE_START_BIT, FRAME_TYPE_END_BIT);
        return Arrays.stream(MqttUpLinkFrameTypeImpl.values())
                .filter(firmwareVersion -> firmwareVersion.getType().equals(frameTypeNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Frame type not found: " + frameTypeNumber));
    }

    @Override
    public Integer parsePayloadCountID(byte[] headerPayload) {
        return BitWiseUtil.extractIntegerValue(headerPayload, PAYLOAD_COUNT_ID_VERSION_START_BIT, PAYLOAD_COUNT_ID_VERSION_END_BIT);
    }

    @Override
    public Integer parsePayloadLength(byte[] headerPayload) {
        return BitWiseUtil.extractIntegerValue(headerPayload, PAYLOAD_LENGTH_START_BIT, PAYLOAD_LENGTH_END_BIT);
    }

    @Override
    public String parseSpotID(byte[] headerPayload) {
        Long spotIDNumber = BitWiseUtil.extractLongValue(headerPayload, SPOT_ID_START_BIT, SPOT_ID_END_BIT);
        return Long.toHexString(spotIDNumber);
    }

    @Override
    public ProtocolVersion getProtocolVersion() {
        return protocolVersion;
    }

    @Override
    public FirmwareVersion getFirmwareVersion() {
        return firmwareVersion;
    }

    @Override
    public MqttUpLinkFrameTypeImpl getMqttUpLinkFrameType() {
        return mqttUpLinkFrameTypeImpl;
    }

    @Override
    public Integer getPayloadCountID() {
        return payloadCountID;
    }

    @Override
    public Integer getPayloadLength() {
        return payloadLength;
    }

    @Override
    public String getSpotID() {
        return spotID;
    }
}
