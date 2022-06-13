package br.ufmg.watchdogs.server.mqtt.uplink.topic.impl;

import br.ufmg.watchdogs.server.mqtt.uplink.topic.MqttUpLinkTopic;

public enum MqttUpLinkTopicImpl implements MqttUpLinkTopic {

    UPLINK_TOPIC_ACK("/watchdogs/uplink/ack", 1),
    UPLINK_TOPIC_SPOT_SYNC("/watchdogs/uplink/spot-sync", 1),
    UPLINK_TOPIC_ANIMAL_SYNC("/watchdogs/uplink/animal-sync", 1),
    UPLINK_TOPIC_LOG("/watchdogs/uplink/log", 1),
    UPLINK_TOPIC_FOOD_RELEASE("/watchdogs/uplink/food-release", 1);

    private final String topicName;
    private final Integer topicQoS;

    MqttUpLinkTopicImpl(String topicName, Integer topicQoS) {
        this.topicName = topicName;
        this.topicQoS = topicQoS;
    }

    public String getTopicName() {
        return topicName;
    }

    public Integer getTopicQoS() {
        return topicQoS;
    }
}
