package br.ufmg.watchdogs.server.mqtt.downlink.payload;

public enum MqttDownLinkFrameType {

    DOWNLINK_FRAME_TYPE_ACK(0b0000),
    DOWNLINK_FRAME_TYPE_SPOT_SYNC(0b0001),
    DOWNLINK_FRAME_TYPE_ANIMAL_SYNC(0b0010);

    private final Integer type;

    MqttDownLinkFrameType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
