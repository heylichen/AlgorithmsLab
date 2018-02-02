package algorithms.sedgewick.sorting.quick.cutoff;

/**
 * Created by Chen Li on 2018/2/2.
 */
public interface CutoffSort<T extends Comparable<T>> {

  /**
   * if array length is no more than a specified number,
   * sort the array and return true, else return false.
   */
  boolean cutoff(T[] arr, int low, int high);

}
