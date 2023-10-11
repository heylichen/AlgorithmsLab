package algorithms.sorting.compare;

import algorithms.sorting.SelectionSort;
import algorithms.sorting.ShellSort;
import algorithms.sorting.heap.HeapSortFactory;
import algorithms.sorting.insertion.InsertionSort;
import algorithms.sorting.merge.CopyOnceMergeSort;
import algorithms.sorting.merge.InsertionMergeSort;
import algorithms.sorting.merge.MergeSort;
import algorithms.sorting.quick.QuickSortFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Chen Li on 2018/4/1.
 */

public class GeneralSortCompareTest extends AbstractSortCompareTest {

  @Autowired
  private SortCompareContext randomSortCompareContext;

  @Test
  public void insertionVsSelectionTest() {
    SortCompareContext context = randomSortCompareContext.load(new InsertionSort(), new SelectionSort(),
                                                               SortSizeConfigs.slow2SizeAndTimesList
    );
    compareTest(context);
  }

  @Test
  public void shellVsInsertionTest() {
    SortCompareContext context = randomSortCompareContext.load(new ShellSort(), new InsertionSort(),
                                                               SortSizeConfigs.slow2SizeAndTimesList
    );
    compareTest(context);
  }

  @Test
  public void shellVsMergeTest() throws Exception {
    SortCompareContext context = randomSortCompareContext.load(new MergeSort(), new ShellSort(),
                                                               SortSizeConfigs.slow1SizeAndTimesList
    );
    compareTest(context);
  }

  @Test
  public void mergeVsCopyOnceTest() throws Exception {
    SortCompareContext context = randomSortCompareContext.load(new CopyOnceMergeSort(), new MergeSort(),
                                                               SortSizeConfigs.middleSizeAndTimesList
    );
    compareTest(context);
  }

  @Test
  public void mergeVsInsertionMergeTest() throws Exception {
    SortCompareContext context = randomSortCompareContext.load(new InsertionMergeSort(), new MergeSort(),
                                                               SortSizeConfigs.slow1SizeAndTimesList
    );
    compareTest(context);
  }

  @Test
  public void mergeVsHeapSortTest() throws Exception {
    HeapSortFactory factory = new HeapSortFactory();

    SortCompareContext context = randomSortCompareContext.load(new CopyOnceMergeSort(), factory.bestHeapSort(),
                                                               SortSizeConfigs.slow1SizeAndTimesList
    );
    compareTest(context);
  }

  @Test
  public void mergeVsQuickTest() throws Exception {
    QuickSortFactory factory = new QuickSortFactory();

    SortCompareContext context = randomSortCompareContext.load(new CopyOnceMergeSort(), factory.median3QuickSort(),
                                                               SortSizeConfigs.largeSizeConfig
    );
    compareTest(context);
  }

  @Test
  public void fastTest() throws Exception {
    QuickSortFactory factory = new QuickSortFactory();
    SortCompareContext context = randomSortCompareContext.load(new CopyOnceMergeSort(), factory.bestQuickSort(),
                                                               SortSizeConfigs.middleSizeAndTimesList
    );
    compareTest(context);
  }
}
