package br.ufmg.watchdogs.server.unit.mqtt;

import br.ufmg.watchdogs.server.mqtt.protocol.FirmwareVersion;
import br.ufmg.watchdogs.server.mqtt.protocol.ProtocolVersion;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkHeaderParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MqttUpLinkHeaderParserTest {

    @Test
    void shouldParseACKFrameHeader() {

        byte[] payload = {
                0b00000000,
                0b00000000,
                (byte) 0b11111111,
                0b00000000,
                0b00000000,
                0b00000000,
                (byte) 0b10010000,
                (byte) 0b10010001,
                (byte) 0b10010010,
                (byte) 0b10010011,
                (byte) 0b10010100,
                (byte) 0b10010101
        };

        MqttUpLinkHeaderParser header = new MqttUpLinkHeaderParser(payload);

        Assertions.assertEquals(ProtocolVersion.V1, header.getProtocolVersion());
        Assertions.assertEquals(FirmwareVersion.V1, header.getFirmwareVersion());
        Assertions.assertEquals(MqttUpLinkFrameType.UPLINK_FRAME_TYPE_ACK, header.getMqttUpLinkFrameType());
        Assertions.assertEquals(255, header.getPayloadCountID());
        Assertions.assertEquals(0, header.getPayloadLength());
        Assertions.assertEquals("909192939495", header.getSpotID());
    }
}
