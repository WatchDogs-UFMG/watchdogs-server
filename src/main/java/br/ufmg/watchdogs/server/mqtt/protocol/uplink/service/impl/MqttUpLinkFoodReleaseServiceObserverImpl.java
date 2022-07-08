package br.ufmg.watchdogs.server.mqtt.protocol.uplink.service.impl;

import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.MqttDownLinkMessage;
import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.impl.MqttDownLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.impl.MqttDownLinkMessageImpl;
import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.parser.MqttDownLinkPayloadParser;
import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.parser.impl.MqttDownLinkAckPayloadParserImpl;
import br.ufmg.watchdogs.server.mqtt.protocol.downlink.service.MqttDownLinkService;
import br.ufmg.watchdogs.server.mqtt.protocol.downlink.service.impl.MqttDownLinkServiceImpl;
import br.ufmg.watchdogs.server.mqtt.protocol.downlink.topic.impl.MqttDownLinkTopicImpl;
import br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.MqttUpLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.MqttUpLinkMessage;
import br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.impl.MqttUpLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.protocol.uplink.service.MqttUpLinkServiceObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttUpLinkFoodReleaseServiceObserverImpl implements MqttUpLinkServiceObserver {

    private final MqttDownLinkService mqttDownLinkService;

    @Autowired
    public MqttUpLinkFoodReleaseServiceObserverImpl(MqttDownLinkServiceImpl mqttDownLinkService) {
        this.mqttDownLinkService = mqttDownLinkService;
    }

    @Override
    public void process(MqttUpLinkMessage upLinkMessage, String upLinkTopic) {

        MqttDownLinkPayloadParser payload = new MqttDownLinkAckPayloadParserImpl(upLinkMessage.getHeader().getPayloadCountID());
        MqttDownLinkMessage downLinkMessage = new MqttDownLinkMessageImpl(payload, MqttDownLinkFrameTypeImpl.DOWNLINK_FRAME_TYPE_ACK);

        this.mqttDownLinkService.publish(downLinkMessage, MqttDownLinkTopicImpl.DOWNLINK_TOPIC_ACK, upLinkMessage.getHeader().getSpotID());
    }

    @Override
    public MqttUpLinkFrameType upLinkFrameType() {
        return MqttUpLinkFrameTypeImpl.UPLINK_FRAME_TYPE_FOOD_RELEASE;
    }
}
