package br.ufmg.watchdogs.server.mqtt.uplink.service.impl;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUplinkMessage;
import br.ufmg.watchdogs.server.mqtt.uplink.service.MqttUplinkService;
import org.springframework.stereotype.Service;

@Service
public class MqttUplinkAnimalSyncServiceImpl implements MqttUplinkService {

    @Override
    public void process(MqttUplinkMessage message, String topic) {
        this.log(message, topic);
    }

    @Override
    public MqttUpLinkFrameType upLinkFrameType() {
        return MqttUpLinkFrameType.UPLINK_FRAME_TYPE_ANIMAL_SYNC;
    }
}
