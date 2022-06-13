package br.ufmg.watchdogs.server.mqtt.downlink.payload.impl;

import br.ufmg.watchdogs.server.mqtt.downlink.payload.MqttDownLinkPayloadParser;
import br.ufmg.watchdogs.server.mqtt.util.BitWiseUtil;

public class MqttDownLinkAckPayloadParserImpl implements MqttDownLinkPayloadParser {

    private final Integer payloadCountID;

    public MqttDownLinkAckPayloadParserImpl(Integer payloadCountID) {
        this.payloadCountID = payloadCountID;
    }

    @Override
    public byte[] toByteArray() {

        byte payloadCountIDByte1 = BitWiseUtil.getByteSlice(
                (long) this.payloadCountID,
                8,
                4
        ).byteValue();

        byte payloadCountIDByte2 = BitWiseUtil.getByteSlice(
                (long) this.payloadCountID,
                4,
                0
        ).byteValue();

        return new byte[]{ payloadCountIDByte1, payloadCountIDByte2 };
    }
}
