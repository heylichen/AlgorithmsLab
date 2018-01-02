package algorithms.sedgewick.sorting.elementrary.function;

import algorithms.sedgewick.sorting.elementrary.Sort;
import algorithms.sedgewick.sorting.elementrary.SortCompare;
import algorithms.sedgewick.sorting.elementrary.merge.InsertionMergeSort;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class InsertionMergeSortTest extends SortTest {

  @Override
  protected Sort newInstance() {
    return new InsertionMergeSort();
  }

  @Test
  public void sortLargeArrayTest() {
    long start = System.currentTimeMillis();
    sortArrayBySize(200000);
    long end = System.currentTimeMillis();
    System.out.println(end - start);
  }


  public void findBestThreshold() {
    SortCompare sortCompare = new SortCompare();
    InsertionMergeSort sort = new InsertionMergeSort();
    List<Pair<Long, Integer>> logs = new ArrayList<>();
    for (int i = 4; i < 100; i = i + 10) {
      sort.setSmallSize(i);
      long duration = sortCompare.timeRandomInput(sort, 400000, 10);
      logs.add(Pair.of(duration, i));
    }
    Collections.sort(logs, (p1, p2) -> p1.getLeft().compareTo(p2.getLeft()));
    log.info(JSON.toJSONString(logs));
  }
}