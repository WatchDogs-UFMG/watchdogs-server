package br.ufmg.watchdogs.server.mqtt.uplink.service.impl;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkFrameTypeImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkMessageImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.service.MqttUpLinkService;
import org.springframework.stereotype.Service;

@Service
public class MqttUpLinkLogServiceImpl implements MqttUpLinkService {

    @Override
    public void process(MqttUpLinkMessageImpl message, String topic) {
        this.log(message, topic);
    }

    @Override
    public MqttUpLinkFrameTypeImpl upLinkFrameType() {
        return MqttUpLinkFrameTypeImpl.UPLINK_FRAME_TYPE_LOG;
    }
}
