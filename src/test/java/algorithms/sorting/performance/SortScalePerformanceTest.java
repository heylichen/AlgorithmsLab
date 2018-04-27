package algorithms.sorting.performance;

import java.util.ArrayList;
import java.util.List;

import algorithms.sedgewick.utils.SizeDoubleIterable;
import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.compare.AbstractSortCompareTest;
import algorithms.sedgewick.sorting.compare.SortCompareContext;
import algorithms.sedgewick.sorting.compare.SortSizeConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SortScalePerformanceTest extends AbstractSortCompareTest {

  protected Sort sort;
  @Autowired
  protected SortCompareContext randomSortCompareContext;
  protected SizeDoubleIterable sizeDoubleItarable = new SizeDoubleIterable();

  protected abstract Sort newInstance();

  @Before
  public void setUp() throws Exception {
    sort = newInstance();
  }

  @Test
  public void scaleTest() throws InstantiationException, IllegalAccessException {
    List<Integer> sizes = sizeDoubleItarable.toSizes(1000, 20000);
    List<SortSizeConfig> sizeConfigs = new ArrayList<>();
    for (Integer size : sizes) {
      sizeConfigs.add(new SortSizeConfig(size, 10));
    }

    SortCompareContext context = randomSortCompareContext.load(sort, null, sizeConfigs);

    compareTest(context);
  }
}