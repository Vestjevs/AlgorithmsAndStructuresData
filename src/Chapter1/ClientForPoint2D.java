package Chapter1;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class ClientForPoint2D {
    public static void main(String[] args) {
        int n = Integer.parseInt("10");
        Point2D[] arrayP2D = new Point2D[n];
        double[] arInter = new double[(n - 1) * ((n - 1) + 1) / 2];


        for (int i = 0; i < arrayP2D.length; i++) {
            double x = StdRandom.random();
            double y = StdRandom.random();
            arrayP2D[i] = new Point2D(x, y);
            StdDraw.setPenRadius(0.01);

            StdDraw.point(x, y);

        }
        int o = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = o; k < arInter.length; k++) {
                    arInter[k] = arrayP2D[i].distanceTo(arrayP2D[j]);
                }
                o++;
            }

        }
        Arrays.sort(arInter);
        System.out.println(arInter[0]);
        System.out.println(Arrays.toString(arInter));
    }
}
