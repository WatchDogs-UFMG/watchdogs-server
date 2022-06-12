package br.ufmg.watchdogs.server.unit.mqtt;

import br.ufmg.watchdogs.server.mqtt.util.BitWiseUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BitWiseUtilTest {

    private static Long byte1;
    private static Long byte2;
    private static Long longByte;

    @BeforeAll
    static void before() {
        byte1 = 0b0110_1001_1010_0101_1100_0011_0001_1000L;
        byte2 = 0b1001_0111_0101_1010_0011_1100_1110_0111L;
        longByte = 0b0110_1001_1010_0101_1100_0011_0001_1000_1001_0111_0101_1010_0011_1100_1110_0111L;
    }

    @Test
    void shouldGetByteSlice() {
        Long byteSlice1 = BitWiseUtil.getByteSlice(byte1, 4L, 0L);
        Long byteSlice2 = BitWiseUtil.getByteSlice(byte1, 4L, 4L);
        Long byteSlice3 = BitWiseUtil.getByteSlice(byte1, 8L, 8L);
        Long byteSlice4 = BitWiseUtil.getByteSlice(byte1, 8L, 16L);
        Long byteSlice5 = BitWiseUtil.getByteSlice(byte1, 8L, 24L);

        Assertions.assertEquals(0b1000, byteSlice1);
        Assertions.assertEquals(0b0001, byteSlice2);
        Assertions.assertEquals(0b1100_0011, byteSlice3);
        Assertions.assertEquals(0b1010_0101, byteSlice4);
        Assertions.assertEquals(0b0110_1001, byteSlice5);
    }

    @Test
    void shouldConcatTwoBytes() {

        Long concatBytes1 = BitWiseUtil.concatBytes(byte2, byte1, 32L);

        Long concatBytes2 = 0L;
        concatBytes2 = BitWiseUtil.concatBytes(concatBytes2, 0b1000L, 0L);
        concatBytes2 = BitWiseUtil.concatBytes(concatBytes2, 0b0001L, 4L);
        concatBytes2 = BitWiseUtil.concatBytes(concatBytes2, 0b0011L, 8L);
        concatBytes2 = BitWiseUtil.concatBytes(concatBytes2, 0b1100L, 12L);
        concatBytes2 = BitWiseUtil.concatBytes(concatBytes2, 0b0101L, 16L);
        concatBytes2 = BitWiseUtil.concatBytes(concatBytes2, 0b1010L, 20L);
        concatBytes2 = BitWiseUtil.concatBytes(concatBytes2, 0b1001L, 24L);
        concatBytes2 = BitWiseUtil.concatBytes(concatBytes2, 0b0110L, 28L);

        Assertions.assertEquals(longByte, concatBytes1);
        Assertions.assertEquals(byte1.longValue(), concatBytes2);
    }

    @Test
    void shouldApplyBitMaskToAByte() {
        Long byteMasked1 = BitWiseUtil.applyBitMask(byte1, 8L);
        Assertions.assertEquals(0b0001_1000, byteMasked1);
    }

    @Test
    void shouldShiftTheByte() {

        Long byteShift1 = BitWiseUtil.shiftByte(byte1, 0L);
        Long byteShift2 = BitWiseUtil.shiftByte(byte1, 8L);
        Long byteShift3 = BitWiseUtil.shiftByte(byte1, 16L);
        Long byteShift4 = BitWiseUtil.shiftByte(byte1, 24L);
        Long byteShift5 = BitWiseUtil.shiftByte(byte1, 32L);


        Assertions.assertEquals(0b0110_1001_1010_0101_1100_0011_0001_1000L, byteShift1);
        Assertions.assertEquals(0b0110_1001_1010_0101_1100_0011L, byteShift2);
        Assertions.assertEquals(0b0110_1001_1010_0101L, byteShift3);
        Assertions.assertEquals(0b0110_1001L, byteShift4);
        Assertions.assertEquals(0b0L, byteShift5);

    }
}
