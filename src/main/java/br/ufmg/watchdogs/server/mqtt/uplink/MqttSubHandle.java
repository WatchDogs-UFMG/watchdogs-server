package br.ufmg.watchdogs.server.mqtt.uplink;

import br.ufmg.watchdogs.server.mqtt.client.impl.MqttClientConnectorPahoImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkMessage;
import br.ufmg.watchdogs.server.mqtt.uplink.service.MqttUpLinkService;
import br.ufmg.watchdogs.server.mqtt.uplink.service.impl.*;
import br.ufmg.watchdogs.server.mqtt.uplink.topic.MqttUpLinkTopics;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MqttSubHandle implements MqttCallback {

    private final MqttClientConnectorPahoImpl mqttClientConnector;
    private final List<MqttUpLinkService> services = new ArrayList<>();

    @Autowired
    public MqttSubHandle(
            MqttClientConnectorPahoImpl mqttClientConnector,
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
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

        System.out.println("Received message on topic: " + topic);
        System.out.println("Payload: " + Arrays.toString(mqttMessage.getPayload()));
        System.out.println("Message: " + new String(mqttMessage.getPayload(), StandardCharsets.UTF_8));

        MqttUpLinkMessage mqttUplinkMessage = new MqttUpLinkMessage(mqttMessage.getPayload());
        MqttUpLinkService service = this.getMqttUpLinkService(mqttUplinkMessage);

        service.process(mqttUplinkMessage, topic);
    }

    public void listen() {

        this.mqttClientConnector.setSubListenerCallback(this);

        Arrays.stream(MqttUpLinkTopics.values()).forEach(topic -> {

            System.out.print("Tying to subscribe to topic: " + topic.getTopicName() + "... ");
            this.mqttClientConnector.subscribe(topic.getTopicName(), topic.getTopicQoS());
            System.out.println("Done!");
        });
    }

    private MqttUpLinkService getMqttUpLinkService(MqttUpLinkMessage mqttUplinkMessage) {

        MqttUpLinkFrameType receivedMqttUpLinkFrameType = mqttUplinkMessage.getHeader().getMqttUpLinkFrameType();

        return this.services.stream()
                .filter(service -> service.upLinkFrameType() == receivedMqttUpLinkFrameType)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find MqttUpLinkService to handle " + receivedMqttUpLinkFrameType.name() + " frame type!"));
    }
}
