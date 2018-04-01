package algorithms.sedgewick.sorting.compare;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.quick.QuickSortFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Chen Li on 2018/4/1.
 */
@Slf4j
public class QuickSortCompareTest extends AbstractSortCompareTest {

  private QuickSortFactory factory = new QuickSortFactory();
  @Autowired
  private SortCompareContext randomSortCompareContext;
  @Autowired
  private SortCompareContext entropyOptimalSortCompareContext;

  @Test
  public void insertionQuickTest() throws Exception {
    Sort cutoffQuickSort = factory.cutoffQuickSort();
    Sort quick = factory.basicQuickSort();

    SortCompareContext context = randomSortCompareContext.load(cutoffQuickSort, quick,
                                                               SortSizeConfigs.slow2SizeAndTimesList
    );
    compareTest(context);
  }

  @Test
  public void pivotQuickTest() throws Exception {
    QuickSortFactory factory = new QuickSortFactory();
    Sort basic = factory.basicQuickSort();
    Sort median3 = factory.median3QuickSort();
    Sort ninther = factory.nintherQuickSort();

    SortCompareContext context = randomSortCompareContext.load(median3, basic, SortSizeConfigs.slow1SizeAndTimesList);
    compareTest(context);

    context = randomSortCompareContext.load(ninther, basic, SortSizeConfigs.slow1SizeAndTimesList);
    compareTest(context);

    context = randomSortCompareContext.load(median3, ninther, SortSizeConfigs.slow1SizeAndTimesList);
    compareTest(context);
  }

  @Test
  public void randomPivotQuickTest() throws Exception {
    QuickSortFactory factory = new QuickSortFactory();
    Sort median3 = factory.median3QuickSort();
    Sort randomMedian3 = factory.randomMedian3QuickSort();

    SortCompareContext context =
        randomSortCompareContext.load(median3, randomMedian3, SortSizeConfigs.slow1SizeAndTimesList);
    compareTest(context);
  }

  /**
   * distinct values sort, Fast3WayPartitionQuickSort performs better than ThreeWayPartitionQuickSort
   * QuickSort > ThreeWayPartitionQuickSort
   * Fast3WayPartitionQuickSort > ThreeWayPartitionQuickSor
   * FastThreeWayPartitionQuickSorta almost the same as   QuickSort
   */
  @Test
  public void entropyOptimalTest() throws Exception {
    QuickSortFactory factory = new QuickSortFactory();
    log.info("----------------entropy optimal array, three way partition performs better.");

    SortCompareContext context = entropyOptimalSortCompareContext.load(
        factory.threeWayPartitionQuickSort(), factory.basicQuickSort(),
        SortSizeConfigs.slow1SizeAndTimesList
    );
    compareTest(context);

    context = entropyOptimalSortCompareContext.load(
        factory.fast3WayPartitionQuickSort(), factory.basicQuickSort(),
        SortSizeConfigs.slow1SizeAndTimesList
    );
    compareTest(context);
    log.info("----------------random array, fast three way partition performs better.");

    context = randomSortCompareContext.load(
        factory.basicQuickSort(), factory.threeWayPartitionQuickSort(),
        SortSizeConfigs.slow1SizeAndTimesList
    );
    compareTest(context);

    context = randomSortCompareContext.load(
        factory.basicQuickSort(), factory.fast3WayPartitionQuickSort(),
        SortSizeConfigs.slow1SizeAndTimesList
    );
    compareTest(context);
    context = randomSortCompareContext.load(
        factory.fast3WayPartitionQuickSort(), factory.threeWayPartitionQuickSort(),
        SortSizeConfigs.slow1SizeAndTimesList
    );
    compareTest(context);
  }

  @Test
  public void bestQuickSortTest() throws Exception {
    log.info("-------------random distinct data array");
    SortCompareContext context = randomSortCompareContext.load(factory.bestQuickSort(), factory.median3QuickSort(),
                                                               SortSizeConfigs.slow1SizeAndTimesList
    );
    compareTest(context);

    log.info("-------------entropy optimal array");
    context = entropyOptimalSortCompareContext.load(factory.bestQuickSort(), factory.median3QuickSort(),
                                                    SortSizeConfigs.slow1SizeAndTimesList
    );
    compareTest(context);
  }
}
