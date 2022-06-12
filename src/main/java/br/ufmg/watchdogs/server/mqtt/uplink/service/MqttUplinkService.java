package br.ufmg.watchdogs.server.mqtt.uplink.service;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUplinkMessage;

import java.util.Arrays;

public interface MqttUplinkService {

    void process(MqttUplinkMessage message, String topic);
    MqttUpLinkFrameType upLinkFrameType();

    default void log(MqttUplinkMessage message, String topic) {
        System.out.println("Received message in the topic: " + topic);
        System.out.println("Frame type: " + message.getHeader().getMqttUpLinkFrameType().name());
        System.out.println("Payload: [ " + Arrays.toString(message.getPayload()) + " ]");
    }
}
