package br.ufmg.watchdogs.server.test.unit.mqtt.downlink.payload;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.impl.MqttDownLinkSpotSyncPayloadParserImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MqttDownLinkSpotSyncPayloadParserImplTest {

    @Test
    void shouldParseASpotSyncFramePayload() {

        MqttDownLinkSpotSyncPayloadParserImpl spotSyncFrameParser = new MqttDownLinkSpotSyncPayloadParserImpl(
                true,
                false,
                true,
                false,
                false,
                true,
                false,
                true
        );

        byte[] byteArray = spotSyncFrameParser.toByteArray();

        Assertions.assertEquals(1, byteArray.length);
        Assertions.assertEquals((byte) 0b10100101, byteArray[0]);

    }
}
