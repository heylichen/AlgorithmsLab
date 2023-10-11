package algorithms.sorting.compare;

import java.util.List;

import algorithms.sorting.Sort;
import algorithms.sorting.compare.datagen.DataGenerator;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/3/18.
 */
@Getter
@Setter
public class SortCompareContext<T extends Comparable<T>> {

  private DataGenerator<T> dataGenerator;
  private TimerSortRunner<T> sortTester;
  private List<SortSizeConfig> sizeConfigs;
  private Sort<T> sortA;
  private Sort<T> sortB;
  private SortProfiles profiles = new SortProfiles();

  public SortCompareContext<T> getCopy() {
    SortCompareContext copy = new SortCompareContext();
    copy.setSortTester(this.sortTester);
    copy.setDataGenerator(this.dataGenerator);
    copy.setSizeConfigs(this.sizeConfigs);
    copy.setSortB(this.sortA);
    copy.setSortB(this.sortB);
    copy.setProfiles(this.getProfiles());
    return copy;
  }

  public SortCompareContext<T> load(Sort<T> sortA, Sort<T> sortB,
                                    DataGenerator<T> dataGenerator,
                                    List<SortSizeConfig> sizeConfigs) {
    SortCompareContext copy = getCopy();
    copy.setSortA(sortA);
    copy.setSortB(sortB);
    copy.setSizeConfigs(sizeConfigs);
    copy.setProfiles(new SortProfiles());
    copy.setDataGenerator(dataGenerator);
    return copy;
  }

  public SortCompareContext<T> load(Sort<T> sortA, Sort<T> sortB, List<SortSizeConfig> sizeConfigs) {
    SortCompareContext copy = getCopy();
    copy.setSortA(sortA);
    copy.setSortB(sortB);
    copy.setSizeConfigs(sizeConfigs);
    copy.setProfiles(new SortProfiles());
    return copy;
  }
}
