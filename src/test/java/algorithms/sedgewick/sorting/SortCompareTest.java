package algorithms.sedgewick.sorting;

import java.util.Arrays;
import java.util.List;

import algorithms.sedgewick.sorting.insertion.InsertionSort;
import algorithms.sedgewick.sorting.merge.CopyOnceMergeSort;
import algorithms.sedgewick.sorting.merge.InsertionMergeSort;
import algorithms.sedgewick.sorting.merge.MergeSort;
import algorithms.sedgewick.sorting.quick.QuickSortFactory;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

public class SortCompareTest extends AbstractSortCompareTest {

  private List<Pair<Integer, Integer>> tinySizeAndTimesList = Arrays.asList(
      Pair.of(1000, 100)
  );

  @Test
  public void selectionAndInsertion() throws Exception {
    sortCompare(new InsertionSort(), new SelectionSort(), tinySizeAndTimesList);
  }

  @Test
  public void shellVsInsertionTest() throws Exception {
    List<Pair<Integer, Integer>> sizeAndTimesList = Arrays.asList(
        Pair.of(10000, 10)
    );
    sortCompare(new ShellSort(), new InsertionSort(), sizeAndTimesList);
  }

  @Test
  public void shellVsMergeTest() throws Exception {
    List<Pair<Integer, Integer>> sizeAndTimesList = Arrays.asList(
        Pair.of(100000, 10)
    );
    sortCompare(new MergeSort(), new ShellSort(), sizeAndTimesList);
  }

  @Test
  public void mergeVsCopyOnceTest() throws Exception {
    List<Pair<Integer, Integer>> sizeAndTimesList = Arrays.asList(
        Pair.of(200000, 10)
    );
    sortCompare(new CopyOnceMergeSort(), new MergeSort(), sizeAndTimesList);
    Thread.sleep(1000);
  }

  @Test
  public void mergeVsInsertionMergeTest() throws Exception {
    List<Pair<Integer, Integer>> sizeAndTimesList = Arrays.asList(
        Pair.of(200000, 10)
    );
    sortCompare(new InsertionMergeSort(), new MergeSort(), sizeAndTimesList);
    Thread.sleep(1000);
  }

  @Test
  public void mergeVsQuickTest() throws Exception {
    QuickSortFactory factory = new QuickSortFactory();
    sortCompare(factory.median3QuickSort(), new CopyOnceMergeSort(), fastSizeAndTimesList);
  }

  @Test
  public void fastTest() throws Exception {
    List<Pair<Integer, Integer>> sizeAndTimesList = Arrays.asList(
        Pair.of(100000, 20),
        Pair.of(200000, 10),
        Pair.of(400000, 10),
        Pair.of(800000, 5),
        Pair.of(1600000, 2),
        Pair.of(3200000, 2)
    );

    QuickSortFactory factory = new QuickSortFactory();
    sortCompare(factory.bestQuickSort(), new CopyOnceMergeSort(), sizeAndTimesList);
  }
}