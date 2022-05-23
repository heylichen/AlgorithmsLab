package algorithms.kd.nn;

import algorithms.kd.dist.Distance;
import lombok.Getter;

@Getter
public class TargetDistance {
  private final double[] target;
  private final Distance distance;

  public TargetDistance(double[] target, Distance distance) {
    this.target = target;
    this.distance = distance;
  }

  public double getDistance(double[] from, double[] to) {
    return distance.getDistance(from, to);
  }
}
