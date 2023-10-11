package algorithms.strings.sorts.partition;

import algorithms.sorting.ComparableOperations;
import algorithms.strings.sorts.StringOperations;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/4/6.
 */
public class ThreeWayStringPartitioner implements StringPartitioner<String> {

  private StringOperations stringOperations = new StringOperations();
  private ComparableOperations<String> comparableOperations = new ComparableOperations();

  @Override
  public Pair<Integer, Integer> partition(String[] arr, int low, int high, int d) {
    int v = stringOperations.charAt(arr[low], d);
    int i = low + 1;
    int j = i;
    int n = high;
    while (j <= n) {
      int compared = stringOperations.charAt(arr[j], d) - v;
      if (compared == 0) {
        j++;
      } else if (compared < 0) {
        comparableOperations.exchange(arr, i++, j++);
      } else {
        comparableOperations.exchange(arr, j, n--);
      }
    }
    comparableOperations.exchange(arr, low, i - 1);
    return Pair.of(i - 2, j);
  }

}
