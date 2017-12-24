package com.iglin.tetramino.bit

import static com.iglin.tetramino.bit.BitSetUtils.*

/**
 * Created by Alexander Iglin on 24.12.2017.
 */
class BitField {
    BitSet [] bits
    int size

    BitField() {
        size = 8
        bits = new BitSet[size]
        for (int i = 0; i < size; i++) bits[i] = new BitSet(size)
    }

    BitField(int size) {
        this.size = size
        bits = new BitSet[size]
        for (int i = 0; i < size; i++) bits[i] = new BitSet(size)
    }

    boolean insert(BitFigure bitFigure, int x, int y) {
        boolean canInsert = true
        for (int i = 0; i < bitFigure.n; i++) {
            BitSet operand = rightShiftZero(bitFigure.bits[i], y)
            BitSet result = operand & bits[i + x]
            result = invert(result, size)
            //BitSet result = ~(bitFigure.bits[i] & bits[i + x].get(y, y + bitFigure.m))
            //canInsert = canInsert && checkAll(result, bitFigure.m)
            canInsert = canInsert && checkAll(result, size)
        }
        if (canInsert) {
            for (int i = 0; i < bitFigure.n; i++) {
                //BitSet operand = bitFigure.bits[i] >>> y
                BitSet operand = rightShiftZero(bitFigure.bits[i], y)
                bits[i + x] |= operand
            }
            return true
        }
        return false
    }

    void remove(BitFigure bitFigure, int x, int y) {
        for (int i = 0; i < bitFigure.n; i++) {
            //BitSet operand = bitFigure.bits[i] >>> y
            BitSet operand = rightShiftZero(bitFigure.bits[i], y)
            //bits[i + x] &= ~operand
            bits[i + x] &= invert(operand, size)
        }
    }

    void print() {
        println(toString())
    }

    @Override
    String toString() {
        StringBuilder builder = new StringBuilder()
        bits.each {
            builder.append(stringifyBitSet(it, size)).append(System.lineSeparator())
        }
        return builder.toString()
    }
}
