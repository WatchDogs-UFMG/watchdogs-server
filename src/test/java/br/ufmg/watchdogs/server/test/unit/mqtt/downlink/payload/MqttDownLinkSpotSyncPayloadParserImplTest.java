package br.ufmg.watchdogs.server.test.unit.mqtt.downlink.payload;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.impl.MqttDownLinkSpotSyncPayloadParserImpl;
import br.ufmg.watchdogs.server.util.BitWiseUtil;
import br.ufmg.watchdogs.server.util.DateTimeFormatterUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class MqttDownLinkSpotSyncPayloadParserImplTest {

    @Test
    void shouldParseASpotSyncFramePayload() {

        final LocalDateTime currentDateTime = DateTimeFormatterUtil.parseFromFormattedString("12/12/2012 12:12:12");

        MqttDownLinkSpotSyncPayloadParserImpl spotSyncFrameParser = new MqttDownLinkSpotSyncPayloadParserImpl(
                true,
                false,
                true,
                false,
                false,
                true,
                false,
                true,
                currentDateTime
        );

        // 10100101
        // 00101000 -> 40
        // 10100101 -> 165
        // 00101010 -> 42 10100010
        // 10011111 -> 159
        // 01101000 -> 104

        byte[] byteArray = spotSyncFrameParser.toByteArray();

        Long parsedSecond = BitWiseUtil.extractLongValue(
                byteArray,
                MqttDownLinkSpotSyncPayloadParserImpl.SECOND_START_BIT,
                MqttDownLinkSpotSyncPayloadParserImpl.SECOND_END_BIT
        );

        Long parsedMinute = BitWiseUtil.extractLongValue(
                byteArray,
                MqttDownLinkSpotSyncPayloadParserImpl.MINUTE_START_BIT,
                MqttDownLinkSpotSyncPayloadParserImpl.MINUTE_END_BIT
        );

        Long parsedHour = BitWiseUtil.extractLongValue(
                byteArray,
                MqttDownLinkSpotSyncPayloadParserImpl.HOUR_START_BIT,
                MqttDownLinkSpotSyncPayloadParserImpl.HOUR_END_BIT
        );

        Long parsedDay = BitWiseUtil.extractLongValue(
                byteArray,
                MqttDownLinkSpotSyncPayloadParserImpl.DAY_START_BIT,
                MqttDownLinkSpotSyncPayloadParserImpl.DAY_END_BIT
        );

        Long parsedMonth = BitWiseUtil.extractLongValue(
                byteArray,
                MqttDownLinkSpotSyncPayloadParserImpl.MONTH_START_BIT,
                MqttDownLinkSpotSyncPayloadParserImpl.MONTH_END_BIT
        );

        Long parsedYear = BitWiseUtil.extractLongValue(
                byteArray,
                MqttDownLinkSpotSyncPayloadParserImpl.YEAR_START_BIT,
                MqttDownLinkSpotSyncPayloadParserImpl.YEAR_END_BIT
        );

        String formattedDateTimeString = DateTimeFormatterUtil.getFormattedDateTimeString(
                parsedSecond,
                parsedMinute,
                parsedHour,
                parsedDay,
                parsedMonth,
                parsedYear
        );

        LocalDateTime parsedLocalDateTime = DateTimeFormatterUtil.parseFromFormattedString(formattedDateTimeString);

        Assertions.assertEquals(6, byteArray.length);
        Assertions.assertEquals((byte) 0b10100101, byteArray[0]);
        Assertions.assertEquals(currentDateTime.getSecond(), parsedLocalDateTime.getSecond());
        Assertions.assertEquals(currentDateTime.getMinute(), parsedLocalDateTime.getMinute());
        Assertions.assertEquals(currentDateTime.getHour(), parsedLocalDateTime.getHour());
        Assertions.assertEquals(currentDateTime.getDayOfMonth(), parsedLocalDateTime.getDayOfMonth());
        Assertions.assertEquals(currentDateTime.getMonth(), parsedLocalDateTime.getMonth());
        Assertions.assertEquals(currentDateTime.getYear(), parsedLocalDateTime.getYear());
    }
}
