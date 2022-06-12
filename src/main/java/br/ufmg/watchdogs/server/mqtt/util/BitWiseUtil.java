package br.ufmg.watchdogs.server.mqtt.util;

import java.util.Arrays;

public class BitWiseUtil {

    public static Long getByteSlice(Long originalByte, Long bitsMaskCount, Long unsignedRightShift) {
        return BitWiseUtil.applyBitMask(BitWiseUtil.shiftByte(originalByte, unsignedRightShift), bitsMaskCount);
    }

    public static Long concatBytes(Long rightByte, Long leftByte, Long leftByteOffset) {
        return (rightByte + (leftByte << leftByteOffset));
    }

    public static Long applyBitMask(Long originalByte, Long bitWiseMaskCount) {
        return originalByte & BitWiseUtil.getBitWiseMask(bitWiseMaskCount).getMask();
    }

    public static Long shiftByte(Long originalByte, Long unsignedRightShift) {
        return (originalByte >>> unsignedRightShift);
    }

    private static BitWiseMask getBitWiseMask(Long bitsMaskCount) {
        return Arrays.stream(BitWiseMask.values())
                .filter(bitMask -> bitMask.getBitsMaskCount().equals(bitsMaskCount))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("BitWiseMask not found for a " + bitsMaskCount + " bits mask!"));
    }
}
