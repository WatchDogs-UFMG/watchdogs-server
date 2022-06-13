package br.ufmg.watchdogs.server.test.unit.mqtt.uplink.payload;

import br.ufmg.watchdogs.server.mqtt.protocol.FirmwareVersion;
import br.ufmg.watchdogs.server.mqtt.protocol.ProtocolVersion;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.impl.MqttUpLinkHeaderParserImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MqttUpLinkHeaderParserImplTest {

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

        MqttUpLinkHeaderParserImpl header = new MqttUpLinkHeaderParserImpl(payload);

        Assertions.assertEquals(ProtocolVersion.V1, header.getProtocolVersion());
        Assertions.assertEquals(FirmwareVersion.V1, header.getFirmwareVersion());
        Assertions.assertEquals(MqttUpLinkFrameTypeImpl.UPLINK_FRAME_TYPE_ACK, header.getMqttUpLinkFrameType());
        Assertions.assertEquals(255, header.getPayloadCountID());
        Assertions.assertEquals(0, header.getPayloadLength());
        Assertions.assertEquals("909192939495", header.getSpotID());
    }
}
