package algorithms.sedgewick.sorting.compare;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import algorithms.sedgewick.sorting.compare.AbstractSortCompareTest;
import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.compare.EntropyOptimalSortCompare;
import algorithms.sedgewick.sorting.compare.RandomArraySortCompare;
import algorithms.sedgewick.sorting.compare.SortCompare;
import algorithms.sedgewick.sorting.quick.QuickSortFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/1/10.
 */
@Slf4j
public class QuickSortCompareTest extends AbstractSortCompareTest {

  private QuickSortFactory factory = new QuickSortFactory();
  private Random rand = new Random();
  private RandomArraySortCompare randomArraySortCompare = new RandomArraySortCompare();
  private SortCompare entropyOptimalSortCompare = new EntropyOptimalSortCompare();

  @Test
  public void insertionQuickTest() throws Exception {
    Sort cutoffQuickSort = factory.cutoffQuickSort();
    Sort quick = factory.basicQuickSort();
    sortCompare(cutoffQuickSort, quick, slowSizeAndTimesList);
  }

  @Test
  public void pivotQuickTest() throws Exception {
    QuickSortFactory factory = new QuickSortFactory();
    Sort basic = factory.basicQuickSort();
    Sort median3 = factory.median3QuickSort();
    Sort ninther = factory.nintherQuickSort();

    sortCompare(median3, basic, slowSizeAndTimesList);
    sortCompare(ninther, basic, slowSizeAndTimesList);
    sortCompare(median3, ninther, fastSizeAndTimesList);
  }

  @Test
  public void randomPivotQuickTest() throws Exception {
    QuickSortFactory factory = new QuickSortFactory();
    Sort median3 = factory.median3QuickSort();
    Sort randomMedian3 = factory.randomMedian3QuickSort();

    sortCompare(randomMedian3, median3, fastSizeAndTimesList);
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
    List<Pair<Integer, Integer>> tinySizeAndTimesList = Arrays.asList(
        Pair.of(100000, 10),
        Pair.of(200000, 10),
        Pair.of(400000, 5)
    );
    log.info("----------------entropy optimal array, three way partition performs better.");
    sortCompare(factory.threeWayPartitionQuickSort(), factory.basicQuickSort(),
                tinySizeAndTimesList, entropyOptimalSortCompare);
    sortCompare(factory.fast3WayPartitionQuickSort(), factory.basicQuickSort(),
                tinySizeAndTimesList, entropyOptimalSortCompare);
    log.info("----------------random array, fast three way partition performs better.");
    sortCompare(factory.basicQuickSort(), factory.threeWayPartitionQuickSort(),
                tinySizeAndTimesList, randomArraySortCompare);
    sortCompare(factory.basicQuickSort(), factory.fast3WayPartitionQuickSort(),
                tinySizeAndTimesList, randomArraySortCompare);
    sortCompare(factory.fast3WayPartitionQuickSort(), factory.threeWayPartitionQuickSort(),
                tinySizeAndTimesList, randomArraySortCompare);
  }


  @Test
  public void bestQuickSortTest() throws Exception {
    log.info("-------------random distinct data array");
    sortCompare(factory.bestQuickSort(), factory.median3QuickSort(), fastSizeAndTimesList);
    log.info("-------------entropy optimal array");
    sortCompare(factory.bestQuickSort(), factory.median3QuickSort(), fastSizeAndTimesList, entropyOptimalSortCompare);
  }
}
