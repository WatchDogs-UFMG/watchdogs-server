package br.ufmg.watchdogs.server.mqtt.uplink.payload.parser;

import br.ufmg.watchdogs.server.mqtt.protocol.FirmwareVersion;
import br.ufmg.watchdogs.server.mqtt.protocol.ProtocolVersion;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkFrameTypeImpl;

import java.time.LocalDateTime;

public interface MqttUpLinkHeaderParser {

    ProtocolVersion getProtocolVersion();
    FirmwareVersion getFirmwareVersion();
    MqttUpLinkFrameTypeImpl getMqttUpLinkFrameType();
    Integer getPayloadCountID();
    Integer getPayloadLength();
    String getSpotID();
    LocalDateTime getTimestamp();
}
