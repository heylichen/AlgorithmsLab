package algorithms.sorting.compare;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Chen Li on 2018/4/1.
 */
public class SortSizeConfigs {

  public static final List<SortSizeConfig> largeSizeConfig = Arrays.asList(
      new SortSizeConfig(100000, 20),
      new SortSizeConfig(200000, 10),
      new SortSizeConfig(400000, 10),
      new SortSizeConfig(800000, 5)
  );

  public static final List<SortSizeConfig> middleSizeAndTimesList = Arrays.asList(
      new SortSizeConfig(100000, 20),
      new SortSizeConfig(200000, 10),
      new SortSizeConfig(400000, 10)
  );

  public static final List<SortSizeConfig> slow1SizeAndTimesList = Arrays.asList(
      new SortSizeConfig(100000, 20),
      new SortSizeConfig(200000, 10)
  );


  public static final List<SortSizeConfig> slow2SizeAndTimesList = Arrays.asList(
      new SortSizeConfig(4000, 10),
      new SortSizeConfig(8000, 10)
  );

}
