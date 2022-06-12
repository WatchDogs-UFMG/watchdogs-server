package br.ufmg.watchdogs.server.mqtt.uplink.payload;

public enum MqttUpLinkFrameType {
    UPLINK_FRAME_TYPE_ACK,
    UPLINK_FRAME_TYPE_SPOT_SYNC,
    UPLINK_FRAME_TYPE_ANIMAL_SYNC,
    UPLINK_FRAME_TYPE_LOG,
    UPLINK_FRAME_TYPE_FOOD_RELEASE
}
