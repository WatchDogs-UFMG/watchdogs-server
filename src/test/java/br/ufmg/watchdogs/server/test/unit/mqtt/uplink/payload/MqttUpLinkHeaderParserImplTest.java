package br.ufmg.watchdogs.server.test.unit.mqtt.uplink.payload;

import br.ufmg.watchdogs.server.api.util.MyDateTimeFormatterUtil;
import br.ufmg.watchdogs.server.mqtt.protocol.FirmwareVersion;
import br.ufmg.watchdogs.server.mqtt.protocol.ProtocolVersion;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.impl.MqttUpLinkHeaderParserImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MqttUpLinkHeaderParserImplTest {

    @Test
    void shouldParseMinDateTimeACKFrameHeader() {

        byte[] payload = {
                (byte) 0b00000000,
                (byte) 0b00000000,
                (byte) 0b11111111,
                (byte) 0b00000000,
                (byte) 0b00000000,
                (byte) 0b00000000,
                (byte) 0b10010000,
                (byte) 0b10010001,
                (byte) 0b10010010,
                (byte) 0b10010011,
                (byte) 0b10010100,
                (byte) 0b10010101,
                (byte) 0b00000100,
                (byte) 0b00100001,
                (byte) 0b00010000,
                (byte) 0b00000001
        };

        MqttUpLinkHeaderParserImpl header = new MqttUpLinkHeaderParserImpl(payload);

        Assertions.assertEquals(ProtocolVersion.V1, header.getProtocolVersion());
        Assertions.assertEquals(FirmwareVersion.V1, header.getFirmwareVersion());
        Assertions.assertEquals(MqttUpLinkFrameTypeImpl.UPLINK_FRAME_TYPE_ACK, header.getMqttUpLinkFrameType());
        Assertions.assertEquals(255, header.getPayloadCountID());
        Assertions.assertEquals(0, header.getPayloadLength());
        Assertions.assertEquals("909192939495", header.getSpotID());
        Assertions.assertEquals("01/01/0001 01:01:00", header.getTimestamp().format(MyDateTimeFormatterUtil.FORMATTER));
    }


    @Test
    void shouldParseMaxDateTimeACKFrameHeader() {

        byte[] payload = {
                (byte) 0b00000000,
                (byte) 0b00000000,
                (byte) 0b11111111,
                (byte) 0b00000000,
                (byte) 0b00000000,
                (byte) 0b00000000,
                (byte) 0b10010000,
                (byte) 0b10010001,
                (byte) 0b10010010,
                (byte) 0b10010011,
                (byte) 0b10010100,
                (byte) 0b10010101,
                (byte) 0b11101110,
                (byte) 0b11111111,
                (byte) 0b11001111,
                (byte) 0b11111111
        };

        MqttUpLinkHeaderParserImpl header = new MqttUpLinkHeaderParserImpl(payload);

        Assertions.assertEquals(ProtocolVersion.V1, header.getProtocolVersion());
        Assertions.assertEquals(FirmwareVersion.V1, header.getFirmwareVersion());
        Assertions.assertEquals(MqttUpLinkFrameTypeImpl.UPLINK_FRAME_TYPE_ACK, header.getMqttUpLinkFrameType());
        Assertions.assertEquals(255, header.getPayloadCountID());
        Assertions.assertEquals(0, header.getPayloadLength());
        Assertions.assertEquals("909192939495", header.getSpotID());
        Assertions.assertEquals("31/12/4095 23:59:00", header.getTimestamp().format(MyDateTimeFormatterUtil.FORMATTER));
    }
}
