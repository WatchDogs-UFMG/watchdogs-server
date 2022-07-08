package br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.parser.impl;

import br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.parser.LogTriggerEvent;
import br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.parser.MqttUpLinkPayloadParser;
import br.ufmg.watchdogs.server.util.BitWiseUtil;

import java.util.Arrays;

public class MqttUpLinkLogPayloadParser implements MqttUpLinkPayloadParser {

    private static final Integer TRIGGER_EVENT_START_BIT = 0;
    private static final Integer TRIGGER_EVENT_END_BIT = 8;

    private final LogTriggerEvent triggerEvent;

    public MqttUpLinkLogPayloadParser(byte[] payload) {
        this.triggerEvent = this.parseTriggerEvent(payload);
    }

    private LogTriggerEvent parseTriggerEvent(byte[] payload) {

        Integer eventTriggerCode = BitWiseUtil.extractIntegerValue(payload, TRIGGER_EVENT_START_BIT, TRIGGER_EVENT_END_BIT);

        return Arrays.stream(LogTriggerEventImpl.values())
                .filter(event -> event.getCode().equals(eventTriggerCode))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Event trigger not found with code " + eventTriggerCode + "!"));
    }

    public LogTriggerEvent getTriggerEvent() {
        return triggerEvent;
    }
}
