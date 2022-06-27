package br.ufmg.watchdogs.server.mqtt.uplink.payload.parser;

public interface LogTriggerEvent {

    Integer getCode();
    String name();
}
