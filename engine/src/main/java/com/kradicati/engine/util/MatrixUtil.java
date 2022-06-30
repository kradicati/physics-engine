package com.kradicati.engine.util;

import com.kradicati.engine.util.datastructure.Vector;
import lombok.experimental.UtilityClass;

/**
 * A utility class for 2 dimensional matrix operations
 */

@UtilityClass
public class MatrixUtil {

    public final double[][] ROTATE_90_CCW = new double[][]{{0, -1}, {1, 0}};
    public final double[][] ROTATE_180_CCW = new double[][]{{-1, -0}, {0, -1}};
    public final double[][] ROTATE_270_CCW = new double[][]{{0, 1}, {-1, 0}};

    public Vector applyRotationMatrix(Vector vector, double t) {
        double[][] rotationMatrix = getRotationMatrix(t);

        return matrix2dToVector(multiply(rotationMatrix, vectorToMatrix(vector)));
    }

    public double[][] getRotationMatrix(double t) {
        double cos = Math.cos(t);
        double sin = Math.sin(t);
        return new double[][]{{cos, -sin}, {sin, cos}};
    }

    public double[][] vectorToMatrix(Vector vector) {
        return new double[][]{{vector.getX()}, {vector.getY()}};
    }

    public Vector matrix2dToVector(double[][] matrix) {
        return new Vector(matrix[0][0], matrix[1][0]);
    }

    public double[][] multiply(double[][] a, double[][] b) {
        double[][] toReturn = new double[a.length][b[0].length];

        double cell = 0;

        for (int row = 0; row < toReturn.length; row++) {
            for (int col = 0; col < toReturn[row].length; col++) {
                for (int i = 0; i < b.length; i++) {
                    cell += a[row][i] * b[i][col];
                }

                toReturn[row][col] = cell;
                cell = 0;
            }
        }

        return toReturn;
    }

}
