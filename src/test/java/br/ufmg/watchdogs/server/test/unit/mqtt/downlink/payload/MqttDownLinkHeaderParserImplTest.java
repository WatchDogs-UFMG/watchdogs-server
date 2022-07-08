package br.ufmg.watchdogs.server.test.unit.mqtt.downlink.payload;

import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.impl.MqttDownLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.parser.impl.MqttDownLinkHeaderParserImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MqttDownLinkHeaderParserImplTest {

    @Test
    void shouldParseACKFrameHeader() {

        byte[] payload = {
                (byte) 0b10101010,
                (byte) 0b10101010
        };

        MqttDownLinkHeaderParserImpl header = new MqttDownLinkHeaderParserImpl(payload.length, MqttDownLinkFrameTypeImpl.DOWNLINK_FRAME_TYPE_ACK);
        byte[] byteArray = header.toByteArray();

        Assertions.assertEquals(0b0, byteArray[0]);
        Assertions.assertEquals(0b0, byteArray[1]);
        Assertions.assertEquals(0b00000010, byteArray[2]);
    }

    @Test
    void shouldParseSpotSyncFrameHeader() {

        byte[] payload = { (byte) 0b10101010 };

        MqttDownLinkHeaderParserImpl header = new MqttDownLinkHeaderParserImpl(payload.length, MqttDownLinkFrameTypeImpl.DOWNLINK_FRAME_TYPE_SPOT_SYNC);
        byte[] byteArray = header.toByteArray();

        Assertions.assertEquals(0b0, byteArray[0]);
        Assertions.assertEquals(0b00010000, byteArray[1]);
        Assertions.assertEquals(0b00000001, byteArray[2]);
    }

    @Test
    void shouldParseAnimalSyncFrameHeader() {

        byte[] payload = {
                0b00000001,
                0b00000001,
                0b01111111,
                0b00111111,
                0b00011111,
                0b00001111,
                0b00000111,
        };

        MqttDownLinkHeaderParserImpl header = new MqttDownLinkHeaderParserImpl(payload.length, MqttDownLinkFrameTypeImpl.DOWNLINK_FRAME_TYPE_ANIMAL_SYNC);
        byte[] byteArray = header.toByteArray();

        Assertions.assertEquals(0b0, byteArray[0]);
        Assertions.assertEquals(0b00100000, byteArray[1]);
        Assertions.assertEquals(0b00000111, byteArray[2]);
    }
}
