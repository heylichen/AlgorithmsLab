package algorithms.sedgewick.sorting.quick;

import org.junit.Test;

/**
 * Created by Chen Li on 2018/1/31.
 */
public class QuickSelectTest {
  @Test
  public void test() {
    int size = 1000;
    Integer[] arr = new Integer[size];
    for (int i = 0; i < size; i++) {
      arr[i] = size - i;
    }
    QuickSelect select = new QuickSelect();
    int i1 = select.select(arr, 0, size - 1, 4);
    System.out.println(i1);
  }
}