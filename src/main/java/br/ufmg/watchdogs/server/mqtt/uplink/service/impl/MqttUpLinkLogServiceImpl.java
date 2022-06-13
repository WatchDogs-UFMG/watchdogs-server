package br.ufmg.watchdogs.server.mqtt.uplink.service.impl;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.MqttDownLinkMessage;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.impl.MqttDownLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.impl.MqttDownLinkMessageImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.MqttDownLinkPayloadParser;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.impl.MqttDownLinkAckPayloadParserImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.service.MqttDownLinkService;
import br.ufmg.watchdogs.server.mqtt.downlink.topic.impl.MqttDownLinkTopicImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkMessage;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.service.MqttUpLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttUpLinkLogServiceImpl implements MqttUpLinkService {

    private final MqttDownLinkService mqttDownLinkService;

    @Autowired
    public MqttUpLinkLogServiceImpl(MqttDownLinkService mqttDownLinkService) {
        this.mqttDownLinkService = mqttDownLinkService;
    }

    @Override
    public void process(MqttUpLinkMessage upLinkMessage, String upLinkTopic) {

        this.log(upLinkMessage, upLinkTopic);

        MqttDownLinkPayloadParser payload = new MqttDownLinkAckPayloadParserImpl(upLinkMessage.getHeader().getPayloadCountID());
        MqttDownLinkMessage downLinkMessage = new MqttDownLinkMessageImpl(
                payload,
                MqttDownLinkFrameTypeImpl.DOWNLINK_FRAME_TYPE_ACK
        );

        this.mqttDownLinkService.publish(
                downLinkMessage,
                MqttDownLinkTopicImpl.DOWNLINK_TOPIC_ACK,
                upLinkMessage.getHeader().getSpotID()
        );
    }

    @Override
    public MqttUpLinkFrameType upLinkFrameType() {
        return MqttUpLinkFrameTypeImpl.UPLINK_FRAME_TYPE_LOG;
    }
}
