package br.ufmg.watchdogs.server.mqtt.uplink.service;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkMessage;

import java.util.Arrays;

public interface MqttUpLinkService {

    void process(MqttUpLinkMessage message, String topic);
    MqttUpLinkFrameType upLinkFrameType();

    default void log(MqttUpLinkMessage message, String topic) {
        System.out.println("Sending message in the topic: " + topic);
        System.out.println("Frame type: " + message.getHeader().getMqttUpLinkFrameType().name());
        System.out.println("Payload: [ " + Arrays.toString(message.getPayload()) + " ]");
    }
}
