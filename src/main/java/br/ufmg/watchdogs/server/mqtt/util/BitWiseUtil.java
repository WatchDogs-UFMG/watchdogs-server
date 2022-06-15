package br.ufmg.watchdogs.server.mqtt.util;

import java.util.Arrays;

public class BitWiseUtil {

    public static Long extractLongValue(byte[] bytePayload, Integer startBit, Integer endBit) {
        String substring = BitWiseUtil.getBinaryString(bytePayload).substring(startBit, endBit);
        return Long.parseLong(substring, 2);
    }

    public static Integer extractIntegerValue(byte[] bytePayload, Integer startBit, Integer endBit) {
        String substring = BitWiseUtil.getBinaryString(bytePayload).substring(startBit, endBit);
        return Integer.parseInt(substring, 2);
    }

    public static String getBinaryString(byte[] bytePayload) {

        StringBuilder binaryString = new StringBuilder();

        for (byte byteElement: bytePayload) {

            StringBuilder binaryStringAux = new StringBuilder(Long.toBinaryString(byteElement & 0xffffffffL));

            for (int i = binaryStringAux.length(); i < 8; i++) {
                binaryStringAux.insert(0, "0");
            }

            String formattedBinaryString = (binaryStringAux.length() > 8)
                    ? binaryStringAux.substring(binaryStringAux.length() - 8, binaryStringAux.length())
                    : binaryStringAux.toString();

            binaryString.append(formattedBinaryString);
        }

        return binaryString.toString();
    }

    public static Long getByteSlice(Long originalByte, Integer bitsMaskCount, Integer unsignedRightShift) {
        return BitWiseUtil.applyBitMask(BitWiseUtil.shiftByte(originalByte, unsignedRightShift), bitsMaskCount);
    }

    public static Long concatBytes(Long leftByte, Long rightByte, Integer leftByteOffset) {
        return (rightByte + (leftByte << leftByteOffset));
    }

    public static Long applyBitMask(Long originalByte, Integer bitWiseMaskCount) {
        return originalByte & BitWiseUtil.getBitWiseMask(bitWiseMaskCount).getMask();
    }

    public static Long shiftByte(Long originalByte, Integer unsignedRightShift) {
        return (originalByte >>> unsignedRightShift);
    }

    private static BitWiseMask getBitWiseMask(Integer bitsMaskCount) {
        return Arrays.stream(BitWiseMask.values())
                .filter(bitMask -> bitMask.getBitsMaskCount().equals(bitsMaskCount))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("BitWiseMask not found for a " + bitsMaskCount + " bits mask!"));
    }

    public static Long shiftByteLeft(Long originalByte, Integer leftShift) {
        return originalByte << leftShift;
    }
}
