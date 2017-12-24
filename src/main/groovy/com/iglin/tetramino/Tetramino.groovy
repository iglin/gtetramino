package com.iglin.tetramino

import org.slf4j.Logger
import org.slf4j.LoggerFactory


/**
 * Created by Alexander Iglin on 23.12.2017.
 */
class Tetramino {
    int size = 8
    Field field
    List<Figure> figures
    List<HistoryPoint> history = new ArrayList<>()
    Random random = new Random(System.currentTimeMillis())
    long startingTime

    static final Logger LOGGER = LoggerFactory.getLogger(Tetramino.class);

    private void initFigures() {
        figures = new ArrayList<>(7)

        // x x x
        // x
        figures.add(new Figure(3, [ [true, true, true], [true, false, false] ] as boolean [][]))

        // x x x
        //     x
        figures.add(new Figure(3, [ [true, true, true], [false, false, true] ] as boolean [][]))

        // x x x
        //   x
        figures.add(new Figure(3, [ [true, true, true], [false, true, false] ] as boolean [][]))

        // x x
        //   x x
        figures.add(new Figure(1, [ [true, true, false], [false, true, true] ] as boolean [][]))

        //   x x
        // x x
        figures.add(new Figure(1, [ [false, true, true], [true, true, false] ] as boolean [][]))

        // x x x x
        figures.add(new Figure(1, [[true, true, true, true]] as boolean [][]))

        // x x
        // x x
        figures.add(new Figure(0, [ [true, true], [true, true] ] as boolean [][]))
    }

    Tetramino() {
        field = new Field(size)
        initFigures()
    }

    Tetramino(int size) {
        this.size = size
        field = new Field(size)
        initFigures()
    }

    private boolean checkFinished() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                if (field.points[i][j] == 0) return false
        }
        return true
    }

    def solveRecursive(int stage, int iteration) {
        iteration++
        if (checkFinished()) return new Tuple(true, iteration)
       // while (!checkFinished()) {
        List<Integer> availableIndexes = new ArrayList<>(7)
        for (int i = 0; i < 7; i++) {
            int r = random.nextInt(7)
            while (availableIndexes.contains(r)) r = random.nextInt(7)
            availableIndexes.add(r)
        }
        while (!availableIndexes.empty) {
            int index = availableIndexes.remove(0)
            Figure figure = figures.get(index)

            for (int i = 0; i <= size - figure.points.length; i++) {
                for (int j = 0; j <= size - figure.points[0].length; j++) {
                    if (field.insert(figure, i, j, stage)) {
                        //history.add(new HistoryPoint(stage, availableIndexes, figure, i, j))
                        LOGGER.info("Succeed on iteration {}, time {} ms", iteration,
                                (System.nanoTime() - startingTime) / 1000000.0)
                        LOGGER.info("{}", field)
                        println()
                        if (checkFinished()) return new Tuple(true, iteration)
                        stage++
                        def (Boolean result, Integer newIteration) = solveRecursive(stage, iteration)
                        iteration = newIteration
                        if (result) {
                            return new Tuple(true, iteration)
                        } else {
                            stage--
                            //HistoryPoint historyPoint = history.remove(history.size() - 1)
                            //field.remove(historyPoint.figure, historyPoint.x, historyPoint.y)
                            field.remove(figure, i, j)
                            LOGGER.info("Rolled back on iteration {}, time {} ms", iteration,
                                    (System.nanoTime() - startingTime) / 1000000.0)
                            LOGGER.info("{}", field)
                        }
                    }
                }
            }
        }
   //     }
        return new Tuple(false, iteration)
    }

    def solve() {
        startingTime = System.nanoTime()
        LOGGER.info("STARTING TIME {}", startingTime)
        solveRecursive(1, 0)
        LOGGER.info("FINISHED AT TIME {} ms", (System.nanoTime() - startingTime) / 1000000.0)
        LOGGER.info("{}", field)
    }

    int solveCycles() {

        int stage = 1
        while (!checkFinished()) {
            List<Integer> availableIndexes = new ArrayList<>(7)
            for (int i = 0; i < 7; i++) {
                int r = random.nextInt(7)
                while (availableIndexes.contains(r)) r = random.nextInt(7)
                availableIndexes.add(r)
            }
            while (!availableIndexes.empty) {
                int index = availableIndexes.remove(0)
                Figure figure = figures.get(index)

                int x = 0
                int y = 0
                for (int i = x; i <= size - figure.points.length; i++) {
                    for (int j = y; j <= size - figure.points.length; j++) {
                        if (field.insert(figure, i, j, stage)) {
                            history.add(new HistoryPoint(stage, availableIndexes, figure, i, j))
                            stage++
                            //if ()
                        }
                    }
                }
            }
        }
        return 0;
    }
}
