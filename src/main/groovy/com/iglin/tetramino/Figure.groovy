package com.iglin.tetramino
/**
 * Created by Alexander Iglin on 23.12.2017.
 */
class Figure {
    int turns
    boolean [][] points

    Figure() {}

    Figure(int turns) {
        this.turns = turns
    }

    Figure(int turns, int n, int m) {
        this.turns = turns
        points = new boolean[n][m]
    }

    Figure(int turns, boolean [][] points) {
        this.turns = turns
        this.points = points
    }

    Figure(int turns, ArrayList points) {
        this.turns = turns
        this.points = points
    }

    boolean rotate() {
        if (turns == 0) return false

        turns--
        int newN = points[0].length
        int newM = points.length
        boolean [][] newPoints = new boolean[newN][newM]
        for (int i = 0; i < newN; i++) {
            for (int j = 0; j < newM; j++) {
                newPoints[i][j] = points[newM - j - 1][i]
            }
        }
        points = newPoints
        return true;
    }

    void print() {
        points.each {
            it.each {
                print("${it} ")
            }
            println()
        }
    }
}

