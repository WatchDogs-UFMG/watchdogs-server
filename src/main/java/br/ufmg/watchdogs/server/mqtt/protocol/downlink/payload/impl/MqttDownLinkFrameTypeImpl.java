package br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.impl;

import br.ufmg.watchdogs.server.mqtt.protocol.downlink.payload.MqttDownLinkFrameType;

public enum MqttDownLinkFrameTypeImpl implements MqttDownLinkFrameType {

    DOWNLINK_FRAME_TYPE_ACK(0b0000),
    DOWNLINK_FRAME_TYPE_SPOT_SYNC(0b0001),
    DOWNLINK_FRAME_TYPE_ANIMAL_SYNC(0b0010);

    private final Integer type;

    MqttDownLinkFrameTypeImpl(Integer type) {
        this.type = type;
    }

    @Override
    public Integer getType() {
        return type;
    }
}
