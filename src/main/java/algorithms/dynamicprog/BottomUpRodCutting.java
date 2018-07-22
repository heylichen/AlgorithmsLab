package algorithms.dynamicprog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Li on 2018/7/21.
 */
public class BottomUpRodCutting implements RodCutting {

  @Override
  public BestRodCutting findBestCuts(List<Double> prices) {
    int size = prices.size();
    int actualSize = size - 1;
    List<BestRodCutting> bestCuttings = new ArrayList<>(size);
    bestCuttings.add(BestRodCutting.zero());

    for (int i = 1; i <= actualSize; i++) {
      BestRodCutting copy = findBestCuttingOfSize(prices, bestCuttings, i);
      bestCuttings.add(copy);
    }
    return bestCuttings.get(actualSize);
  }

  private BestRodCutting findBestCuttingOfSize(List<Double> prices, List<BestRodCutting> bestCuttings, int size) {
    BestRodCutting bestCuttingRemainder = null;
    Integer bestPrefix = null;
    double maxPrice = 0d;
    double currentCutPrice = 0d;

    for (int j = 1; j <= size; j++) {
      BestRodCutting currentRemainder = bestCuttings.get(size - j);
      currentCutPrice = prices.get(j) + currentRemainder.getTotalPrice();
      if (currentCutPrice > maxPrice) {
        maxPrice = currentCutPrice;
        bestCuttingRemainder = currentRemainder;
        bestPrefix = j;
      }
    }
    BestRodCutting copy = bestCuttingRemainder.copy();
    copy.setTotalPrice(maxPrice);
    copy.getCuts().add(bestPrefix);
    return copy;
  }

}