package br.ufmg.watchdogs.server.mqtt.downlink.service.impl;

import br.ufmg.watchdogs.server.mqtt.client.impl.MqttClientAdapterPahoImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.payload.impl.MqttDownLinkMessageImpl;
import br.ufmg.watchdogs.server.mqtt.downlink.service.MqttDownLinkService;
import br.ufmg.watchdogs.server.mqtt.downlink.topic.impl.MqttDownLinkTopicImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MqttDownLinkServiceImpl implements MqttDownLinkService {

    private final MqttClientAdapterPahoImpl mqttClientConnectorPaho;

    @Autowired
    public MqttDownLinkServiceImpl(MqttClientAdapterPahoImpl mqttClientConnectorPaho) {
        this.mqttClientConnectorPaho = mqttClientConnectorPaho;
    }

    @Override
    public void publish(MqttDownLinkMessageImpl message, MqttDownLinkTopicImpl mqttDownLinkTopic, String spotID) {

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
