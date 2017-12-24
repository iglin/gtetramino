package com.iglin.tetramino.bit

/**
 * Created by Alexander Iglin on 24.12.2017.
 */
class BitSetUtils {

    static String stringifyBitSet(BitSet bitSet, int size) {
        StringBuilder stringBuilder = new StringBuilder()
        for (int i = 0; i < size; i++) {
            bitSet[i] ? stringBuilder.append("1") : stringBuilder.append("0")
        }
        return stringBuilder.toString()
    }

    static boolean checkAll(BitSet bitSet, int size) {
        return bitSet.nextClearBit(0) >= size
    }

    static boolean checkNone(BitSet bitSet, int size) {
        return !checkAny(bitSet, size)
    }

    static boolean checkAny(BitSet bitSet, int size) {
        int index = bitSet.nextSetBit(0)
        return index != -1 && index < size
    }

    static BitSet rightShiftZero(BitSet bitSet, int distance) {
        long[] arr = bitSet.toLongArray()
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] << distance
        }
        return BitSet.valueOf(arr)
    }

    static BitSet invert(BitSet bitSet, int size) {
        if (bitSet.length() > 0) return ~bitSet

        BitSet result = new BitSet()
        result.set(0, size)
        return result
    }
}
