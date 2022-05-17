package algorithms.kd.dist;

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
    double dist = 0;
    int d = from.length;
    for (int i = 0; i < d; i++) {
      double diff = from[i] - to[i];
      dist += diff * diff;
    }
    return dist;
  }
}
