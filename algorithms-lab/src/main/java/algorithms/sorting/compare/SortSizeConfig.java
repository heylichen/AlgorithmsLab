package algorithms.sorting.compare;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/3/18.
 */
@Getter
@Setter
public class SortSizeConfig {

  private int size;
  private int times;

  public SortSizeConfig(int size, int times) {
    this.size = size;
    this.times = times;
  }

  public SortSizeConfig() {
  }
}
