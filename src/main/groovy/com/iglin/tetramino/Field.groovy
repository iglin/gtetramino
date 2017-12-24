package com.iglin.tetramino

/**
 * Created by Alexander Iglin on 23.12.2017.
 */
class Field {
    int size = 8
    int[][] points

    Field() {
        points = new int[size][size]
//        points.each {
//            for (int i = 0; i < size; i++) it[i] = -1
//        }
    }

    Field(int size) {
        this.size = size
        points = new int[size][size]
//        points.each {
//            for (int i = 0; i < size; i++) it[i] = -1
//        }
    }

    boolean insert(Figure figure, int x, int y, int stage) {
        for (int i = 0; i < figure.points.length; i++) {
            for (int j = 0; j < figure.points[0].length; j++) {
                if (figure.points[i][j]) {
                    if (points[x + i][y + j] != 0) return false
                }
            }
        }

        for (int i = 0; i < figure.points.length; i++) {
            for (int j = 0; j < figure.points[0].length; j++) {
                if (figure.points[i][j]) {
                    points[x + i][y + j] = stage
                }
            }
        }
        return true;
    }

    void remove(Figure figure, int x, int y) {
        for (int i = 0; i < figure.points.length; i++) {
            for (int j = 0; j < figure.points[0].length; j++) {
                if (figure.points[i][j]) {
                    points[x + i][y + j] = 0
                }
            }
        }
    }

    void print() {
        points.each {
            it.each {
                if (it < 0 || it > 9) print("${it} ")
                else print(" ${it} ")
            }
            println()
        }
    }

    @Override
    String toString() {
        StringBuilder stringBuilder = new StringBuilder()
        points.each {
            it.each {
                if (it < 0 || it > 9) stringBuilder.append("${it} ")
                else stringBuilder.append(" ${it} ")
            }
            stringBuilder.append(System.lineSeparator())
        }
        return stringBuilder.toString()
    }
}
