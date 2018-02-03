package algorithms.sedgewick.sorting.quick.partition;

import algorithms.sedgewick.sorting.AbstractComparableOperator;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/2/2.
 */
public class Fast3WayPartitioner<T extends Comparable<T>> extends AbstractComparableOperator implements Partitioner<T> {

  @Override
  public Pair<Integer, Integer> partition(T[] arr, int low, int high) {
    //fast 3 way partition
    T v = arr[low];
    int i = low + 1;
    int j = high;
    int p = i;
    int q = j;
    while (i <= j) {
      while (i <= j) {
        int compared = arr[i].compareTo(v);
        if (compared == 0) {
          exchange(arr, i, p);
          p++;
          i++;
          continue;
        } else if (compared < 0) {
          i++;
          continue;
        } else {
          //>
          break;
        }
      }
      while (i <= j) {
        int compared = arr[j].compareTo(v);
        if (compared == 0) {
          exchange(arr, j, q);
          q--;
          j--;
        } else if (compared > 0) {
          j--;
        } else {
          break;
        }
      }
      if (i <= j) {
        exchange(arr, i, j);
        i++;
        j--;
      }
    }

    //swap equal keys to right position
    swapSubArray(arr, low, j, p - 1);
    swapSubArray(arr, i, high, q);
    return Pair.of(low + j - p, high - q + i);
  }

  /**
   * swap left subarray and right sub array
   *
   * @param arr    the array
   * @param low    from index
   * @param high   to index
   * @param middle the index splitting the array, to low ~ middle,  middle+1 ~ high
   */
  protected void swapSubArray(Comparable[] arr, int low, int high, int middle) {
    int size = high - low + 1;
    int leftSize = middle - low + 1;
    int rightSize = size - leftSize;
    if (size < 1 || leftSize < 1 || rightSize < 1) {
      return;
    }
    int halfSize = size / 2;
    if (leftSize <= halfSize) {
      //from left sub array
      for (int i = low; i <= middle; i++) {
        exchange(arr, i, i + rightSize);
      }
    } else {
      //from right sub array
      for (int i = middle + 1; i <= high; i++) {
        exchange(arr, i, i - leftSize);
      }
    }
  }
}
