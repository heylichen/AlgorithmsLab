package algorithms.sedgewick.ch1_fundamentals;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.In;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Chen Li on 2018/4/26.
 */
@Slf4j
public class DynamicConnectivityClientTest {

  @Test
  public void solveByQuickFind() throws Exception {
    solveDynamicConnectivity(new QuickFindImpl());
  }

  @Test
  public void solveByQuickUnion() throws Exception {
    solveDynamicConnectivity(new QuickUnionImpl());
  }

  @Test
  public void solveByWeightedQuickUnion() throws Exception {
    solveDynamicConnectivity(new WeightedQuickUnionImpl());
  }

  private void solveDynamicConnectivity(UnionFind unionFindImpl) throws Exception {
    ClassPathResource cpr = new ClassPathResource("algorithms/sedgewick/uf/largeUF.txt");
    In input = new In(cpr.getFile());
    int count = input.readInt();
    unionFindImpl.init(count);

    DynamicConnectivityClient client = new DynamicConnectivityClient();
    long start = System.currentTimeMillis();
    doTest(client, unionFindImpl, input);
    long end = System.currentTimeMillis();
    log.info("using {} ms", end - start);
  }

  private void doTest(DynamicConnectivityClient client, UnionFind unionFind, In input) {
    while (!input.isEmpty()) {
      try {
        int p = input.readInt();
        int q = input.readInt();
        client.solve(unionFind, p, q);
      } catch (NoSuchElementException e) {
        break;
      }
    }
  }
}