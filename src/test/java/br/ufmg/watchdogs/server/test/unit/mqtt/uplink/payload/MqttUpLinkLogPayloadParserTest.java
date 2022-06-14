package br.ufmg.watchdogs.server.test.unit.mqtt.uplink.payload;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.impl.LogEventTriggerImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.impl.MqttUpLinkLogPayloadParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MqttUpLinkLogPayloadParserTest {

    @Test
    void shouldParseAKitRestartLog() {

        byte[] payload = { (byte) 0b00000000 };

        MqttUpLinkLogPayloadParser parser = new MqttUpLinkLogPayloadParser(payload);

        Assertions.assertEquals(LogEventTriggerImpl.KIT_RESTART, parser.getTriggerEvent());
    }

    @Test
    void shouldParseAAnimalIdentificationErrorLog() {

        byte[] payload = { (byte) 0b00000001 };

        MqttUpLinkLogPayloadParser parser = new MqttUpLinkLogPayloadParser(payload);

        Assertions.assertEquals(LogEventTriggerImpl.ANIMAL_IDENTIFICATION_ERROR, parser.getTriggerEvent());
    }

    @Test
    void shouldParseAFoodNotReleasedLog() {

        byte[] payload = { (byte) 0b00000010 };

        MqttUpLinkLogPayloadParser parser = new MqttUpLinkLogPayloadParser(payload);

        Assertions.assertEquals(LogEventTriggerImpl.FOOD_NOT_RELEASED, parser.getTriggerEvent());
    }

    @Test
    void shouldParseAWaterNotReleasedLog() {

        byte[] payload = { (byte) 0b00000011 };

        MqttUpLinkLogPayloadParser parser = new MqttUpLinkLogPayloadParser(payload);

        Assertions.assertEquals(LogEventTriggerImpl.WATER_NOT_RELEASED, parser.getTriggerEvent());
    }

    @Test
    void shouldParseAPhotoNotCapturedLog() {

        byte[] payload = { (byte) 0b00000100 };

        MqttUpLinkLogPayloadParser parser = new MqttUpLinkLogPayloadParser(payload);

        Assertions.assertEquals(LogEventTriggerImpl.PHOTO_NOT_CAPTURED, parser.getTriggerEvent());
    }

    @Test
    void shouldParseAFrameLostLog() {

        byte[] payload = { (byte) 0b00000101 };

        MqttUpLinkLogPayloadParser parser = new MqttUpLinkLogPayloadParser(payload);

        Assertions.assertEquals(LogEventTriggerImpl.FRAME_LOST, parser.getTriggerEvent());
    }

    @Test
    void shouldParseARandomHardwareProblemLog() {

        byte[] payload = { (byte) 0b00000110 };

        MqttUpLinkLogPayloadParser parser = new MqttUpLinkLogPayloadParser(payload);

        Assertions.assertEquals(LogEventTriggerImpl.RANDOM_HARDWARE_PROBLEM, parser.getTriggerEvent());
    }

    @Test
    void shouldParseALowBatteryLevelLog() {

        byte[] payload = { (byte) 0b00000111 };

        MqttUpLinkLogPayloadParser parser = new MqttUpLinkLogPayloadParser(payload);

        Assertions.assertEquals(LogEventTriggerImpl.LOW_BATTERY_LEVEL, parser.getTriggerEvent());
    }

    @Test
    void shouldParseAConnectionRefusedLog() {

        byte[] payload = { (byte) 0b00001000 };

        MqttUpLinkLogPayloadParser parser = new MqttUpLinkLogPayloadParser(payload);

        Assertions.assertEquals(LogEventTriggerImpl.CONNECTION_REFUSED, parser.getTriggerEvent());
    }

    @Test
    void shouldParseALowMemoryLog() {

        byte[] payload = { (byte) 0b00001001 };

        MqttUpLinkLogPayloadParser parser = new MqttUpLinkLogPayloadParser(payload);

        Assertions.assertEquals(LogEventTriggerImpl.LOW_MEMORY, parser.getTriggerEvent());
    }
}
