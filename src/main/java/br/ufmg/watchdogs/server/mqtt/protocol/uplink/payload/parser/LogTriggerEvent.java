package br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.parser;

public interface LogTriggerEvent {

    Integer getCode();
    String name();
}
