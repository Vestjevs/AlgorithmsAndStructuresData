package Chapter1;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class ClientForInterval2D {
    public static void main(String[] args) {
        int n = Integer.parseInt("4");
        int min = Integer.parseInt("1");
        int max = Integer.parseInt("15");

        Interval2D[] interval2DS = new Interval2D[n];
        Interval1D interval1;
        Interval1D interval2;

//        for (int i = 0; i < interval2DS.length; i++) {
//            double x1 = StdRandom.random();
//            double x2 = StdRandom.random();
//            double y1 = StdRandom.random();
//            double y2 = StdRandom.random();
//
//            if ((x1 < y1) && (x2 < y2)) {
//                interval1 = new Interval1D(x1, y1);
//                interval2 = new Interval1D(x2, y2);
//
//                if ((interval1.length() <= max && interval1.length() >= min) && (interval2.length() <= max && interval2.length() >= min)) {
//                    interval2DS[i] = new Interval2D(interval1, interval2);
//                }
//            }
//        }


//        for (int j = 0; j < interval2DS.length; j++) {
//            for (int k = 0; k < j; k++) {
//                if (interval2DS[j].intersects(interval2DS[k])) {
//                    count1++;
//                }
//            }
//        }

//        int count2 = 0;
//
//        System.out.println("Crossed pair: " + count1);
        int count1 = 0;
        double val1;
        double val2;

        while (interval2DS.length > count1) {
            double x1 = StdRandom.uniform(min, max);
            double x2 = StdRandom.uniform(min, max);
            double y1 = StdRandom.uniform(min, max);
            double y2 = StdRandom.uniform(min, max);

            if (x1 > y1) {
                val1 = y1;
                y1 = x1;
                x1 = val1;
                interval1 = new Interval1D(x1, y1);
            } else {
                interval1 = new Interval1D(x1, y1);
            }

            if (x2 > y2) {
                val2 = y2;
                y2 = x2;
                x2 = val2;
                interval2 = new Interval1D(x2, y2);
            } else {
                interval2 = new Interval1D(x2, y2);
            }
            interval2DS[count1] = new Interval2D(interval1, interval2);
            count1++;
        }

        count1 = 0;
        for (int i = 0; i < interval2DS.length; i++) {
            for (int j = 0; j < i; j++) {
                if(interval2DS[i].intersects(interval2DS[j])){
                    count1++;
                }
            }
        }


    }
}
