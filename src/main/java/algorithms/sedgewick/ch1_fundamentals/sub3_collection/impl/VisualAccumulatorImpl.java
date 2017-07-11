package algorithms.sedgewick.ch1_fundamentals.sub3_collection.impl;


import edu.princeton.cs.algs4.StdDraw;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisualAccumulatorImpl implements VisualAccumulator {
    private double total;
    private int N;

    public VisualAccumulatorImpl(int trials, double max) {
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(.005);
    }

    @Override
    public void addDataValue(double val) {
        N++;
        total += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, avg());
    }

    @Override
    public double avg() {
        return total / N;
    }

    @Override
    public String toString() {
        return "total :" + total + ", N:" + N + ": avg:" + avg();
    }

}