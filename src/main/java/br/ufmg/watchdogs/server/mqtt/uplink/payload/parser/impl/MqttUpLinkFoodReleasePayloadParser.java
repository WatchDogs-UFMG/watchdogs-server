package br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.impl;

import br.ufmg.watchdogs.server.mqtt.uplink.payload.parser.MqttUpLinkPayloadParser;
import br.ufmg.watchdogs.server.util.BitWiseUtil;

import java.util.Arrays;

public class MqttUpLinkFoodReleasePayloadParser implements MqttUpLinkPayloadParser {

    private static final Integer ANIMAL_ID_START_BIT = 0;
    private static final Integer ANIMAL_ID_END_BIT = 48;
    private static final Integer FOOD_LEVEL_START_BIT = 48;
    private static final Integer FOOD_LEVEL_END_BIT = 56;
    private static final Integer WATER_LEVEL_START_BIT = 56;
    private static final Integer WATER_LEVEL_END_BIT = 64;
    private static final Integer PHOTO_START_BIT = 64;

    private static final Integer ANIMAL_ID_STRING_LENGTH = ((ANIMAL_ID_END_BIT - ANIMAL_ID_START_BIT) / 8) * 2;

    private final String animalID;
    private final Integer foodLevel;
    private final Integer waterLevel;
    private final byte[] photo;

    public MqttUpLinkFoodReleasePayloadParser(byte[] payload) {
        this.animalID = this.parseAnimalID(payload);
        this.foodLevel = this.parseFoodLevel(payload);
        this.waterLevel = this.parseWaterLevel(payload);
        this.photo = this.parsePhoto(payload);
    }

    private String parseAnimalID(byte[] payload) {

        Long animalIDNumber = BitWiseUtil.extractLongValue(payload, ANIMAL_ID_START_BIT, ANIMAL_ID_END_BIT);
        String hexString = Long.toHexString(animalIDNumber);

        return this.formatString(hexString, ANIMAL_ID_STRING_LENGTH);
    }

    private Integer parseFoodLevel(byte[] payload) {
        return BitWiseUtil.extractIntegerValue(payload, FOOD_LEVEL_START_BIT, FOOD_LEVEL_END_BIT);
    }

    private Integer parseWaterLevel(byte[] payload) {
        return BitWiseUtil.extractIntegerValue(payload, WATER_LEVEL_START_BIT, WATER_LEVEL_END_BIT);
    }

    private byte[] parsePhoto(byte[] payload) {
        return Arrays.copyOfRange(
                payload,
                PHOTO_START_BIT / 8,
                payload.length
        );
    }

    private String formatString(String originalString, Integer finalLength) {

        StringBuilder originalStringBuilder = new StringBuilder(originalString);

        for (int i = originalStringBuilder.length(); i < finalLength; i++) {
            originalStringBuilder.insert(0, "0");
        }

        return originalStringBuilder.toString();
    }

    public String getAnimalID() {
        return animalID;
    }

    public Integer getFoodLevel() {
        return foodLevel;
    }

    public Integer getWaterLevel() {
        return waterLevel;
    }

    public byte[] getPhoto() {
        return photo;
    }
}
