package br.ufmg.watchdogs.server.test.unit.mqtt.util;

import br.ufmg.watchdogs.server.mqtt.util.BitWiseUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BitWiseUtilTest {

    private static final Long byte1 = 0b0110_1001_1010_0101_1100_0011_0001_1000L;
    private static final Long byte2 = 0b1001_0111_0101_1010_0011_1100_1110_0111L;
    private static final Long longByte = 0b0110_1001_1010_0101_1100_0011_0001_1000_1001_0111_0101_1010_0011_1100_1110_0111L;
    private static final byte[] byteArray = { 0b00000001, 0b000000011, 0b00000111, 0b00001111, 0b00011111, 0b00111111, 0b01111111, (byte) 0b11111111, (byte) 0b11111110, (byte) 0b10000000 };

    @Test
    void shouldExtractLongAndIntegerValueFromBinaryString() {

        Integer integerValue = BitWiseUtil.extractIntegerValue(byteArray, 7, 15);
        Integer integerValueMax = BitWiseUtil.extractIntegerValue(byteArray, (byteArray.length * 8) - (4 * 8), byteArray.length * 8);
        Long longValue = BitWiseUtil.extractLongValue(byteArray, (byteArray.length * 8) - (5 * 8), byteArray.length * 8);
        Long longValueMax = BitWiseUtil.extractLongValue(byteArray, (byteArray.length * 8) - (8 * 8), byteArray.length * 8);

        Assertions.assertEquals(0b10000001, integerValue);
        Assertions.assertEquals(0b01111111111111111111111010000000, integerValueMax);
        Assertions.assertEquals(0b0011111101111111111111111111111010000000L, longValue);
        Assertions.assertEquals(0b0000011100001111000111110011111101111111111111111111111010000000L, longValueMax);
    }

    @Test
    void shouldBuildABinaryString() {

//        for (int i = 0; i < 128; i++) {
//            byte[] byteArray = {(byte) i};
//            BitWiseUtil.getBinaryString(byteArray);
//        }
//
//        for (int i = -128; i < 0; i++) {
//            byte[] byteArray = {(byte) i};
//            BitWiseUtil.getBinaryString(byteArray);
//        }

        byte[] byteArray = { 0b1, 0b11, 0b111, 0b1111, 0b11111, 0b111111, 0b1111111, (byte) 0b11111111, (byte) 0b11111110, (byte) 0b10000000 };
        String binaryString = BitWiseUtil.getBinaryString(byteArray);

        Assertions.assertEquals("00000001000000110000011100001111000111110011111101111111111111111111111010000000", binaryString);
        Assertions.assertEquals(byteArray.length * 8, binaryString.length());
    }

    @Test
    void shouldGetByteSlice() {
        Long byteSlice1 = BitWiseUtil.getByteSlice(byte1, 4, 0);
        Long byteSlice2 = BitWiseUtil.getByteSlice(byte1, 4, 4);
        Long byteSlice3 = BitWiseUtil.getByteSlice(byte1, 8, 8);
        Long byteSlice4 = BitWiseUtil.getByteSlice(byte1, 8, 16);
        Long byteSlice5 = BitWiseUtil.getByteSlice(byte1, 8, 24);

        Assertions.assertEquals(0b1000, byteSlice1);
        Assertions.assertEquals(0b0001, byteSlice2);
        Assertions.assertEquals(0b1100_0011, byteSlice3);
        Assertions.assertEquals(0b1010_0101, byteSlice4);
        Assertions.assertEquals(0b0110_1001, byteSlice5);
    }

    @Test
    void shouldConcatTwoBytes() {

        Long concatBytes1 = BitWiseUtil.concatBytes(byte1, byte2, 32);

        Long concatBytes2 = 0L;
        concatBytes2 = BitWiseUtil.concatBytes(0b1000L, concatBytes2, 0);
        concatBytes2 = BitWiseUtil.concatBytes(0b0001L, concatBytes2, 4);
        concatBytes2 = BitWiseUtil.concatBytes(0b0011L, concatBytes2, 8);
        concatBytes2 = BitWiseUtil.concatBytes(0b1100L, concatBytes2, 12);
        concatBytes2 = BitWiseUtil.concatBytes(0b0101L, concatBytes2, 16);
        concatBytes2 = BitWiseUtil.concatBytes(0b1010L, concatBytes2, 20);
        concatBytes2 = BitWiseUtil.concatBytes(0b1001L, concatBytes2, 24);
        concatBytes2 = BitWiseUtil.concatBytes(0b0110L, concatBytes2, 28);

        Assertions.assertEquals(longByte, concatBytes1);
        Assertions.assertEquals(byte1.longValue(), concatBytes2);
    }

    @Test
    void shouldApplyBitMaskToAByte() {
        Long byteMasked1 = BitWiseUtil.applyBitMask(byte1, 8);
        Assertions.assertEquals(0b0001_1000, byteMasked1);
    }

    @Test
    void shouldShiftTheByte() {

        Long byteShift1 = BitWiseUtil.shiftByte(byte1, 0);
        Long byteShift2 = BitWiseUtil.shiftByte(byte1, 8);
        Long byteShift3 = BitWiseUtil.shiftByte(byte1, 16);
        Long byteShift4 = BitWiseUtil.shiftByte(byte1, 24);
        Long byteShift5 = BitWiseUtil.shiftByte(byte1, 32);


        Assertions.assertEquals(0b0110_1001_1010_0101_1100_0011_0001_1000L, byteShift1);
        Assertions.assertEquals(0b0110_1001_1010_0101_1100_0011L, byteShift2);
        Assertions.assertEquals(0b0110_1001_1010_0101L, byteShift3);
        Assertions.assertEquals(0b0110_1001L, byteShift4);
        Assertions.assertEquals(0b0L, byteShift5);

    }

    @Test
    void shouldShiftTheByteLeft() {

        Long byteShift1 = BitWiseUtil.shiftByteLeft(byte1, 0);
        Long byteShift2 = BitWiseUtil.shiftByteLeft(byte1, 4);
        Long byteShift3 = BitWiseUtil.shiftByteLeft(byte1, 8);

        Assertions.assertEquals(0b0110_1001_1010_0101_1100_0011_0001_1000L, byteShift1);
        Assertions.assertEquals(0b0110_1001_1010_0101_1100_0011_0001_1000_0000L, byteShift2);
        Assertions.assertEquals(0b0110_1001_1010_0101_1100_0011_0001_1000_0000_0000L, byteShift3);
    }
}
