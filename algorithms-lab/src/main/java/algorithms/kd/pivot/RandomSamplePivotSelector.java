package algorithms.kd.pivot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

public class RandomSamplePivotSelector implements PivotSelector {
  private static final int SMALL_THRESHOLD = 7;
  private static final Random RAND = new Random();

  private final int smallThreshold;

  public RandomSamplePivotSelector(int smallThreshold) {
    this.smallThreshold = smallThreshold;
  }

  public RandomSamplePivotSelector() {
    this(SMALL_THRESHOLD);
  }

  public <T, K extends Comparable<K>> T extractPivot(List<T> points, int d, BiFunction<T, Integer, K> byDimensionKeyGetter) {
    if (points.isEmpty()) {
      throw new IllegalArgumentException("empty collection not support");
    }
    if (points.size() <= 2) {
      return points.remove(1);
    }
    List<T> sample = getSample(points);
    sample.sort((a, b) -> {
      K da = byDimensionKeyGetter.apply(a, d);
      K db = byDimensionKeyGetter.apply(b, d);
      int compared = da.compareTo(db);
      if (compared < 0) {
        return -1;
      } else if (compared == 0) {
        return 0;
      } else {
        return 1;
      }
    });
    int i = sample.size() / 2;
    T pivotPoint = sample.get(i);
    sample.removeIf(p -> p == pivotPoint);
    return pivotPoint;
  }

  private <T> List<T> getSample(List<T> pointList) {
    if (pointList.size() <= smallThreshold) {
      return pointList;
    }
    int boundary = pointList.size();
    List<T> sampleValues = new ArrayList<>(smallThreshold);
    for (int i = 0; i < smallThreshold; i++) {
      int index = RAND.nextInt(boundary);
      sampleValues.add(pointList.get(index));

      T temp = pointList.get(0);
      pointList.set(0, pointList.get(index));
      pointList.set(index, temp);
      boundary--;
    }
    return sampleValues;
  }
}
