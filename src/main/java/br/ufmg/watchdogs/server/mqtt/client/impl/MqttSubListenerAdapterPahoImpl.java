package br.ufmg.watchdogs.server.mqtt.client.impl;

import br.ufmg.watchdogs.server.mqtt.client.MqttClientAdapter;
import br.ufmg.watchdogs.server.mqtt.client.MqttSubListenerAdapter;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkMessage;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkMessageImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.service.MqttUpLinkService;
import br.ufmg.watchdogs.server.mqtt.uplink.service.impl.*;
import br.ufmg.watchdogs.server.mqtt.uplink.topic.impl.MqttUpLinkTopicImpl;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MqttSubListenerAdapterPahoImpl implements MqttSubListenerAdapter {

    private final MqttClientAdapter mqttClientConnector;
    private final List<MqttUpLinkService> services = new ArrayList<>();

    @Autowired
    public MqttSubListenerAdapterPahoImpl(
            MqttClientAdapterPahoImpl mqttClientConnector,
            MqttUpLinkAckServiceImpl uplinkAckService,
            MqttUpLinkSpotSyncServiceImpl uplinkSpotSyncService,
            MqttUpLinkAnimalSyncServiceImpl uplinkAnimalSyncService,
            MqttUpLinkLogServiceImpl uplinkLogService,
            MqttUpLinkFoodReleaseServiceImpl uplinkFoodReleaseService
    ) {

        this.mqttClientConnector = mqttClientConnector;
        this.services.add(uplinkAckService);
        this.services.add(uplinkSpotSyncService);
        this.services.add(uplinkAnimalSyncService);
        this.services.add(uplinkLogService);
        this.services.add(uplinkFoodReleaseService);
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost with broker!");
        throwable.printStackTrace();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {

        MqttUpLinkMessage mqttUplinkMessageImpl = new MqttUpLinkMessageImpl(mqttMessage.getPayload());
        MqttUpLinkService service = this.getMqttUpLinkService(mqttUplinkMessageImpl);

        service.process(mqttUplinkMessageImpl, topic);
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
    public MqttUpLinkService getMqttUpLinkService(MqttUpLinkMessage mqttUplinkMessageImpl) {

        MqttUpLinkFrameType receivedMqttUpLinkFrameTypeImpl = mqttUplinkMessageImpl.getHeader().getMqttUpLinkFrameType();

        return this.services.stream()
                .filter(service -> service.upLinkFrameType() == receivedMqttUpLinkFrameTypeImpl)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find MqttUpLinkService to handle " + receivedMqttUpLinkFrameTypeImpl.name() + " frame type!"));
    }
}
