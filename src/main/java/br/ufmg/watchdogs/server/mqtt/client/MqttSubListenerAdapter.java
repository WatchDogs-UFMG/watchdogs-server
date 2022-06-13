package br.ufmg.watchdogs.server.mqtt.client;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.MqttUpLinkMessage;
import br.ufmg.watchdogs.server.mqtt.uplink.service.MqttUpLinkService;
import org.eclipse.paho.client.mqttv3.MqttCallback;

public interface MqttSubListenerAdapter extends MqttCallback {

    void listen();
    MqttUpLinkService getMqttUpLinkService(MqttUpLinkMessage mqttUplinkMessageImpl);
}
