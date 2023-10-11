package algorithms.sorting.heap;

import java.util.Arrays;
import java.util.List;

import algorithms.sorting.Sort;
import algorithms.sorting.compare.AbstractSortCompareTest;
import algorithms.sorting.compare.SortCompareContext;
import algorithms.sorting.compare.SortSizeConfig;
import algorithms.sorting.compare.SortSizeConfigs;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Chen Li on 2018/2/11.
 */
public class HeapSortCompareTest extends AbstractSortCompareTest {

  @Autowired
  private SortCompareContext randomSortCompareContext;
  private HeapSortFactory factory = new HeapSortFactory();

  @Test
  public void lessExchHeapSortTest() throws Exception {
    Sort defaultHeapSort = factory.defaultHeapSort();
    Sort noExchangeHeapSort = factory.noExchangeHeapSort();

    SortCompareContext context = randomSortCompareContext.load(defaultHeapSort, noExchangeHeapSort,
                                                               SortSizeConfigs.middleSizeAndTimesList
    );
    compareTest(context);
  }

  @Test
  public void multiwayHeapSortTest() throws Exception {
    Sort defaultHeapSort = factory.defaultHeapSort();
    Sort threeWayHeapSort = factory.multiwayHeapSort(3);
    SortCompareContext context = randomSortCompareContext.load(defaultHeapSort, threeWayHeapSort,
                                                               SortSizeConfigs.middleSizeAndTimesList
    );
    compareTest(context);

    Sort fourWayHeapSort = factory.multiwayHeapSort(4);
    context = randomSortCompareContext.load(defaultHeapSort, fourWayHeapSort,
                                            SortSizeConfigs.middleSizeAndTimesList
    );
    compareTest(context);

    Sort eightWayHeapSort = factory.multiwayHeapSort(8);
    context = randomSortCompareContext.load(defaultHeapSort, eightWayHeapSort,
                                            SortSizeConfigs.middleSizeAndTimesList
    );
    compareTest(context);
  }

  @Test
  public void comparesTest() throws Exception {
    List<SortSizeConfig> middleSizeAndTimesList = Arrays.asList(
        new SortSizeConfig(400000, 5)
    );

    HeapSort noExchangeHeapSort = factory.noExchangeHeapSort();
    LessCompareHeapSort lessCompareHeapSort = factory.lessCompareHeapSort();
    SortCompareContext context = randomSortCompareContext.load(noExchangeHeapSort, lessCompareHeapSort,
                                                               SortSizeConfigs.middleSizeAndTimesList
    );
    compareTest(context);

    System.out.println(lessCompareHeapSort.getCompares());
    System.out.println(noExchangeHeapSort.getCompares());
  }
}
