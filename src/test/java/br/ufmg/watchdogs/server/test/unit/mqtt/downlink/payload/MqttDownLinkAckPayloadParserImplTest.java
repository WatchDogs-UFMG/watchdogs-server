package br.ufmg.watchdogs.server.test.unit.mqtt.downlink.payload;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.impl.MqttDownLinkAckPayloadParserImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MqttDownLinkAckPayloadParserImplTest {

    @Test
    void shouldParseAACKFramePayload() {

        MqttDownLinkAckPayloadParserImpl ackFrameParser = new MqttDownLinkAckPayloadParserImpl(0b111001010010);

        byte[] byteArray = ackFrameParser.toByteArray();

        Assertions.assertEquals(2, byteArray.length);
        Assertions.assertEquals((byte) 0b11100101, byteArray[0]);
        Assertions.assertEquals((byte) 0b00000010, byteArray[1]);
    }
}
