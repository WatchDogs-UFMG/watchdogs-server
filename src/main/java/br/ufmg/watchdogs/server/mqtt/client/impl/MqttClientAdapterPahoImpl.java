package br.ufmg.watchdogs.server.mqtt.client.impl;

import br.ufmg.watchdogs.server.mqtt.client.MqttClientAdapter;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

@Service
public class MqttClientAdapterPahoImpl implements MqttClientAdapter {

    private static final String MQTT_DEV_BROKER = "tcp://localhost:7893";
    private static final String MQTT_PROD_BROKER = "tcp://watchdogs_mqtt_broker:7892";
    private static final String MQTT_SERVER_CLIENT_ID = "watchdogs_ufmg_server";
    private static final MemoryPersistence MQTT_MEMORY_PERSISTENCE = new MemoryPersistence();

    private final Environment env;
    private MqttAsyncClient mqttClient;

    @Autowired
    public MqttClientAdapterPahoImpl(Environment env) {
        this.env = env;
    }

    @Autowired
    public void connect() {

        try {

            MqttConnectOptions mqttConnectionOptions = new MqttConnectOptions();
            mqttConnectionOptions.setCleanSession(true);

            this.mqttClient = new MqttAsyncClient(
                    this.getBrokerName(),
                    MqttClientAdapterPahoImpl.MQTT_SERVER_CLIENT_ID,
                    MqttClientAdapterPahoImpl.MQTT_MEMORY_PERSISTENCE
            );

            IMqttToken mqttToken = this.mqttClient.connect(mqttConnectionOptions);
            mqttToken.waitForCompletion();

            System.out.println("MQTT client connected!");

        } catch(MqttException e) {

            System.out.println("MQTT Exception reason: " + e.getReasonCode());
            System.out.println("MQTT Exception message: " + e.getMessage());
            System.out.println("MQTT Exception cause: " + e.getCause());

            e.printStackTrace();

            System.exit(0);
        }
    }

    @Override
    public void subscribe(String topicName, Integer topicQoS) {

        try {

            IMqttToken token = this.mqttClient.subscribe(topicName, topicQoS);
            token.waitForCompletion();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean publish(byte[] payload, String topicName, Integer topicQoS) {

        try {

            System.out.println("Publishing payload: " + Arrays.toString(payload));
            System.out.println("Publishing message: " + new String(payload, UTF_8));

            MqttMessage mqttMessage = new MqttMessage(payload);
            mqttMessage.setQos(topicQoS);

            IMqttDeliveryToken token = this.mqttClient.publish(topicName, mqttMessage);
            token.waitForCompletion();

            System.out.println("Message published");

            return true;

        } catch (MqttException e) {
            return false;
        }
    }

    @Override
    public void setSubListenerCallback(Object callback) {
        this.mqttClient.setCallback((MqttCallback) callback);
    }

    @Override
    public void keepSendingTestMessages() {

        try {

            for (int i = 0; i >= 0; i++) {

                Thread.sleep(10 * 1000);
                this.publish(("Message sending number: " + i).getBytes(), "/watchdogs/downlink/ack", 1);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String getBrokerName() {

        if (Arrays.asList(env.getActiveProfiles()).contains("dev")) {
            return MqttClientAdapterPahoImpl.MQTT_DEV_BROKER;
        } else if (Arrays.asList(env.getActiveProfiles()).contains("prod")){
            return MqttClientAdapterPahoImpl.MQTT_PROD_BROKER;
        } else {
            return MqttClientAdapterPahoImpl.MQTT_PROD_BROKER;
        }
    }
}
