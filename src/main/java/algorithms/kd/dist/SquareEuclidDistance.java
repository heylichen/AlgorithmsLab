package algorithms.kd.dist;

import java.math.BigDecimal;

/**
 * only need relative distance, so don't calculate the square root
 */
public class SquareEuclidDistance implements Distance {
  public static final SquareEuclidDistance INSTANCE = new SquareEuclidDistance();

  @Override
  public double getDistance(double[] from, double[] to) {
    if (from == null || to == null) {
      throw new IllegalArgumentException("null point");
    }
    if (from.length != to.length) {
      throw new IllegalArgumentException("non-compatible dimensions");
    }
    BigDecimal bd = BigDecimal.ZERO;
    int d = from.length;
    for (int i = 0; i < d; i++) {
      BigDecimal diff = BigDecimal.valueOf(from[i]).subtract(BigDecimal.valueOf(to[i]));
      bd = bd.add(diff.multiply(diff));
    }
    return bd.doubleValue();
  }
}
