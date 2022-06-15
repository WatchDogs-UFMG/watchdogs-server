package br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.impl;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.MqttDownLinkPayloadParser;
import br.ufmg.watchdogs.server.mqtt.util.BitWiseUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class MqttDownLinkSpotSyncPayloadParserImpl implements MqttDownLinkPayloadParser {

    private static final Integer ENABLE_SPOT_FLAG_OFFSET = 0;
    private static final Integer ENABLE_FOOD_RELEASE_OFFSET = 1;
    private static final Integer ENABLE_CAM_OFFSET = 2;
    private static final Integer ENABLE_ANIMAL_IDENTIFICATION_OFFSET = 3;
    private static final Integer ENABLE_PRESENCE_SENSOR_OFFSET = 4;
    private static final Integer ENABLE_FOOD_LEVEL_SENSOR_OFFSET = 5;
    private static final Integer ENABLE_WATER_LEVEL_SENSOR_OFFSET = 6;
    private static final Integer ENABLE_WIFI_SLEEP_MODE_OFFSET = 7;
    private final boolean enableSpot;
    private final boolean enableFoodRelease;
    private final boolean enableCam;
    private final boolean enableAnimalIdentification;
    private final boolean enablePresenceSensor;
    private final boolean enableFoodLevelSensor;
    private final boolean enableWaterLevelSensor;
    private final boolean enableWiFiSleep;

    private final LocalDateTime currentDateTime;

    public MqttDownLinkSpotSyncPayloadParserImpl(
            boolean enableSpot,
            boolean enableFoodRelease,
            boolean enableCam,
            boolean enableAnimalIdentification,
            boolean enablePresenceSensor,
            boolean enableFoodLevelSensor,
            boolean enableWaterLevelSensor,
            boolean enableWiFiSleep
    ) {
        this.enableSpot = enableSpot;
        this.enableFoodRelease = enableFoodRelease;
        this.enableCam = enableCam;
        this.enableAnimalIdentification = enableAnimalIdentification;
        this.enablePresenceSensor = enablePresenceSensor;
        this.enableFoodLevelSensor = enableFoodLevelSensor;
        this.enableWaterLevelSensor = enableWaterLevelSensor;
        this.enableWiFiSleep = enableWiFiSleep;
        this.currentDateTime = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }

    @Override
    public byte[] toByteArray() {

        Long flags = 0L;
        flags = BitWiseUtil.concatBytes(this.enableSpot ? 0b1L : 0b0L, flags, ENABLE_SPOT_FLAG_OFFSET);
        flags = BitWiseUtil.concatBytes(this.enableFoodRelease ? 0b1L : 0b0L, flags, ENABLE_FOOD_RELEASE_OFFSET);
        flags = BitWiseUtil.concatBytes(this.enableCam ? 0b1L : 0b0L, flags, ENABLE_CAM_OFFSET);
        flags = BitWiseUtil.concatBytes(this.enableAnimalIdentification ? 0b1L : 0b0L, flags, ENABLE_ANIMAL_IDENTIFICATION_OFFSET);
        flags = BitWiseUtil.concatBytes(this.enablePresenceSensor ? 0b1L : 0b0L, flags, ENABLE_PRESENCE_SENSOR_OFFSET);
        flags = BitWiseUtil.concatBytes(this.enableFoodLevelSensor ? 0b1L : 0b0L, flags, ENABLE_FOOD_LEVEL_SENSOR_OFFSET);
        flags = BitWiseUtil.concatBytes(this.enableWaterLevelSensor ? 0b1L : 0b0L, flags, ENABLE_WATER_LEVEL_SENSOR_OFFSET);
        flags = BitWiseUtil.concatBytes(this.enableWiFiSleep ? 0b1L : 0b0L, flags, ENABLE_WIFI_SLEEP_MODE_OFFSET);

        byte dateTimeByte1 = BitWiseUtil.concatBytes(
                BitWiseUtil.getByteSlice(
                        (long) this.currentDateTime.getSecond(),
                        6,
                        0
                ),
                BitWiseUtil.getByteSlice(
                        (long) this.currentDateTime.getMinute(),
                        2,
                        4
                ),
                2
        ).byteValue();

        byte dateTimeByte2 = BitWiseUtil.concatBytes(
                BitWiseUtil.getByteSlice(
                        (long) this.currentDateTime.getMinute(),
                        4,
                        0
                ),
                BitWiseUtil.getByteSlice(
                        (long) this.currentDateTime.getHour(),
                        4,
                        1
                ),
                4
        ).byteValue();

        byte dateTimeByte3 = BitWiseUtil.concatBytes(
                BitWiseUtil.getByteSlice(
                        (long) this.currentDateTime.getHour(),
                        1,
                        0
                ),
                BitWiseUtil.shiftByteLeft(
                        BitWiseUtil.getByteSlice(
                                (long) this.currentDateTime.getDayOfMonth(),
                                5,
                                0
                        ),
                        2
                ),
                7
        ).byteValue();

        dateTimeByte3 = BitWiseUtil.concatBytes(
                (long) dateTimeByte3,
                BitWiseUtil.getByteSlice(
                        (long) this.currentDateTime.getMonth().getValue(),
                        2,
                        2
                ),
                2
        ).byteValue();

        byte dateTimeByte4 = BitWiseUtil.concatBytes(
                BitWiseUtil.getByteSlice(
                        (long) this.currentDateTime.getMonth().getValue(),
                        2,
                        0
                ),
                BitWiseUtil.getByteSlice(
                        (long) this.currentDateTime.getYear(),
                        6,
                        6
                ),
                6
        ).byteValue();

        byte dateTimeByte5 = BitWiseUtil.concatBytes(
                BitWiseUtil.getByteSlice(
                        (long) this.currentDateTime.getYear(),
                        6,
                        0
                ),
                0L,
                2
        ).byteValue();

        return new byte[] {
                flags.byteValue(),
                dateTimeByte1,
                dateTimeByte2,
                dateTimeByte3,
                dateTimeByte4,
                dateTimeByte5
        };
    }
}
