package br.ufmg.watchdogs.server.unit.mqtt.downlink.payload;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.impl.MqttDownLinkAnimalSyncPayloadParserImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MqttDownLinkAnimalSyncPayloadParserImplTest {

    @Test
    void shouldParseAACKFramePayload() {

        MqttDownLinkAnimalSyncPayloadParserImpl animalSyncFrameParser = new MqttDownLinkAnimalSyncPayloadParserImpl(List.of(
                "f1a2a3a4a5a6",
                "f1b2b3b4b5b6"
        ));

        byte[] byteArray = animalSyncFrameParser.toByteArray();

        Assertions.assertEquals(MqttDownLinkAnimalSyncPayloadParserImpl.ANIMAL_ID_LENGTH * 2 + 1, byteArray.length);
        Assertions.assertEquals((byte) 0b00000010, byteArray[0]);
        Assertions.assertEquals((byte) 0b11110001, byteArray[1]);
        Assertions.assertEquals((byte) 0b10100010, byteArray[2]);
        Assertions.assertEquals((byte) 0b10100011, byteArray[3]);
        Assertions.assertEquals((byte) 0b10100100, byteArray[4]);
        Assertions.assertEquals((byte) 0b10100101, byteArray[5]);
        Assertions.assertEquals((byte) 0b10100110, byteArray[6]);
        Assertions.assertEquals((byte) 0b11110001, byteArray[7]);
        Assertions.assertEquals((byte) 0b10110010, byteArray[8]);
        Assertions.assertEquals((byte) 0b10110011, byteArray[9]);
        Assertions.assertEquals((byte) 0b10110100, byteArray[10]);
        Assertions.assertEquals((byte) 0b10110101, byteArray[11]);
        Assertions.assertEquals((byte) 0b10110110, byteArray[12]);
    }
}

