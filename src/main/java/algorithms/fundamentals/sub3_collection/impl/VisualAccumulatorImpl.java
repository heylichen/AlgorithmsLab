package algorithms.fundamentals.sub3_collection.impl;


import edu.princeton.cs.algs4.StdDraw;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisualAccumulatorImpl implements VisualAccumulator {
  private boolean enabled;
  private double total;
  private int N;
  private boolean draw = true;

  public VisualAccumulatorImpl(int trials, double max) {
    StdDraw.setXscale(0, trials);
    StdDraw.setYscale(0, max);
    StdDraw.setPenRadius(.005);
    enabled = true;
  }

  @Override
  public void addDataValue(double val) {
    if (enabled) {
      N++;
      total += val;
      if (draw) {
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, avg());
      }
    }
  }

  @Override
  public double avg() {
    return total / N;
  }

  @Override
  public String toString() {
    return "total :" + total + ", N:" + N + ": avg:" + avg();
  }

  @Override
  public void enableDraw(boolean enable) {
    this.enabled = enable;
  }
}