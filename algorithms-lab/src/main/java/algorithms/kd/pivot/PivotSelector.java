package algorithms.kd.pivot;

import java.util.List;
import java.util.function.BiFunction;

public interface PivotSelector {
  /**
   * select the pivot point and remove from the points
   * @param points
   * @param d
   * @return
   */
  <T, K extends Comparable<K>> T extractPivot(List<T> points, int d, BiFunction<T, Integer, K> byDimensionKeyGetter);
}
