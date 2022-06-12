package br.ufmg.watchdogs.server.mqtt.util;

public enum BitWiseMask {

    ONE_BIT_MASK(0b00000001L, 1L),
    TWO_BIT_MASK(0b00000011L, 2L),
    THREE_BIT_MASK(0b00000111L, 3L),
    FOUR_BIT_MASK(0b00001111L, 4L),
    FIVE_BIT_MASK(0b00011111L, 5L),
    SIX_BIT_MASK(0b00111111L, 6L),
    SEVEN_BIT_MASK(0b01111111L, 7L),
    EIGHT_BIT_MASK(0b11111111L, 8L);

    private final Long mask;
    private final Long bitsMaskCount;

    BitWiseMask(Long mask, Long bitsMaskCount) {
        this.mask = mask;
        this.bitsMaskCount = bitsMaskCount;
    }

    public Long getMask() {
        return mask;
    }

    public Long getBitsMaskCount() {
        return bitsMaskCount;
    }
}
