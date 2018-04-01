package algorithms.sedgewick.sorting.compare;

import algorithms.sedgewick.sorting.merge.CopyOnceMergeSort;
import algorithms.sedgewick.strings.sorts.MSDSort;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Chen Li on 2018/4/1.
 */
public class StringSortCompareTest extends AbstractSortCompareTest {

  @Autowired
  private SortCompareContext platesSortCompareContext;

  @Test
  public void mergeVsMsd() {
    SortCompareContext context = platesSortCompareContext.load(new CopyOnceMergeSort(), new MSDSort(),
                                                               SortSizeConfigs.largeSizeConfig
    );
    compareTest(context);
  }
}
