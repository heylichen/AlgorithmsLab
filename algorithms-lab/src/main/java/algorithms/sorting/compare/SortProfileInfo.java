package algorithms.sorting.compare;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/3/30.
 */
@Getter
@Setter
public class SortProfileInfo {

  private String sorterName;
  private SortSizeConfig sizeConfig;
  private long totalElapsed;
  private long averageElapsed;

  private SortProfileInfo() {
  }

  private void init() {
    averageElapsed = totalElapsed / (long) sizeConfig.getTimes();
  }

  public static SortProfileInfoBuilder builder() {
    return new SortProfileInfoBuilder();
  }

  public static final class SortProfileInfoBuilder {

    private String sorterName;
    private SortSizeConfig sizeConfig;
    private long totalElapsed;

    private SortProfileInfoBuilder() {
    }

    public static SortProfileInfoBuilder aSortProfileInfo() {
      return new SortProfileInfoBuilder();
    }

    public SortProfileInfoBuilder withSorterName(String sorterName) {
      this.sorterName = sorterName;
      return this;
    }

    public SortProfileInfoBuilder withSizeConfig(SortSizeConfig sizeConfig) {
      this.sizeConfig = sizeConfig;
      return this;
    }

    public SortProfileInfoBuilder withTotalElapsed(long totalElapsed) {
      this.totalElapsed = totalElapsed;
      return this;
    }

    public SortProfileInfo build() {
      SortProfileInfo sortProfileInfo = new SortProfileInfo();
      sortProfileInfo.setSorterName(sorterName);
      sortProfileInfo.setSizeConfig(sizeConfig);
      sortProfileInfo.setTotalElapsed(totalElapsed);
      sortProfileInfo.init();
      return sortProfileInfo;
    }
  }
}
