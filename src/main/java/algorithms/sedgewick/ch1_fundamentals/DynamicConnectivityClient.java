package algorithms.sedgewick.ch1_fundamentals;

import java.util.List;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Chen Li on 2018/4/26.
 */
@Slf4j
public class DynamicConnectivityClient {

  public void solve(UnionFind unionFind, List<Pair<Integer, Integer>> pairs) {
    for (Pair<Integer, Integer> pair : pairs) {
      Integer p = pair.getKey();
      Integer q = pair.getValue();
      solve(unionFind, p, q);
    }
  }

  public  void solve(UnionFind unionFind, int p, int q) {
    if (!unionFind.connected(p, q)) {
      unionFind.union(p, q);
      log.info("{} {}", p, q);
    }
  }
}
