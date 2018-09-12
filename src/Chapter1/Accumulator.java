package Chapter1;

import Chapter1.Interfaces.IAccumulator;

public class Accumulator implements IAccumulator {
    private int amount; // 4 byte
    private double total; // 8 byte
    private double m; // 8 byte
                              // 16 head
                              // 4 adding


    Accumulator() {
    }

    Accumulator(int trials, double m) {
        this.total = trials;
        this.m = m;

//        StdDraw.setXscale(0, trials);
//        StdDraw.setYscale(0, m);
//        StdDraw.setPenRadius(0.005);
    }

    @Override
    public void addDataValue(double val) {
        amount++;
        total += val + 1.0 * (amount - 1) / amount * (val - m) * (val - m);
        m += (val - m) / amount;
//        StdDraw.setPenColor(StdDraw.DARK_GRAY);
//        StdDraw.point(amount, val);
//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.point(amount, total / amount);

    }

    @Override
    public double mean() {
        return m;
    }

    public double var() {
        return total / (amount - 1);
    }

    public double stddev() {
        return Math.sqrt(this.var());
    }

    public String toString() {
        return "Average ( " + amount + " values ): " + String.format("%7.5f", mean());
    }
}
