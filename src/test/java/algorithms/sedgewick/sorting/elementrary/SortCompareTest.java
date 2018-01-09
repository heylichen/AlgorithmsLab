package algorithms.sedgewick.sorting.elementrary;

import algorithms.sedgewick.sorting.elementrary.merge.CopyOnceMergeSort;
import algorithms.sedgewick.sorting.elementrary.merge.InsertionMergeSort;
import algorithms.sedgewick.sorting.elementrary.merge.MergeSort;
import algorithms.sedgewick.sorting.elementrary.quick.QuickSort;
import org.junit.Test;

public class SortCompareTest {

  private SortCompare sortCompare = new SortCompare();

  @Test
  public void selectionAndInsertion() throws Exception {
    Thread.sleep(1000);
    sortCompare.compare(InsertionSort.class, SelectionSort.class, 1000, 100);
    sortCompare.compare(InsertionSort.class, SelectionSort.class, 1000, 100);

    sortCompare.compare(SelectionSort.class, InsertionSort.class, 1000, 100);
    sortCompare.compare(SelectionSort.class, InsertionSort.class, 1000, 100);
  }

  @Test
  public void shellVsInsertionTest() throws Exception {
    Thread.sleep(1000);
    sortCompare.compare(ShellSort.class, InsertionSort.class, 10000, 10);
    sortCompare.compare(InsertionSort.class, ShellSort.class, 10000, 10);
  }


  @Test
  public void shellVsMergeTest() throws Exception {
    Thread.sleep(1000);
    sortCompare.compare(MergeSort.class, ShellSort.class, 100000, 10);
    sortCompare.compare(ShellSort.class, MergeSort.class, 100000, 10);
  }

  @Test
  public void mergeVsCopyOnceTest() throws Exception {
    Thread.sleep(1000);
    sortCompare.compare(CopyOnceMergeSort.class, MergeSort.class, 100000, 10);
    sortCompare.compare(MergeSort.class, CopyOnceMergeSort.class, 100000, 10);
  }

  @Test
  public void mergeVsInsertionMergeTest() throws Exception {
    Thread.sleep(1000);
    sortCompare.compare(InsertionMergeSort.class, MergeSort.class, 200000, 10);
    sortCompare.compare(MergeSort.class, InsertionMergeSort.class, 200000, 10);
  }

  @Test
  public void mergeVsQuickTest() throws Exception {
    Thread.sleep(1000);
    sortCompare.compare(CopyOnceMergeSort.class, QuickSort.class, 200000, 10);
    sortCompare.compare(QuickSort.class, CopyOnceMergeSort.class, 200000, 10);
  }
}