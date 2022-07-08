package br.ufmg.watchdogs.server.mqtt.client;

public interface MqttCallbackAdapter {

    void listen();
    Object getCallback();
}
