package br.ufmg.watchdogs.server.mqtt.downlink.service;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.MqttDownLinkMessage;
import br.ufmg.watchdogs.server.mqtt.downlink.topic.MqttDownLinkTopics;

import java.util.Arrays;

public interface MqttDownLinkService {

    void publish(MqttDownLinkMessage message, MqttDownLinkTopics mqttDownLinkTopic, String spotID);

    default void log(MqttDownLinkMessage message, String topic) {
        System.out.println("Publishing message in the topic: " + topic);
        System.out.println("Frame type: " + message.getHeader().getMqttDownLinkFrameType().name());
        System.out.println("Payload: [ " + Arrays.toString(message.getPayload()) + " ]");
    }
}
