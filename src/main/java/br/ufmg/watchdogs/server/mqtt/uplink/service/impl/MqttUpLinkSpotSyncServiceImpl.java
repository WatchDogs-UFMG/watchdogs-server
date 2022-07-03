package br.ufmg.watchdogs.server.mqtt.uplink.service.impl;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.MqttDownLinkMessage;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.impl.MqttDownLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.impl.MqttDownLinkMessageImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.MqttDownLinkPayloadParser;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.impl.MqttDownLinkSpotSyncPayloadParserImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.service.MqttDownLinkService;
import br.ufmg.watchdogs.server.mqtt.downlink.service.impl.MqttDownLinkServiceImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.topic.impl.MqttDownLinkTopicImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkMessage;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.service.MqttUpLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttUpLinkSpotSyncServiceImpl implements MqttUpLinkService {

    private final MqttDownLinkService mqttDownLinkService;

    @Autowired
    public MqttUpLinkSpotSyncServiceImpl(MqttDownLinkServiceImpl mqttDownLinkService) {
        this.mqttDownLinkService = mqttDownLinkService;
    }

    @Override
    public void process(MqttUpLinkMessage upLinkMessage, String upLinkTopic) {

        this.log(upLinkMessage, upLinkTopic);

        MqttDownLinkPayloadParser payload = new MqttDownLinkSpotSyncPayloadParserImpl(
            this.getEnableSpot(),
            this.getEnableFoodRelease(),
            this.getEnableCam(),
            this.getEnableAnimalIdentification(),
            this.getEnablePresenceSensor(),
            this.getEnableFoodLevelSensor(),
            this.getEnableWaterLevelSensor(),
            this.getEnableWiFiPeriodicWakeUp()
        );

        MqttDownLinkMessage downLinkMessage = new MqttDownLinkMessageImpl(
                payload,
                MqttDownLinkFrameTypeImpl.DOWNLINK_FRAME_TYPE_SPOT_SYNC
        );

        this.mqttDownLinkService.publish(
                downLinkMessage,
                MqttDownLinkTopicImpl.DOWNLINK_TOPIC_SPOT_SYNC,
                upLinkMessage.getHeader().getSpotID()
        );
    }

    @Override
    public MqttUpLinkFrameType upLinkFrameType() {
        return MqttUpLinkFrameTypeImpl.UPLINK_FRAME_TYPE_SPOT_SYNC;
    }

    private boolean getEnableSpot() {
        return true;
    }

    private boolean getEnableFoodRelease() {
        return true;
    }

    private boolean getEnableCam() {
        return true;
    }

    private boolean getEnableAnimalIdentification() {
        return true;
    }

    private boolean getEnablePresenceSensor() {
        return true;
    }

    private boolean getEnableFoodLevelSensor() {
        return true;
    }

    private boolean getEnableWaterLevelSensor() {
        return true;
    }

    private boolean getEnableWiFiPeriodicWakeUp() {
        return true;
    }
}
