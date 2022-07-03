package br.ufmg.watchdogs.server.mqtt.uplink.service.impl;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.MqttDownLinkMessage;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.impl.MqttDownLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.impl.MqttDownLinkMessageImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.MqttDownLinkPayloadParser;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.impl.MqttDownLinkAnimalSyncPayloadParserImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.service.MqttDownLinkService;
import br.ufmg.watchdogs.server.mqtt.downlink.service.impl.MqttDownLinkServiceImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.topic.impl.MqttDownLinkTopicImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkMessage;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.service.MqttUpLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MqttUpLinkAnimalSyncServiceImpl implements MqttUpLinkService {

    private final MqttDownLinkService mqttDownLinkService;

    @Autowired
    public MqttUpLinkAnimalSyncServiceImpl(MqttDownLinkServiceImpl mqttDownLinkService) {
        this.mqttDownLinkService = mqttDownLinkService;
    }

    @Override
    public void process(MqttUpLinkMessage upLinkMessage, String upLinkTopic) {

        this.log(upLinkMessage, upLinkTopic);

        List<String> spotAnimalList = this.getSpotAnimalList(upLinkMessage.getHeader().getSpotID());

        MqttDownLinkPayloadParser payload = new MqttDownLinkAnimalSyncPayloadParserImpl(spotAnimalList);
        MqttDownLinkMessage downLinkMessage = new MqttDownLinkMessageImpl(
                payload,
                MqttDownLinkFrameTypeImpl.DOWNLINK_FRAME_TYPE_ANIMAL_SYNC
        );

        this.mqttDownLinkService.publish(
                downLinkMessage,
                MqttDownLinkTopicImpl.DOWNLINK_TOPIC_ANIMAL_SYNC,
                upLinkMessage.getHeader().getSpotID()
        );
    }

    @Override
    public MqttUpLinkFrameType upLinkFrameType() {
        return MqttUpLinkFrameTypeImpl.UPLINK_FRAME_TYPE_ANIMAL_SYNC;
    }

    private List<String> getSpotAnimalList(String spotID) {
        return List.of(
                "f1a2a3a4a5a6",
                "f1b2b3b4b5b6",
                "f1c2c3c4c5c6"
        );
    }
}
