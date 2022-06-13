package br.ufmg.watchdogs.server.mqtt.downlink.payload.impl;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.MqttDownLinkPayloadParser;
import br.ufmg.watchdogs.server.mqtt.util.BitWiseUtil;

public class MqttDownLinkSpotSyncPayloadParserImpl implements MqttDownLinkPayloadParser {

    private final boolean enableSpot;
    private final boolean enableFoodRelease;
    private final boolean enableCam;
    private final boolean enableAnimalIdentification;
    private final boolean enablePresenceSensor;
    private final boolean enableFoodLevelSensor;
    private final boolean enableWaterLevelSensor;
    private final boolean enableWiFiPeriodicWakeUp;

    public MqttDownLinkSpotSyncPayloadParserImpl(
            boolean enableSpot,
            boolean enableFoodRelease,
            boolean enableCam,
            boolean enableAnimalIdentification,
            boolean enablePresenceSensor,
            boolean enableFoodLevelSensor,
            boolean enableWaterLevelSensor,
            boolean enableWiFiPeriodicWakeUp
    ) {
        this.enableSpot = enableSpot;
        this.enableFoodRelease = enableFoodRelease;
        this.enableCam = enableCam;
        this.enableAnimalIdentification = enableAnimalIdentification;
        this.enablePresenceSensor = enablePresenceSensor;
        this.enableFoodLevelSensor = enableFoodLevelSensor;
        this.enableWaterLevelSensor = enableWaterLevelSensor;
        this.enableWiFiPeriodicWakeUp = enableWiFiPeriodicWakeUp;
    }

    @Override
    public byte[] toByteArray() {

        Long flags = 0L;
        flags = BitWiseUtil.concatBytes(flags, this.enableSpot ? 0b1L : 0b0L, 0);
        flags = BitWiseUtil.concatBytes(flags, this.enableFoodRelease ? 0b1L : 0b0L, 1);
        flags = BitWiseUtil.concatBytes(flags, this.enableCam ? 0b1L : 0b0L, 2);
        flags = BitWiseUtil.concatBytes(flags, this.enableAnimalIdentification ? 0b1L : 0b0L, 3);
        flags = BitWiseUtil.concatBytes(flags, this.enablePresenceSensor ? 0b1L : 0b0L, 4);
        flags = BitWiseUtil.concatBytes(flags, this.enableFoodLevelSensor ? 0b1L : 0b0L, 5);
        flags = BitWiseUtil.concatBytes(flags, this.enableWaterLevelSensor ? 0b1L : 0b0L, 6);
        flags = BitWiseUtil.concatBytes(flags, this.enableWiFiPeriodicWakeUp ? 0b1L : 0b0L, 7);

        return new byte[] { flags.byteValue() };
    }
}
