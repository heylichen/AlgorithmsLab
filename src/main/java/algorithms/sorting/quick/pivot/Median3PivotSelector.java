package algorithms.sorting.quick.pivot;

/**
 * Created by Chen Li on 2018/2/2.
 */
public class Median3PivotSelector<T extends Comparable<T>> implements PivotSelector<T> {

  @Override
  public int pivot(T[] arr, int low, int high) {
    //median of three rearrange, so that arr[low] is middle value, arr[high] is max value
    int middle = (low + high) / 2;
    return median3(arr, low, middle, high);
  }

  protected int median3(Comparable[] arr, int i, int j, int k) {
    Comparable vi = arr[i];
    Comparable vj = arr[j];
    Comparable vk = arr[k];
    if (vi.compareTo(vj) <= 0) {
      if (vk.compareTo(vi) <= 0) {
        return i;
      } else if (vk.compareTo(vj) > 0) {
        return k;
      } else {
        return k;
      }
    } else {
      if (vk.compareTo(vj) <= 0) {
        return j;
      } else if (vk.compareTo(vi) > 0) {
        return i;
      } else {
        return k;
      }
    }
  }
}
