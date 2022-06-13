package br.ufmg.watchdogs.server.mqtt.client;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.impl.MqttUpLinkMessageImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.service.MqttUpLinkService;
import org.eclipse.paho.client.mqttv3.MqttCallback;

public interface MqttSubListenerAdapter extends MqttCallback {

    public void listen();
    public MqttUpLinkService getMqttUpLinkService(MqttUpLinkMessageImpl mqttUplinkMessageImpl);
}
