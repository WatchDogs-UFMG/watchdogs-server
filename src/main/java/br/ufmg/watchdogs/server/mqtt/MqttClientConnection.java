package br.ufmg.watchdogs.server.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MqttClientConnection {

    private static final String MQTT_DEV_BROKER = "tcp://127.0.0.1:7892";
    private static final String MQTT_PROD_BROKER = "tcp://watchdogs_mqtt_broker:7892";
    private static final String MQTT_SERVER_CLIENT_ID = "watchdogs_ufmg_server";
    private static final Integer MQTT_BROKER_QOS = 1;
    private static final MemoryPersistence MQTT_MEMORY_PERSISTENCE = new MemoryPersistence();
    private static Environment env;

    private MqttClient mqttClient;
    private MqttConnectOptions mqttConnectionOptions;

    @Autowired
    public MqttClientConnection(Environment env) {
        this.env = env;
        this.connect();
        System.out.println("MQTT client connected!");
    }

    private void connect() {

        try {

            this.mqttConnectionOptions = new MqttConnectOptions();
            this.mqttConnectionOptions.setCleanSession(true);

            this.mqttClient = new MqttClient(
                    this.getBrokerName(),
                    MqttClientConnection.MQTT_SERVER_CLIENT_ID,
                    MqttClientConnection.MQTT_MEMORY_PERSISTENCE
            );

            this.mqttClient.connect(this.mqttConnectionOptions);

        } catch(MqttException e) {

            System.out.println("MQTT Exception reason: " + e.getReasonCode());
            System.out.println("MQTT Exception message: " + e.getMessage());
            System.out.println("MQTT Exception cause: " + e.getCause());

            e.printStackTrace();

            System.exit(0);
        }
    }

    public void keepSendingTestMessages() {

        try {

            for (int i = 0; i >= 0; i++) {

                Thread.sleep(1000);
                this.publish(("Mensagem enviada: " + i).getBytes(), "/watchdogs/uplink/ack");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean publish(byte[] payload, String topic) {

        try {

            System.out.println("Publishing message: " + payload);

            MqttMessage mqttMessage = new MqttMessage(payload);
            mqttMessage.setQos(MqttClientConnection.MQTT_BROKER_QOS);

            this.mqttClient.publish(topic, mqttMessage);

            System.out.println("Message published");

            return true;

        } catch (MqttException e) {
            return false;
        }
    }

    private String getBrokerName() {

        if (Arrays.stream(env.getActiveProfiles()).anyMatch(profile -> profile.equals("dev"))) {
            return MqttClientConnection.MQTT_DEV_BROKER;
        } else if (Arrays.stream(env.getActiveProfiles()).anyMatch(profile ->  profile.equals("prod"))){
            return MqttClientConnection.MQTT_PROD_BROKER;
        } else {
            return MqttClientConnection.MQTT_PROD_BROKER;
        }
    }
}
