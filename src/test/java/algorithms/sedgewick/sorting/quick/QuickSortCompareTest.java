package algorithms.sedgewick.sorting.quick;

import java.util.Random;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.SortCompare;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/1/10.
 */
public class QuickSortCompareTest {

  private SortCompare sortCompare = new SortCompare();
  private Random rand = new Random();

  @Test
  public void insertionQuickTest() throws Exception {
    sortCompare.compare(InsertionQuickSort.class, QuickSort.class, 100000, 5);
    sortCompare.compare(QuickSort.class, InsertionQuickSort.class, 100000, 5);

    sortCompare.compare(InsertionQuickSort.class, QuickSort.class, 400000, 5);
    sortCompare.compare(QuickSort.class, InsertionQuickSort.class, 400000, 5);
  }

  @Test
  public void median3QuickTest() throws Exception {
    sortCompare.compare(Median3QuickSort.class, QuickSort.class, 100000, 5);
    sortCompare.compare(QuickSort.class, Median3QuickSort.class, 100000, 5);

    sortCompare.compare(Median3QuickSort.class, QuickSort.class, 400000, 5);
    sortCompare.compare(QuickSort.class, Median3QuickSort.class, 400000, 5);
  }

  @Test
  public void nintherQuickTest() throws Exception {
    sortCompare.compare(NintherPivotQuickSort.class, QuickSort.class, 100000, 5);
    sortCompare.compare(QuickSort.class, NintherPivotQuickSort.class, 100000, 5);

    sortCompare.compare(NintherPivotQuickSort.class, QuickSort.class, 400000, 5);
    sortCompare.compare(QuickSort.class, NintherPivotQuickSort.class, 400000, 5);
  }

  @Test
  public void pivotQuickTest() throws Exception {
    QuickSortFactory factory = new QuickSortFactory();
    Sort basic = factory.basicQuickSort();
    Sort median3 = factory.media3QuickSort();
    Sort ninther = factory.nintherQuickSort();

    sortCompare.compare(basic, median3, 10000, 5);
    sortCompare.compare(ninther, median3, 10000, 5);
    System.out.println("---------------");

    sortCompare.compare(basic, median3, 100000, 5);
    sortCompare.compare(median3, basic, 100000, 5);

    sortCompare.compare(basic, ninther, 400000, 5);
    sortCompare.compare(ninther, basic, 400000, 5);
    System.out.println("---------------");
    sortCompare.compare(median3, ninther, 400000, 5);
    sortCompare.compare(median3, ninther, 400000, 5);
    sortCompare.compare(ninther, median3, 400000, 5);
    sortCompare.compare(ninther, median3, 400000, 5);
  }

  /**
   * distinct values sort, Fast3WayPartitionQuickSort performs better than ThreeWayPartitionQuickSort
   * QuickSort > ThreeWayPartitionQuickSort
   * Fast3WayPartitionQuickSort > ThreeWayPartitionQuickSor
   * FastThreeWayPartitionQuickSorta lmost the same as   QuickSort
   */
  @Test
  public void entropyOptimalPerfTest() throws Exception {
    sortCompare.compare(ThreeWayPartitionQuickSort.class, Fast3WayPartitionQuickSort.class, 100000, 5);

    sortCompare.compare(ThreeWayPartitionQuickSort.class, Fast3WayPartitionQuickSort.class, 200000, 5);

    sortCompare.compare(ThreeWayPartitionQuickSort.class, Fast3WayPartitionQuickSort.class, 400000, 5);

    sortCompare.compare(Fast3WayPartitionQuickSort.class, QuickSort.class, 400000, 5);
  }

  /**
   * Arrays with large numbers of duplicate keys performance compare
   *
   * ThreeWayPartitionQuickSort > Fast3WayPartitionQuickSort > QuickSort
   */
  @Test
  public void entropyOptimalTest1() {
    Sort sort1 = new QuickSort();
    Sort sort2 = new ThreeWayPartitionQuickSort();
    Sort sort3 = new Fast3WayPartitionQuickSort();
    //
    entropyOptimalSort(sort1, 10000);
    entropyOptimalSort(sort2, 10000);
    entropyOptimalSort(sort3, 10000);
    System.out.println("warming up");
    System.out.println("-----------");
    entropyOptimalSort(sort1, sort2, 40000, 10);
    System.out.println("-----------");
    entropyOptimalSort(sort2, sort3, 400000, 10);
  }

  private void entropyOptimalSort(Sort sort1, Sort sort2, int size, int round) {
    long time1 = 0;
    for (int i = 0; i < round; i++) {
      time1 += entropyOptimalSort(sort1, size);
    }
    long time2 = 0;
    for (int i = 0; i < round; i++) {
      time2 += entropyOptimalSort(sort2, size);
    }
    System.out.println(sort1.getClass().getSimpleName() + "\t\t" + time1 + " ms");
    System.out.println(sort2.getClass().getSimpleName() + "\t\t" + time2 + " ms");
  }

  private long entropyOptimalSort(Sort sort, int size) {
    Integer[] dataArr = new Integer[size * 2];
    int a = rand.nextInt();
    int b = rand.nextInt();
    for (int i = 0; i < size; i++) {
      int j = i * 2;
      dataArr[j] = a;
      dataArr[j + 1] = b;
    }
    long start = System.currentTimeMillis();
    sort.sort(dataArr);
    return System.currentTimeMillis() - start;
  }

}
