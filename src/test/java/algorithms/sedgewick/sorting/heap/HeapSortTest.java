package algorithms.sedgewick.sorting.heap;

import com.alibaba.fastjson.JSON;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/2/6.
 */
public class HeapSortTest {

  @Test
  public void test() {
    int size = 10;
    Double[] arr = new Double[size+1];
    for (int i = 0; i < size; i++) {
      arr[1 + i]= StdRandom.uniform();
    }
    HeapSort sort = new HeapSort();
    sort.sort(arr);
    System.out.println(JSON.toJSONString(arr));
  }
}