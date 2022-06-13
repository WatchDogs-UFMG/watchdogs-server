package br.ufmg.watchdogs.server.unit.mqtt;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.MqttDownLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.MqttDownLinkHeaderParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MqttDownLinkHeaderParserTest {

    @Test
    void shouldParseACKFrameHeader() {

        byte[] payload = new byte[0];

        MqttDownLinkHeaderParser header = new MqttDownLinkHeaderParser(payload.length, MqttDownLinkFrameType.DOWNLINK_FRAME_TYPE_ACK);
        byte[] byteArray = header.toByteArray();

        Assertions.assertEquals(0b0, byteArray[0]);
        Assertions.assertEquals(0b0, byteArray[1]);
        Assertions.assertEquals(0b0, byteArray[1]);
    }

    @Test
    void shouldParseSpotSyncFrameHeader() {

        byte[] payload = { (byte) 0b10101010 };

        MqttDownLinkHeaderParser header = new MqttDownLinkHeaderParser(payload.length, MqttDownLinkFrameType.DOWNLINK_FRAME_TYPE_SPOT_SYNC);
        byte[] byteArray = header.toByteArray();

        Assertions.assertEquals(0b0, byteArray[0]);
        Assertions.assertEquals(0b00010000, byteArray[1]);
        Assertions.assertEquals(0b00000001, byteArray[2]);
    }

    @Test
    void shouldParseAnimalSyncFrameHeader() {

        byte[] payload = {
                0b00000000,
                0b00000001,
                0b01111111,
                0b00111111,
                0b00011111,
                0b00001111,
                0b00000111,
                0b00000011,
        };

        MqttDownLinkHeaderParser header = new MqttDownLinkHeaderParser(payload.length, MqttDownLinkFrameType.DOWNLINK_FRAME_TYPE_ANIMAL_SYNC);
        byte[] byteArray = header.toByteArray();

        Assertions.assertEquals(0b0, byteArray[0]);
        Assertions.assertEquals(0b00100000, byteArray[1]);
        Assertions.assertEquals(0b00001000, byteArray[2]);
    }
}
