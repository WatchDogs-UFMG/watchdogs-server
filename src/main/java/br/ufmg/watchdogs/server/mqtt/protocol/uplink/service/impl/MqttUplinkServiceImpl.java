package br.ufmg.watchdogs.server.mqtt.protocol.uplink.service.impl;

import br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.MqttUpLinkFrameType;
import br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.MqttUpLinkMessage;
import br.ufmg.watchdogs.server.mqtt.protocol.uplink.service.MqttUpLinkService;
import br.ufmg.watchdogs.server.mqtt.protocol.uplink.service.MqttUpLinkServiceObserver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MqttUplinkServiceImpl implements MqttUpLinkService {


    private final List<MqttUpLinkServiceObserver> uplinkServices = new ArrayList<>();

    public MqttUplinkServiceImpl(
            MqttUpLinkAckServiceObserverImpl uplinkAckService,
            MqttUpLinkSpotSyncServiceObserverImpl uplinkSpotSyncService,
            MqttUpLinkAnimalSyncServiceObserverImpl uplinkAnimalSyncService,
            MqttUpLinkLogServiceObserverImpl uplinkLogService,
            MqttUpLinkFoodReleaseServiceObserverImpl uplinkFoodReleaseService
    ) {
        this.uplinkServices.add(uplinkAckService);
        this.uplinkServices.add(uplinkSpotSyncService);
        this.uplinkServices.add(uplinkAnimalSyncService);
        this.uplinkServices.add(uplinkLogService);
        this.uplinkServices.add(uplinkFoodReleaseService);
    }


    @Override
    public void identify(MqttUpLinkMessage upLinkMessage, String upLinkTopic) {

        this.log(upLinkMessage, upLinkTopic);

        this.uplinkServices.stream()
                .filter(service -> service.upLinkFrameType() == this.upLinkFrameType(upLinkMessage))
                .forEach(service -> service.process(upLinkMessage, upLinkTopic));
    }

    private MqttUpLinkFrameType upLinkFrameType(MqttUpLinkMessage upLinkMessage) {
        return upLinkMessage.getHeader().getMqttUpLinkFrameType();
    }
}
