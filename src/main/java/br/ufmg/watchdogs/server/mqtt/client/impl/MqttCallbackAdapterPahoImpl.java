package br.ufmg.watchdogs.server.mqtt.client.impl;

import br.ufmg.watchdogs.server.mqtt.client.MqttClientAdapter;
import br.ufmg.watchdogs.server.mqtt.client.MqttCallbackAdapter;
import br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.impl.MqttUpLinkMessageImpl;
import br.ufmg.watchdogs.server.mqtt.protocol.uplink.service.MqttUpLinkService;
import br.ufmg.watchdogs.server.mqtt.protocol.uplink.service.impl.*;
import br.ufmg.watchdogs.server.mqtt.protocol.uplink.topic.impl.MqttUpLinkTopicImpl;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MqttCallbackAdapterPahoImpl implements MqttCallbackAdapter, MqttCallback {

    private final MqttClientAdapter mqttClientConnector;
    private final MqttUpLinkService mqttUpLinkService;

    @Autowired
    public MqttCallbackAdapterPahoImpl(
            MqttClientAdapterPahoImpl mqttClientConnector,
            MqttUplinkServiceImpl mqttUplinkServiceObserver
    ) {
        this.mqttClientConnector = mqttClientConnector;
        this.mqttUpLinkService = mqttUplinkServiceObserver;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost with broker!");
        throwable.printStackTrace();
        this.mqttClientConnector.connect();
        this.listen();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {

        mqttUpLinkService.identify(
                new MqttUpLinkMessageImpl(mqttMessage.getPayload()),
                topic
        );
    }

    @Override
    public void listen() {

        this.mqttClientConnector.setSubListenerCallback(this);

        Arrays.stream(MqttUpLinkTopicImpl.values()).forEach(topic -> {

            System.out.print("Tying to subscribe to topic: " + topic.getTopicName() + "... ");
            this.mqttClientConnector.subscribe(topic.getTopicName(), topic.getTopicQoS());
            System.out.println("Done!");
        });
    }

    @Override
    public Object getCallback() {
        return this;
    }
}
