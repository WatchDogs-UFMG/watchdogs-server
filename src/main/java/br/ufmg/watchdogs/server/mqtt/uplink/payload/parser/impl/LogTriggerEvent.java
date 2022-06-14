package br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.impl;

public enum LogTriggerEvent {
    KIT_RESTART,
    ANIMAL_IDENTIFICATION_ERROR,
    FOOD_NOT_RELEASED,
    WATER_NOT_RELEASED,
    PHOTO_NOT_CAPTURED,
    FRAME_LOST,
    RANDOM_HARDWARE_PROBLEM,
    LOW_BATTERY_LEVEL,
    CONNECTION_REFUSED,
    LOW_MEMORY
}
