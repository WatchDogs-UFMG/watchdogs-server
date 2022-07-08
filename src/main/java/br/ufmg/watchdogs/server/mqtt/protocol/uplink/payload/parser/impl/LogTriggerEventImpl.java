package br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.parser.impl;

import br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.parser.LogTriggerEvent;

public enum LogTriggerEventImpl implements LogTriggerEvent {

    KIT_RESTART(0b00000000),
    ANIMAL_IDENTIFICATION_ERROR(0b00000001),
    FOOD_NOT_RELEASED(0b00000010),
    WATER_NOT_RELEASED(0b00000011),
    PHOTO_NOT_CAPTURED(0b00000100),
    FRAME_LOST(0b00000101),
    RANDOM_HARDWARE_PROBLEM(0b00000110),
    LOW_BATTERY_LEVEL(0b00000111),
    CONNECTION_REFUSED(0b00001000),
    LOW_MEMORY(0b00001001);

    private final Integer code;

    LogTriggerEventImpl(Integer code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }
}
