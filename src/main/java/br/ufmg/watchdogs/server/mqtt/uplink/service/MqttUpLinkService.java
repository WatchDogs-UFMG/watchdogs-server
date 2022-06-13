package br.ufmg.watchdogs.server.mqtt.uplink.service;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkMessageImpl;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public interface MqttUpLinkService {

    void process(MqttUpLinkMessageImpl message, String topic);
    MqttUpLinkFrameTypeImpl upLinkFrameType();

    default void log(MqttUpLinkMessageImpl message, String topic) {
        System.out.println("Received message on topic: " + topic);
        System.out.println("Frame type: " + message.getHeader().getMqttUpLinkFrameType().name());
        System.out.println("Payload: " + Arrays.toString(message.getPayload()));
        System.out.println("Message: " + new String(message.getPayload(), StandardCharsets.UTF_8));
        System.out.println();
    }
}
