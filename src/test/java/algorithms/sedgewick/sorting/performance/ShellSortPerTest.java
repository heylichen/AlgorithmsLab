package algorithms.sedgewick.sorting.performance;

import java.util.ArrayList;
import java.util.List;

import algorithms.sedgewick.sorting.ShellSort;
import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.compare.SortCompareContext;
import algorithms.sedgewick.sorting.compare.SortSizeConfig;
import org.junit.Test;

public class ShellSortPerTest extends SortScalePerformanceTest {

  @Override
  protected Sort newInstance() {
    return new ShellSort();
  }

  @Test
  public void scaleTest() throws InstantiationException, IllegalAccessException {

    List<Integer> sizes = sizeDoubleItarable.toSizes(1000, 320000);
    List<SortSizeConfig> sizeConfigs = new ArrayList<>();
    for (Integer size : sizes) {
      sizeConfigs.add(new SortSizeConfig(size, 10));
    }

    SortCompareContext context = randomSortCompareContext.load(sort, null, sizeConfigs);

    compareTest(context);
  }
}
