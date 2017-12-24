package com.iglin.tetramino

/**
 * Created by Alexander Iglin on 23.12.2017.
 */
class HistoryPoint {
    int stage
    List<Integer> availableIndexes
    Figure figure
    int x
    int y

    HistoryPoint() {
    }

    HistoryPoint(int stage, List<Integer> availableIndexes, Figure figure, int x, int y) {
        this.stage = stage
        this.availableIndexes = availableIndexes
        this.figure = figure
        this.x = x
        this.y = y
    }
}
