package algorithms.sedgewick.sorting.quick.cutoff;

/**
 * Created by Chen Li on 2018/2/2.
 */
public class Cutoff1Sort<T extends Comparable<T>> implements CutoffSort<T> {

  @Override
  public boolean cutoff(T[] arr, int low, int high) {
    return low >= high;
  }
}
