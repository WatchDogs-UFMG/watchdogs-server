package br.ufmg.watchdogs.server.mqtt.uplink;

import br.ufmg.watchdogs.server.mqtt.client.impl.MqttClientConnectorPahoImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.service.MqttUplinkService;
import br.ufmg.watchdogs.server.mqtt.uplink.service.impl.*;
import br.ufmg.watchdogs.server.mqtt.uplink.topic.MqttUpLinkTopics;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MqttSubHandle implements MqttCallback {

    private final MqttClientConnectorPahoImpl mqttClientConnector;
    private final List<MqttUplinkService> services = new ArrayList<>();

    @Autowired
    public MqttSubHandle(
            MqttClientConnectorPahoImpl mqttClientConnector,
            MqttUplinkAckServiceImpl uplinkAckService,
            MqttUplinkSpotSyncServiceImpl uplinkSpotSyncService,
            MqttUplinkAnimalSyncServiceImpl uplinkAnimalSyncService,
            MqttUplinkLogServiceImpl uplinkLogService,
            MqttUplinkFoodReleaseServiceImpl uplinkFoodReleaseService
    ) {

        this.mqttClientConnector = mqttClientConnector;
        this.services.add(uplinkAckService);
        this.services.add(uplinkSpotSyncService);
        this.services.add(uplinkAnimalSyncService);
        this.services.add(uplinkLogService);
        this.services.add(uplinkFoodReleaseService);

        this.listen();
    }

    private void listen() {
        this.mqttClientConnector.setSubListenerCallback(this);
        Arrays.stream(MqttUpLinkTopics.values()).forEach(topic -> {
            System.out.println("Tying to subscribe to topic: " + topic.getTopicName());
            this.mqttClientConnector.subscribe(topic.getTopicName(), topic.getTopicQoS());
            System.out.println("Subscribed!");
        });
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost with broker!");
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        System.out.println("Received message on topic: " + topic);
        System.out.println("Payload: " + Arrays.toString(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }
}
