package br.ufmg.watchdogs.server.mqtt.util;

public enum BitWiseMask {

    ONE_BIT_MASK(0b00000001L, 1),
    TWO_BIT_MASK(0b00000011L, 2),
    THREE_BIT_MASK(0b00000111L, 3),
    FOUR_BIT_MASK(0b00001111L, 4),
    FIVE_BIT_MASK(0b00011111L, 5),
    SIX_BIT_MASK(0b00111111L, 6),
    SEVEN_BIT_MASK(0b01111111L, 7),
    EIGHT_BIT_MASK(0b11111111L, 8);

    private final Long mask;
    private final Integer bitsMaskCount;

    BitWiseMask(Long mask, Integer bitsMaskCount) {
        this.mask = mask;
        this.bitsMaskCount = bitsMaskCount;
    }

    public Long getMask() {
        return mask;
    }

    public Integer getBitsMaskCount() {
        return bitsMaskCount;
    }
}
