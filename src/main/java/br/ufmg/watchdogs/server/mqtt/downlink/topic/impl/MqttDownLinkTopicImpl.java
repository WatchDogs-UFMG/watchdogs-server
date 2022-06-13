package br.ufmg.watchdogs.server.mqtt.downlink.topic.impl;

import br.ufmg.watchdogs.server.mqtt.downlink.topic.MqttDownLinkTopic;

public enum MqttDownLinkTopicImpl implements MqttDownLinkTopic {

    DOWNLINK_TOPIC_ACK("/watchdogs/downlink/ack/{spotID}", 1),
    DOWNLINK_TOPIC_SPOT_SYNC("/watchdogs/downlink/spot-sync/{spotID}", 1),
    DOWNLINK_TOPIC_ANIMAL_SYNC("/watchdogs/downlink/animal-sync/{spotID}", 1);

    private final String topicName;
    private final Integer topicQoS;

    MqttDownLinkTopicImpl(String topicName, Integer topicQoS) {
        this.topicName = topicName;
        this.topicQoS = topicQoS;
    }

    @Override
    public String getTopicName(String spotID) {
        return this.topicName.replace("{spodID}", spotID);
    }

    @Override
    public Integer getTopicQoS() {
        return this.topicQoS;
    }
}
