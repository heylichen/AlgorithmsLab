package algorithms.sedgewick.sorting.compare;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Chen Li on 2018/4/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/root-context.xml")
public class AbstractSortCompareTest<T extends Comparable<T>> {

  @Autowired
  private SortComparator defaultSortCompare;
  @Autowired
  private SortProfilesLogReport defaultSortProfilesReport;

  protected void compareTest(SortCompareContext<T> context) {

    getSortCompare().compare(context);

    getProfilesReport().reportProfiles(context.getProfiles());
  }

  public SortComparator getSortCompare() {
    return defaultSortCompare;
  }

  public SortProfilesLogReport getProfilesReport() {
    return defaultSortProfilesReport;
  }
}
