package Chapter1;

import edu.princeton.cs.algs4.Interval1D;

public class ClientForInterval1D{



//    public  boolean intersects1(Interval1D that1) {
//        if (this.max < that1.min()) return false;
//        if(that1.max() < this.min) return false;
//        return true;
//    }
//

    public static void main(String[] args) {

        int n = Integer.parseInt("4");

        double x = 1;
        double y = 5;

        double x2 = 4;
        double y2 = 10;

        double x3 = 0;
        double y3 = 2;

        double x4 = 1;
        double y4 = 6;


        int count = 0;

        Interval1D[] interval1DS = new Interval1D[n];

        interval1DS[0] = new Interval1D(x, y);
        interval1DS[1] = new Interval1D(x2, y2);
        interval1DS[2] = new Interval1D(x3, y3);
        interval1DS[3] = new Interval1D(x4, y4);

        for (int i = 0; i < interval1DS.length; i++) {
            for (int j = 0; j < i; j++) {
                if (interval1DS[i].intersects(interval1DS[j])) {
                    count++;
                }
            }
        }
        System.out.println("Crossed pair: " + count);


    }

}
