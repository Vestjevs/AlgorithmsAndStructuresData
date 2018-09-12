package Chapter1;

import java.util.Arrays;

public class Matrix<E extends Number> {
    Matrix() {
    }

    public static double dot(double[] x, double[] y) {
        double parentheses = 0;

        for (int i = 0; i < x.length; i++) {
            parentheses += x[i] * y[i];
        }
        return parentheses;
    }

    public static double[][] mult(double[][] a, double[][] b) {
        double[][] c = new double[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                for (int k = 0; k < a.length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    public static double[][] transpose(double[][] a) {
        double value;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (i != j) {
                    value = a[i][j];
                    a[i][j] = a[j][i];
                    a[j][i] = value;
                }
            }
        }
        return a;
    }

    public static double[] mult(double[][] a, double[] x) {
        double[] ans = new double[x.length];

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++) {
                ans[i] += a[i][j] * x[j];
            }
        }

        return ans;
    }

    public static double[] mult(double[] y, double[][] a) {
        double[] ans = new double[y.length];
        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < y.length; j++) {
                ans[i] += a[i][j] * y[j];
            }
        }

        return ans;
    }

    public static double[][] mult(double[][] a, double x) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                a[i][j] *= x;
            }
        }
        return a;
    }

    public static double[][] divide(double[][] a, double x) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                a[i][j] /= x;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        double[][] array = {{1.0, 2.0, 5.0}, {3.0, 4.0, 6.0}, {7.0, 8.0, 9.0}};
        double[] array2 = {1, 2, 8};



    }
}

