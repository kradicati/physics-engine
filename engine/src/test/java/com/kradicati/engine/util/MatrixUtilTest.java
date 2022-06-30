package com.kradicati.engine.util;

import com.kradicati.engine.Constants;
import com.kradicati.engine.util.datastructure.Vector;
import org.junit.Test;

import java.util.Arrays;

public class MatrixUtilTest {

    @Test
    public void multiply() {
        double[][] a = new double[][]{{2, -2}, {5, 3}};
        double[][] b = new double[][]{{-1, 4}, {7, -6}};

        double[][] result = MatrixUtil.multiply(a, b);

        printMatrix(result);
    }

    @Test
    public void rotate_vector() {
        Vector vector = new Vector(0, 1);

        printMatrix(MatrixUtil.multiply(MatrixUtil.ROTATE_90_CCW, MatrixUtil.vectorToMatrix(vector)));
        System.out.println();
        printMatrix(MatrixUtil.multiply(MatrixUtil.ROTATE_180_CCW, MatrixUtil.vectorToMatrix(vector)));
        System.out.println();
        printMatrix(MatrixUtil.multiply(MatrixUtil.ROTATE_270_CCW, MatrixUtil.vectorToMatrix(vector)));

        System.out.println();
        System.out.println(MatrixUtil.applyRotationMatrix(vector, Constants.DEG_RAD * 90));
    }

    private void printMatrix(double[][] result) {
        for (double[] doubles : result) {
            System.out.println(Arrays.toString(doubles));
        }
    }

}