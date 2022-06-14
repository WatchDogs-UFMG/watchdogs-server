package br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.impl;

import br.ufmg.watchdogs.server.api.util.MyDateTimeFormatterUtil;
import br.ufmg.watchdogs.server.mqtt.protocol.FirmwareVersion;
import br.ufmg.watchdogs.server.mqtt.protocol.ProtocolVersion;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.MqttUpLinkHeaderParser;
import br.ufmg.watchdogs.server.mqtt.util.BitWiseUtil;

import java.time.LocalDateTime;
import java.util.Arrays;

public class MqttUpLinkHeaderParserImpl implements MqttUpLinkHeaderParser {

    public static final Integer HEADER_LENGTH = 16;
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
    public static final Integer MINUTE_START_BIT = 96;
    public static final Integer MINUTE_END_BIT = 102;
    public static final Integer HOUR_START_BIT = 102;
    public static final Integer HOUR_END_BIT = 107;
    public static final Integer DAY_START_BIT = 107;
    public static final Integer DAY_END_BIT = 112;
    public static final Integer MONTH_START_BIT = 112;
    public static final Integer MONTH_END_BIT = 116;
    public static final Integer YEAR_START_BIT = 116;
    public static final Integer YEAR_END_BIT = 128;

    private static final Integer SPOT_ID_STRING_LENGTH = ((SPOT_ID_END_BIT - SPOT_ID_START_BIT) / 8) * 2;

    private ProtocolVersion protocolVersion;
    private FirmwareVersion firmwareVersion;
    private MqttUpLinkFrameTypeImpl mqttUpLinkFrameTypeImpl;
    private Integer payloadCountID;
    private Integer payloadLength;
    private String spotID;
    private LocalDateTime timestamp;

    public MqttUpLinkHeaderParserImpl(byte[] headerPayload) {
        this.protocolVersion = this.parseProtocolVersion(headerPayload);
        this.firmwareVersion = this.parseFirmwareVersion(headerPayload);
        this.mqttUpLinkFrameTypeImpl = this.parseMqttUpLinkFrameType(headerPayload);
        this.payloadCountID = this.parsePayloadCountID(headerPayload);
        this.payloadLength = this.parsePayloadLength(headerPayload);
        this.spotID = this.parseSpotID(headerPayload);
        this.timestamp = this.parseTimestamp(headerPayload);
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

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
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

    private MqttUpLinkFrameTypeImpl parseMqttUpLinkFrameType(byte[] headerPayload) {
        Integer frameTypeNumber = BitWiseUtil.extractIntegerValue(headerPayload, FRAME_TYPE_START_BIT, FRAME_TYPE_END_BIT);
        return Arrays.stream(MqttUpLinkFrameTypeImpl.values())
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
        String hexString = Long.toHexString(spotIDNumber);

        return this.formatString(hexString, MqttUpLinkHeaderParserImpl.SPOT_ID_STRING_LENGTH);
    }

    private LocalDateTime parseTimestamp(byte[] headerPayload) {

        Long minuteNumber = BitWiseUtil.extractLongValue(headerPayload, MINUTE_START_BIT, MINUTE_END_BIT);
        Long hourNumber = BitWiseUtil.extractLongValue(headerPayload, HOUR_START_BIT, HOUR_END_BIT);
        Long dayNumber = BitWiseUtil.extractLongValue(headerPayload, DAY_START_BIT, DAY_END_BIT);
        Long monthNumber = BitWiseUtil.extractLongValue(headerPayload, MONTH_START_BIT, MONTH_END_BIT);
        Long yearNumber = BitWiseUtil.extractLongValue(headerPayload, YEAR_START_BIT, YEAR_END_BIT);

        String minuteString = this.formatString(minuteNumber.toString(), 2);
        String hourString = this.formatString(hourNumber.toString(), 2);
        String dayString = this.formatString(dayNumber.toString(), 2);
        String monthString = this.formatString(monthNumber.toString(), 2);
        String yearString = this.formatString(yearNumber.toString(), 4);

        String formattedDateTimeString = this.formatDateTimeString(minuteString, hourString, dayString, monthString, yearString);

        return LocalDateTime.parse(formattedDateTimeString, MyDateTimeFormatterUtil.FORMATTER);
    }

    private String formatString(String originalString, Integer finalLength) {

        StringBuilder originalStringBuilder = new StringBuilder(originalString);

        for (int i = originalStringBuilder.length(); i < finalLength; i++) {
            originalStringBuilder.insert(0, "0");
        }

        return originalStringBuilder.toString();
    }

    private String formatDateTimeString(
            String minuteString,
            String hourString,
            String dayString,
            String monthString,
            String yearString
    ) {
        return String.format("%s/%s/%s %s:%s:00",
                dayString,
                monthString,
                yearString,
                hourString,
                minuteString
        );
    }
}
