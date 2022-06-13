package br.ufmg.watchdogs.server.mqtt.uplink.service;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkMessageImpl;

import java.util.Arrays;

public interface MqttUpLinkService {

    void process(MqttUpLinkMessageImpl message, String topic);
    MqttUpLinkFrameTypeImpl upLinkFrameType();

    default void log(MqttUpLinkMessageImpl message, String topic) {
        System.out.println("Sending message in the topic: " + topic);
        System.out.println("Frame type: " + message.getHeader().getMqttUpLinkFrameType().name());
        System.out.println("Payload: [ " + Arrays.toString(message.getPayload()) + " ]");
    }
}
