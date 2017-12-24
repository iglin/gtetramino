package com.iglin.tetramino.bit

/**
 * Created by Alexander Iglin on 24.12.2017.
 */
class BitFigure {
    int turns
    int n
    int m
    BitSet [] bits

    BitFigure() {}

    BitFigure(int turns) {
        this.turns = turns
    }

    BitFigure(int turns, int n, int m) {
        this.turns = turns
        this.n = n
        this.m = m
        this.bits = new BitSet[n]
        for (int i = 0; i < n; i++) bits[i] = new BitSet()
    }

    BitFigure(int turns, ArrayList bits) {
        this.turns = turns
        this.bits = bits
    }

    BitFigure(int turns, bits) {
        this.turns = turns
        this.bits = bits
    }

    BitFigure(int turns, int n, int m, bits) {
        this.turns = turns
        this.n = n
        this.m = m
        this.bits = bits
    }

    BitFigure(int turns, int n, int m, ArrayList bits) {
        this.n = n
        this.m = m
        this.turns = turns
        this.bits = bits
    }

    boolean rotate() {
        if (turns == 0) return false

        turns--
        int newN = m
        int newM = n
        BitSet [] newBits = new BitSet[newN]
        for (int i = 0; i < newN; i++) {
            newBits[i] = new BitSet()
            for (int j = 0; j < newM; j++) {
                newBits[i][j] = bits[newM - j - 1][i]
            }
        }
        bits = newBits
        n = newN
        m = newM
        return true
    }

    void print() {
        println(toString())
    }

    @Override
    String toString() {
        StringBuilder builder = new StringBuilder()
        bits.each {
            builder.append(BitSetUtils.stringifyBitSet(it, m)).append(System.lineSeparator())
        }
        return builder.toString()
    }
}
