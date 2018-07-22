package algorithms.dynamicprog;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/7/21.
 */
public abstract class AbstractRodCuttingTest {

  protected abstract RodCutting getInstance();

  @Test
  public void findBestCuttingTest() {
    RodCutting cutting = getInstance();
//    List<Double> prices = Arrays.asList(0d, 1d, 5d, 8d, 9d, 10d, 17d, 17d, 20d, 24d, 30d);
    List<Double> prices = Arrays.asList(0d, 1d, 5d, 8d, 9d, 10d, 17d, 17d, 20d);
    BestRodCutting cut = cutting.findBestCuts(prices);
    Assert.assertEquals("", 22d, cut.getTotalPrice(), 0d);
    Assert.assertEquals("", 2, cut.getCuts().size());
    System.out.println("ok");
  }
}
