package br.ufmg.watchdogs.server.mqtt.downlink.service.impl;

import br.ufmg.watchdogs.server.mqtt.client.impl.MqttClientConnectorPahoImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.MqttDownLinkMessage;
import br.ufmg.watchdogs.server.mqtt.downlink.service.MqttDownLinkService;
import br.ufmg.watchdogs.server.mqtt.downlink.topic.MqttDownLinkTopic;
import br.ufmg.watchdogs.server.mqtt.downlink.topic.MqttDownLinkTopics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MqttDownLinkServiceImpl implements MqttDownLinkService {

    private final MqttClientConnectorPahoImpl mqttClientConnectorPaho;

    @Autowired
    public MqttDownLinkServiceImpl(MqttClientConnectorPahoImpl mqttClientConnectorPaho) {
        this.mqttClientConnectorPaho = mqttClientConnectorPaho;
    }

    @Override
    public void publish(MqttDownLinkMessage message, MqttDownLinkTopics mqttDownLinkTopic, String spotID) {

        String topicName = mqttDownLinkTopic.getTopicName(spotID);
        Integer topicQoS = mqttDownLinkTopic.getTopicQoS();

        this.log(message, topicName);

        try {

            this.mqttClientConnectorPaho.publish( message.toByteArray(), topicName, topicQoS );

        } catch (IOException e) {
            throw new RuntimeException("Publish message on topic " + topicName +  " failed!", e);
        }
    }
}
