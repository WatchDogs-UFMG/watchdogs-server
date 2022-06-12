package br.ufmg.watchdogs.server.mqtt.uplink.payload;

import br.ufmg.watchdogs.server.mqtt.protocol.FirmwareVersion;
import br.ufmg.watchdogs.server.mqtt.protocol.ProtocolVersion;

public class MqttUpLinkHeader {

    static final Integer HEADER_LENGTH = 10;

    private ProtocolVersion protocolVersion;
    private FirmwareVersion firmwareVersion;
    private MqttUpLinkFrameType mqttUpLinkFrameType;
    private Integer count;
    private Integer payloadLength;
    private String spotID;

    public MqttUpLinkHeader(byte[] header) {

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

    public Integer getCount() {
        return count;
    }

    public Integer getPayloadLength() {
        return payloadLength;
    }

    public String getSpotID() {
        return spotID;
    }
}
