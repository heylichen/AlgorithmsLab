package algorithms.kd;

import algorithms.kd.nn.TargetDistance;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class HyperRectangle {

  @Getter
  private final Point min;
  @Getter
  private final Point max;

  public HyperRectangle(double[] min, double[] max) {
    this(new Point(min), new Point(max));
  }

  public HyperRectangle(Point min, Point max) {
    this.min = min;
    this.max = max;
  }

  public static HyperRectangle getInfiniteRect(int dimensions) {
    double[] min = new double[dimensions];
    double[] max = new double[dimensions];
    double minV = Double.MIN_VALUE;
    double maxV = Double.MAX_VALUE;
    for (int i = 0; i < dimensions; i++) {
      min[i] = minV;
      max[i] = maxV;
    }
    return new HyperRectangle(new Point(min), new Point(max));
  }

  public List<HyperRectangle> cutByPivot(int currentDimension, double pivot) {
    int dimensions = min.getDimensions();
    double[] leftMax = new double[dimensions];
    double[] rightMin = new double[dimensions];
    for (int i = 0; i < dimensions; i++) {
      double leftV = i == currentDimension ? pivot : getMax(i);
      leftMax[i] = leftV;
      double rightV = i == currentDimension ? pivot : getMin(i);
      rightMin[i] = rightV;
    }
    HyperRectangle left = new HyperRectangle(min, new Point(leftMax));
    HyperRectangle right = new HyperRectangle(new Point(rightMin), max);
    return Arrays.asList(left, right);
  }

  public double getMin(int dimension) {
    return min.getInDimension(dimension);
  }

  public double getMax(int dimension) {
    return max.getInDimension(dimension);
  }

  /**
   * calculate distance of the closest point in this rectangle to target
   * @param targetDistance
   * @return
   */
  public double getDistanceTo(TargetDistance targetDistance) {
    Point closestPoint = getClosestPointTo(targetDistance.getTarget());
    return targetDistance.calculateDistanceTo(closestPoint);
  }

  /**
   * get the closest point to target in this rectangle
   *
   * @param target
   * @return
   */
  private Point getClosestPointTo(Point target) {
    int d = target.getDimensions();
    double[] targetCoordinates = target.getCoordinates();
    double[] closestVector = new double[d];
    for (int i = 0; i < d; i++) {
      double targetV = targetCoordinates[i];
      double minV = getMin(i);
      double maxV = getMax(i);

      double v;
      if (targetV <= minV) {
        v = minV;
      } else if (minV < targetV && targetV < maxV) {
        v = targetV;
      } else {
        v = maxV;
      }
      closestVector[i] = v;
    }
    return new Point(closestVector);
  }

  /**
   * check if a region in kd tree has intersection with the query range.
   * query range is left inclusive and right inclusive in any dimension.
   * but a region inside kd tree is left inclusive, right exclusive.
   *
   * @param rangeRectangle is the search range, not a range in kd tree, attention!
   * @return
   */
  public boolean hasIntersectionWith(HyperRectangle rangeRectangle) {
    int dimensions = min.getDimensions();
    for (int dimension = 0; dimension < dimensions; dimension++) {
      double thisMinV = getMin(dimension);
      double thisMaxV = getMax(dimension);

      double thatMinV = rangeRectangle.getMin(dimension);
      double thatMaxV = rangeRectangle.getMax(dimension);
      // if no intersection in any dimension, then no intersection exist between
      if ((thatMinV >= thisMaxV) || (thatMaxV < thisMinV)) {
        // for a dimension value in kd tree regions, min is inclusive, max is exclusive in this implementation
        return false;
      }
    }
    return true;
  }

  public static boolean isPointInClosedRange(Point p, HyperRectangle rangeRectangle) {
    int dimensions = p.getCoordinates().length;
    for (int dimension = 0; dimension < dimensions; dimension++) {
      double pointV = p.getInDimension(dimension);

      double thatMinV = rangeRectangle.getMin(dimension);
      double thatMaxV = rangeRectangle.getMax(dimension);
      if (pointV < thatMinV || pointV > thatMaxV) {
        return false;
      }
    }
    return true;
  }

}
