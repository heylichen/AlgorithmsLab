package algorithms.kd.nn;

import algorithms.kd.dist.Distance;
import lombok.Getter;

@Getter
public class NNTargetDistance {
  private final double[] keyVector;
  private final Distance distance;

  public NNTargetDistance(double[] keyVector, Distance distance) {
    this.keyVector = keyVector;
    this.distance = distance;
  }

  public double getDistance(double[] from, double[] to) {
    return distance.getDistance(from, to);
  }
}
