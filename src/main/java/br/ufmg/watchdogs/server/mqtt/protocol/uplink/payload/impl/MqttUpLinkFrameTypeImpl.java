package br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.impl;

import br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.MqttUpLinkFrameType;

public enum MqttUpLinkFrameTypeImpl implements MqttUpLinkFrameType {

    UPLINK_FRAME_TYPE_ACK(0b0000),
    UPLINK_FRAME_TYPE_SPOT_SYNC(0b0001),
    UPLINK_FRAME_TYPE_ANIMAL_SYNC(0b0010),
    UPLINK_FRAME_TYPE_LOG(0b0011),
    UPLINK_FRAME_TYPE_FOOD_RELEASE(0b0100);

    private final Integer type;

    MqttUpLinkFrameTypeImpl(Integer type) {
        this.type = type;
    }

    @Override
    public Integer getType() {
        return type;
    }
}
