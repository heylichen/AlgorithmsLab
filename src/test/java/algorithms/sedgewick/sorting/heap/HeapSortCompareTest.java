package algorithms.sedgewick.sorting.heap;

import java.util.Arrays;
import java.util.List;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.compare.AbstractSortCompareTest;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/2/11.
 */
public class HeapSortCompareTest extends AbstractSortCompareTest {

  private HeapSortFactory factory = new HeapSortFactory();

  @Test
  public void lessExchHeapSortTest() throws Exception {
    Sort defaultHeapSort = factory.defaultHeapSort();
    Sort noExchangeHeapSort = factory.noExchangeHeapSort();
    sortCompare(defaultHeapSort, noExchangeHeapSort, middleSizeAndTimesList);
  }

  @Test
  public void multiwayHeapSortTest() throws Exception {
    Sort defaultHeapSort = factory.defaultHeapSort();
    Sort threeWayHeapSort = factory.multiwayHeapSort(3);
    sortCompare(threeWayHeapSort, defaultHeapSort, fastSizeAndTimesList);

    Sort fourWayHeapSort = factory.multiwayHeapSort(4);
    sortCompare(fourWayHeapSort, defaultHeapSort, fastSizeAndTimesList);

    Sort eightWayHeapSort = factory.multiwayHeapSort(8);
    sortCompare(eightWayHeapSort, defaultHeapSort, fastSizeAndTimesList);
  }

  @Test
  public void comparesTest() throws Exception {
    List<Pair<Integer, Integer>> middleSizeAndTimesList = Arrays.asList(
        Pair.of(400000, 5)
    );
    HeapSort noExchangeHeapSort = factory.noExchangeHeapSort();
    LessCompareHeapSort lessCompareHeapSort = factory.lessCompareHeapSort();
    sortCompare(lessCompareHeapSort, noExchangeHeapSort, middleSizeAndTimesList);
    System.out.println(lessCompareHeapSort.getCompares());
    System.out.println(noExchangeHeapSort.getCompares());
  }
}
