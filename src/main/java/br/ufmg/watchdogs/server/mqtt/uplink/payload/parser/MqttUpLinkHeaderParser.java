package br.ufmg.watchdogs.server.mqtt.uplink.payload.parser;

import br.ufmg.watchdogs.server.mqtt.protocol.FirmwareVersion;
import br.ufmg.watchdogs.server.mqtt.protocol.ProtocolVersion;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkFrameTypeImpl;

public interface MqttUpLinkHeaderParser {

    ProtocolVersion parseProtocolVersion(byte[] headerPayload);
    FirmwareVersion parseFirmwareVersion(byte[] headerPayload);
    MqttUpLinkFrameTypeImpl parseMqttUpLinkFrameType(byte[] headerPayload);
    Integer parsePayloadCountID(byte[] headerPayload);
    Integer parsePayloadLength(byte[] headerPayload);
    String parseSpotID(byte[] headerPayload);
    ProtocolVersion getProtocolVersion();
    FirmwareVersion getFirmwareVersion();
    MqttUpLinkFrameTypeImpl getMqttUpLinkFrameType();
    Integer getPayloadCountID();
    Integer getPayloadLength();
    String getSpotID();
}
