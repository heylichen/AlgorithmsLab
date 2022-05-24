package algorithms.kd.nn;

import algorithms.kd.Point;
import algorithms.kd.dist.Distance;
import lombok.Getter;

@Getter
public class TargetDistance {
  private final Point target;
  private final Distance distance;

  public TargetDistance(Point target, Distance distance) {
    this.target = target;
    this.distance = distance;
  }

  public double calculateDistanceTo(Point point) {
    return distance.getDistance(target.getCoordinates(), point.getCoordinates());
  }

  public double getTargetCoordinateIn(int dimension) {
    return target.getInDimension(dimension);
  }
}
