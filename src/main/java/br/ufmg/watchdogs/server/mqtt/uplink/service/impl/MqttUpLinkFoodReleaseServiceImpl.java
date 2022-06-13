package br.ufmg.watchdogs.server.mqtt.uplink.service.impl;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkMessage;
import br.ufmg.watchdogs.server.mqtt.uplink.service.MqttUpLinkService;
import org.springframework.stereotype.Service;

@Service
public class MqttUpLinkFoodReleaseServiceImpl implements MqttUpLinkService {

    @Override
    public void process(MqttUpLinkMessage message, String topic) {
        this.log(message, topic);
    }

    @Override
    public MqttUpLinkFrameType upLinkFrameType() {
        return MqttUpLinkFrameType.UPLINK_FRAME_TYPE_FOOD_RELEASE;
    }
}
