package br.ufmg.watchdogs.server.mqtt.uplink.service.impl;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.MqttDownLinkMessage;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.impl.MqttDownLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.impl.MqttDownLinkMessageImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.MqttDownLinkPayloadParser;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.impl.MqttDownLinkAckPayloadParserImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.service.MqttDownLinkService;
import br.ufmg.watchdogs.server.mqtt.downlink.service.impl.MqttDownLinkServiceImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.topic.impl.MqttDownLinkTopicImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkMessageImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.service.MqttUpLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttUpLinkAckServiceImpl implements MqttUpLinkService {

    private final MqttDownLinkService mqttDownLinkService;

    @Autowired
    public MqttUpLinkAckServiceImpl(MqttDownLinkServiceImpl mqttDownLinkService) {
        this.mqttDownLinkService = mqttDownLinkService;
    }

    @Override
    public void process(MqttUpLinkMessageImpl message, String topic) {

        this.log(message, topic);

        MqttDownLinkPayloadParser payload = new MqttDownLinkAckPayloadParserImpl(message.getHeader().getPayloadCountID());
        MqttDownLinkMessage downLinkMessage = new MqttDownLinkMessageImpl(payload, MqttDownLinkFrameTypeImpl.DOWNLINK_FRAME_TYPE_ACK);

        this.mqttDownLinkService.publish(downLinkMessage, MqttDownLinkTopicImpl.DOWNLINK_TOPIC_ACK, message.getHeader().getSpotID());
    }

    @Override
    public MqttUpLinkFrameTypeImpl upLinkFrameType() {
        return MqttUpLinkFrameTypeImpl.UPLINK_FRAME_TYPE_ACK;
    }
}
