package algorithms.sorting.compare;

import algorithms.sedgewick.sorting.merge.CopyOnceMergeSort;
import algorithms.sedgewick.strings.sorts.MSDSort;
import algorithms.sedgewick.strings.sorts.ThreeWayStringQuickSort;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Chen Li on 2018/4/1.
 */
public class StringSortCompareTest extends AbstractSortCompareTest {

  @Autowired
  private SortCompareContext platesSortCompareContext;

  @Autowired
  private SortCompareContext equalStringSortCompareContext;

  @Test
  public void mergeVsMsd() {
    SortCompareContext context = platesSortCompareContext.load(new CopyOnceMergeSort(), new MSDSort(),
                                                               SortSizeConfigs.largeSizeConfig
    );
    compareTest(context);
  }

  @Test
  public void threeWayStringQuickVsMerge() {
    SortCompareContext context = platesSortCompareContext.load(new CopyOnceMergeSort(), new ThreeWayStringQuickSort(),
                                                               SortSizeConfigs.largeSizeConfig
    );
    compareTest(context);
  }

  @Test
  public void threeWayStringQuickVsMerge2() {
    SortCompareContext context = equalStringSortCompareContext.load(new CopyOnceMergeSort(), new ThreeWayStringQuickSort(),
                                                               SortSizeConfigs.largeSizeConfig
    );
    compareTest(context);
  }


  @Test
  public void msdVsthreeWayStringQuick() {
    SortCompareContext context = equalStringSortCompareContext.load(new ThreeWayStringQuickSort(), new MSDSort(),
                                                               SortSizeConfigs.middleSizeAndTimesList
    );
    compareTest(context);
  }

}
