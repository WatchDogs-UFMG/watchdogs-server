package br.ufmg.watchdogs.server.mqtt.downlink.payload.impl;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.MqttDownLinkPayloadParser;
import br.ufmg.watchdogs.server.mqtt.util.BitWiseUtil;

import java.math.BigInteger;
import java.util.List;

public class MqttDownLinkAnimalSyncPayloadParserImpl implements MqttDownLinkPayloadParser {

    public static final Integer ANIMAL_ID_LENGTH = 6;

    private final List<String> animalIDList;

    public MqttDownLinkAnimalSyncPayloadParserImpl(List<String> animalIDList) {
        this.animalIDList = animalIDList;
    }

    @Override
    public byte[] toByteArray() {

        int totalBytes = (this.animalIDList.size() * MqttDownLinkAnimalSyncPayloadParserImpl.ANIMAL_ID_LENGTH) + 1;
        byte[] byteArray = new byte[totalBytes];

        byteArray[0] = BitWiseUtil.getByteSlice(
                (long) this.animalIDList.size(),
                8,
                0
        ).byteValue();

        int nextByte = 1;

        for (String animal: this.animalIDList) {

            BigInteger bigInteger = new BigInteger(animal, 16);
            long animalIDLong = bigInteger.longValue();

            int startOffset = (MqttDownLinkAnimalSyncPayloadParserImpl.ANIMAL_ID_LENGTH - 1) * 8;

            for (int offset = startOffset; offset >= 0; offset -= 8) {

                byteArray[nextByte] = BitWiseUtil.getByteSlice(
                        animalIDLong,
                        8,
                        offset
                ).byteValue();

                nextByte++;
            }
        }

        return byteArray;
    }
}
