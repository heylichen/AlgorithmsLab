package algorithms.dynamicprog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Li on 2018/7/21.
 */
public class TopDownRodCutting implements RodCutting {

  @Override
  public BestRodCutting findBestCuts(List<Double> prices) {
    List<BestRodCutting> bestCuttings = new ArrayList<>(prices.size());
    for (int i = 0; i < prices.size(); i++) {
      bestCuttings.add(null);
    }
    return doFindCuts(prices.size() - 1, prices, bestCuttings);
  }

  private BestRodCutting doFindCuts(int n, List<Double> prices, List<BestRodCutting> bestCuttings) {
    if (n == 0) {
      return BestRodCutting.zero();
    }
    if (n == 1) {
      return BestRodCutting.noCut(prices.get(1));
    }
    if (bestCuttings.get(n) != null) {
      return bestCuttings.get(n);
    }

    double max = -1;
    double current;
    BestRodCutting remainderCut = null;
    Integer currentPrefix = null;
    for (int i = 1; i <= n; i++) {
      BestRodCutting bestCuttingRemainder = doFindCuts(n - i, prices, bestCuttings);
      current = prices.get(i) + bestCuttingRemainder.getTotalPrice();
      if (current > max) {
        max = current;
        remainderCut = bestCuttingRemainder;
        currentPrefix = i;
      }
    }
    BestRodCutting copy = remainderCut.copy();
    copy.setTotalPrice(max);
    copy.getCuts().add(currentPrefix);
    bestCuttings.set(n, copy);
    return copy;
  }
}
