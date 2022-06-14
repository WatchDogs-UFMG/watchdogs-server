package br.ufmg.watchdogs.server.test.unit.mqtt.uplink.payload;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.impl.MqttUpLinkFoodReleasePayloadParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MqttUpLinkFoodReleasePayloadParserTest {

    @Test
    void shouldParseAKitRestartLog() {

        byte[] payload = {
                (byte) 0b11110001, // f1
                (byte) 0b11110010, // f2
                (byte) 0b11110011, // f3
                (byte) 0b11110100, // f4
                (byte) 0b11110101, // f5
                (byte) 0b11110110, // f6
                (byte) 0b01100100, // 100
                (byte) 0b01001011, // 75
                (byte) 0b10100000,
                (byte) 0b10100001,
                (byte) 0b10100010,
                (byte) 0b10100011,
                (byte) 0b10100100,
                (byte) 0b10100101,
                (byte) 0b10100110,
                (byte) 0b10100111,
                (byte) 0b10101000,
                (byte) 0b10101001
        };

        byte[] photo = {
                (byte) 0b10100000,
                (byte) 0b10100001,
                (byte) 0b10100010,
                (byte) 0b10100011,
                (byte) 0b10100100,
                (byte) 0b10100101,
                (byte) 0b10100110,
                (byte) 0b10100111,
                (byte) 0b10101000,
                (byte) 0b10101001
        };

        MqttUpLinkFoodReleasePayloadParser parser = new MqttUpLinkFoodReleasePayloadParser(payload);

        Assertions.assertEquals("f1f2f3f4f5f6", parser.getAnimalID());
        Assertions.assertEquals(100, parser.getFoodLevel());
        Assertions.assertEquals(75, parser.getWaterLevel());
        Assertions.assertEquals(photo.length, parser.getPhoto().length);
        Assertions.assertEquals(Arrays.toString(photo), Arrays.toString(parser.getPhoto()));
    }
}
