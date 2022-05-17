package algorithms.kd;

import algorithms.kd.dist.Distance;
import lombok.Getter;

@Getter
class NN1TargetDistance {
  private final double[] keyVector;
  private final Distance distance;

  public NN1TargetDistance(double[] keyVector, Distance distance) {
    this.keyVector = keyVector;
    this.distance = distance;
  }

  public double getDistance(double[] from, double[] to) {
    return distance.getDistance(from, to);
  }
}
