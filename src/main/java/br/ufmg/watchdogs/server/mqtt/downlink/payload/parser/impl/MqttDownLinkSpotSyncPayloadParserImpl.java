package br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.impl;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.parser.MqttDownLinkPayloadParser;
import br.ufmg.watchdogs.server.util.BitWiseUtil;

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

    public static final Integer SECOND_START_BIT = 8;
    public static final Integer SECOND_END_BIT = 14;
    public static final Integer MINUTE_START_BIT = 14;
    public static final Integer MINUTE_END_BIT = 20;
    public static final Integer HOUR_START_BIT = 20;
    public static final Integer HOUR_END_BIT = 25;
    public static final Integer DAY_START_BIT = 25;
    public static final Integer DAY_END_BIT = 30;
    public static final Integer MONTH_START_BIT = 30;
    public static final Integer MONTH_END_BIT = 34;
    public static final Integer YEAR_START_BIT = 34;
    public static final Integer YEAR_END_BIT = 46;

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

    public MqttDownLinkSpotSyncPayloadParserImpl(
            boolean enableSpot,
            boolean enableFoodRelease,
            boolean enableCam,
            boolean enableAnimalIdentification,
            boolean enablePresenceSensor,
            boolean enableFoodLevelSensor,
            boolean enableWaterLevelSensor,
            boolean enableWiFiSleep,
            LocalDateTime currentDateTime
    ) {
        this.enableSpot = enableSpot;
        this.enableFoodRelease = enableFoodRelease;
        this.enableCam = enableCam;
        this.enableAnimalIdentification = enableAnimalIdentification;
        this.enablePresenceSensor = enablePresenceSensor;
        this.enableFoodLevelSensor = enableFoodLevelSensor;
        this.enableWaterLevelSensor = enableWaterLevelSensor;
        this.enableWiFiSleep = enableWiFiSleep;
        this.currentDateTime = currentDateTime;
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

        Long dateTimeLong1 = BitWiseUtil.concatBytes(
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
        );

        Long dateTimeLong2 = BitWiseUtil.concatBytes(
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
        );

        Long dateTimeLong3 = BitWiseUtil.concatBytes(
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
        );

        dateTimeLong3 = BitWiseUtil.concatBytes(
                dateTimeLong3,
                BitWiseUtil.getByteSlice(
                        (long) this.currentDateTime.getMonth().getValue(),
                        2,
                        2
                ),
                0
        );

        Long dateTimeLong4 = BitWiseUtil.concatBytes(
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
        );

        Long dateTimeLong5 = BitWiseUtil.concatBytes(
                BitWiseUtil.getByteSlice(
                        (long) this.currentDateTime.getYear(),
                        6,
                        0
                ),
                0L,
                2
        );

        return new byte[] {
                flags.byteValue(),
                dateTimeLong1.byteValue(),
                dateTimeLong2.byteValue(),
                dateTimeLong3.byteValue(),
                dateTimeLong4.byteValue(),
                dateTimeLong5.byteValue()
        };
    }
}
