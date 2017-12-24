package com.iglin.tetramino.bit

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static com.iglin.tetramino.bit.BitSetUtils.*

/**
 * Created by Alexander Iglin on 24.12.2017.
 */
class BitTetramino {
    int size
    BitField field
    BitFigure [] figures
    Random random = new Random(System.currentTimeMillis())
    long startingTime

    private static final Logger LOGGER = LoggerFactory.getLogger(BitTetramino.class)

    private def initFigures() {
        figures = new BitFigure[7]

        // x x
        // x x
        BitSet bitSet = new BitSet()
        bitSet[0] = true
        bitSet[1] = true
        figures[0] = new BitFigure(0, 2, 2, [bitSet, bitSet])

        // x x
        //   x x
        bitSet = new BitSet()
        bitSet[0] = true
        bitSet[1] = true
        BitSet bitSet2 = new BitSet()
        bitSet2[1] = true
        bitSet2[2] = true
        figures[1] = new BitFigure(1, 2, 3, [bitSet, bitSet2])

        //   x x
        // x x
        figures[2] = new BitFigure(1, 2, 3, [bitSet2, bitSet])

        // x x x
        // x
        bitSet = new BitSet()
        bitSet[0] = true
        bitSet[1] = true
        bitSet[2] = true
        bitSet2 = new BitSet()
        bitSet2[0] = true
        figures[3] = new BitFigure(3, 2, 3, [bitSet, bitSet2])

        // x x x
        //     x
        bitSet2 = new BitSet()
        bitSet2[2] = true
        figures[4] = new BitFigure(3, 2, 3, [bitSet, bitSet2])

        // x x x
        //   x
        bitSet2 = new BitSet()
        bitSet2[1] = true
        figures[5] = new BitFigure(3, 2, 3, [bitSet, bitSet2])

        // x x x x
        bitSet = new BitSet()
        bitSet[0] = true
        bitSet[1] = true
        bitSet[2] = true
        bitSet[3] = true
        figures[6] = new BitFigure(1, 1, 4, [bitSet])
    }

    BitTetramino() {
        size = 8
        field = new BitField(size)
        initFigures()
    }

    BitTetramino(int size) {
        this.size = size
        field = new BitField(size)
        initFigures()
    }

    boolean checkFinished() {
        for (it in field.bits) {
            if (!checkAll(it, size)) return false
        }
        return true
    }

    def solveRecursive(int stage, int iteration) {
        iteration++
        if (checkFinished()) return new Tuple(true, iteration)
        List<Integer> availableIndexes = new ArrayList<>(7)
        for (int i = 0; i < 7; i++) {
            int r = random.nextInt(7)
            while (availableIndexes.contains(r)) r = random.nextInt(7)
            availableIndexes.add(r)
        }
        while (!availableIndexes.empty) {
            int index = availableIndexes.remove(0)
            BitFigure figure = figures[index]

            for (int i = 0; i <= size - figure.n; i++) {
                for (int j = 0; j <= size - figure.m; j++) {
                    if (field.insert(figure, i, j)) {
                        LOGGER.info("Succeed on iteration {}, time {} ms", iteration,
                                (System.nanoTime() - startingTime) / 1000000.0)
                        LOGGER.info("{}", field)
                        if (checkFinished()) return new Tuple(true, iteration)
                        stage++
                        def (Boolean result, Integer newIteration) = solveRecursive(stage, iteration)
                        iteration = newIteration
                        if (result) {
                            return new Tuple(true, iteration)
                        } else {
                            stage--
                            field.remove(figure, i, j)
                            LOGGER.info("Rolled back on iteration {}, time {} ms", iteration,
                                    (System.nanoTime() - startingTime) / 1000000.0)
                            LOGGER.info("{}", field)
                        }
                    }
                }
            }
        }
        return new Tuple(false, iteration)
    }

    def solve() {
        startingTime = System.nanoTime()
        LOGGER.info("STARTING TIME {}", startingTime)
        solveRecursive(1, 0)
        LOGGER.info("FINISHED AT TIME {} ms", (System.nanoTime() - startingTime) / 1000000.0)
        LOGGER.info("{}", field)
    }
}
