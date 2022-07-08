package br.ufmg.watchdogs.server.mqtt.protocol.uplink.service;

import br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.MqttUpLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.MqttUpLinkMessage;

public interface MqttUpLinkServiceObserver {

    void process(MqttUpLinkMessage upLinkMessage, String upLinkTopic);
    MqttUpLinkFrameType upLinkFrameType();
}
