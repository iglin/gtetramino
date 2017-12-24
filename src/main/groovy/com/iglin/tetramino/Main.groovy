package com.iglin.tetramino

import com.iglin.tetramino.bit.BitField
import com.iglin.tetramino.bit.BitFigure
import com.iglin.tetramino.bit.BitSetUtils
import com.iglin.tetramino.bit.BitTetramino

/**
 * Created by Alexander Iglin on 23.12.2017.
 */
class Main {
    static void main(String[] args) {
        //Tetramino tetramino = new Tetramino(6)
        //tetramino.solve()


        BitTetramino bitTetramino = new BitTetramino(6)
        bitTetramino.solve()


//        BitSet bitSet = new BitSet(5)
//        bitSet[0] = false
//        bitSet.set(2, true)
//        bitSet.set(0, 7)
//        println(bitSet)
//        println(BitSetUtils.stringifyBitSet(bitSet, 8))
//        bitSet = BitSetUtils.rightShiftZero(bitSet, 3)
//        println(BitSetUtils.stringifyBitSet(bitSet, 8))

    }
}
