package algorithms.kd;

import algorithms.kd.dist.Distance;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class HyperRectangle {
  @Getter
  private final double[] minKey;
  @Getter
  private final double[] maxKey;

  public HyperRectangle(double[] minKey, double[] maxKey) {
    this.minKey = minKey;
    this.maxKey = maxKey;
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
    return new HyperRectangle(min, max);
  }

  public List<HyperRectangle> cutByPivot(int currentDimension, double pivot) {
    int dimensions = minKey.length;
    double[] leftMax = new double[dimensions];
    double[] rightMin = new double[dimensions];
    for (int i = 0; i < dimensions; i++) {
      double leftV = i == currentDimension ? pivot : maxKey[i];
      leftMax[i] = leftV;
      double rightV = i == currentDimension ? pivot : minKey[i];
      rightMin[i] = rightV;
    }
    HyperRectangle left = new HyperRectangle(minKey, leftMax);
    HyperRectangle right = new HyperRectangle(rightMin, maxKey);
    return Arrays.asList(left, right);
  }

  /**
   * get distance of the closest point to target
   * @param target
   * @param distance
   * @return
   */
  public double getDistance(double[] target, Distance distance) {
    double[] closestVector = getClosestPoint(target);
    return distance.getDistance(closestVector, target);
  }

  /**
   * get the closest point to target
   * @param target
   * @return
   */
  private double[] getClosestPoint(double[] target) {
    int d = target.length;
    double[] closestVector = new double[d];
    for (int i = 0; i < d; i++) {
      double targetV = target[i];
      double minV = minKey[i];
      double maxV = maxKey[i];

      double v;
      if (targetV <= minV) {
        v = minV;
      } else if (minV < targetV && targetV < maxV) {
        v = targetV;
      } else{
        v = maxV;
      }
      closestVector[i] = v;
    }
    return closestVector;
  }

  /**
   * check if a region in kd tree has intersection with the query range.
   * query range is left inclusive and right inclusive in any dimension.
   * but a region inside kd tree is left inclusive, right exclusive.
   * @param rangeRectangle is the search range, not a range in kd tree, attention!
   * @return
   */
  public boolean hasIntersectionWith(HyperRectangle rangeRectangle) {
    int dimensions = minKey.length;
    for (int dimension = 0; dimension < dimensions; dimension++) {
      double thisMinV = minKey[dimension];
      double thisMaxV = maxKey[dimension];

      double thatMinV = rangeRectangle.minKey[dimension];
      double thatMaxV = rangeRectangle.maxKey[dimension];
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

      double thatMinV = rangeRectangle.minKey[dimension];
      double thatMaxV = rangeRectangle.maxKey[dimension];
      if (pointV < thatMinV || pointV > thatMaxV) {
        return false;
      }
    }
    return true;
  }

}
