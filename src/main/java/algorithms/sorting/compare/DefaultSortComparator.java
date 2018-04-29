package algorithms.sorting.compare;

import java.util.List;

import algorithms.sorting.Sort;
import algorithms.sorting.compare.datagen.DataGenerator;

/**
 * Created by Chen Li on 2018/3/18.
 */
public class DefaultSortComparator<T extends Comparable<T>, C extends SortCompareContext>
    implements SortComparator<T, C> {

  @Override
  public final void compare(C context) {
    List<SortSizeConfig> configs = context.getSizeConfigs();
    for (SortSizeConfig sizeConfig : configs) {
      sortCompareBySize(sizeConfig, context);
    }
  }

  protected void sortCompareBySize(SortSizeConfig sizeConfig, C context) {
    if (sizeConfig == null) {
      return;
    }
    DataGenerator<T> dataGenerator = context.getDataGenerator();
    T data[] = dataGenerator.generate(sizeConfig.getSize());
    sortCompareBySize(sizeConfig, context.getSortA(), data, context);
    sortCompareBySize(sizeConfig, context.getSortB(), data, context);
    if (context.getSortB() != null) {
      sortCompareBySize(sizeConfig, context.getSortB(), data, context);
      sortCompareBySize(sizeConfig, context.getSortA(), data, context);
    }
  }

  protected void sortCompareBySize(SortSizeConfig sizeConfig, Sort<T> sort, T data[], C context) {
    if (sort == null) {
      return;
    }
    System.arraycopy(data, 0, data, 0, data.length);
    TimerSortRunner<T> tester = context.getSortTester();
    long elapsedMs = tester.timeTest(sort, data, sizeConfig.getTimes());
    SortProfileInfo profileInfo = SortProfileInfo.builder()
        .withSizeConfig(sizeConfig)
        .withSorterName(sort.toString())
        .withTotalElapsed(elapsedMs).build();
    context.getProfiles().addProfile(profileInfo);
  }
}
