package algorithms.sedgewick.ch1_fundamentals;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import algorithms.sedgewick.utils.FileLinesBatchIterable;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/4/26.
 */
@Slf4j
public class DynamicConnectivityClientTest {

  private String unFilePath = "algorithms/sedgewick/uf/tinyUF.txt";
  private int bufferBytes = 1024 * 1024 * 8;//8MB

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
    FileLinesBatchIterable linesBatchIterable = new FileLinesBatchIterable(unFilePath, 1000, bufferBytes);
    Iterator<List<String>> iterator = linesBatchIterable.iterator();
    if (!iterator.hasNext()) {
      return;
    }
    List<String> lines = iterator.next();
    if (lines.size() < 2) {
      return;
    }
    int count = Integer.valueOf(StringUtils.trim(lines.get(0)));
    unionFindImpl.init(count);
    DynamicConnectivityClient client = new DynamicConnectivityClient();

    Stopwatch stopwatch = Stopwatch.createStarted();
    solveLines(client, unionFindImpl, lines.subList(1, lines.size()));
    stopwatch.stop();
    //
    while (iterator.hasNext()) {
      lines = iterator.next();
      stopwatch.start();
      solveLines(client, unionFindImpl, lines.subList(1, lines.size()));
      stopwatch.stop();
    }
    long elapsedMs = TimeUnit.MILLISECONDS.convert(stopwatch.elapsed().toNanos(), TimeUnit.NANOSECONDS);
    log.info("using {} ms", elapsedMs);
  }

  private void solveLines(DynamicConnectivityClient client, UnionFind unionFindImpl, List<String> lines) {
    for (String line : lines) {
      line = StringUtils.trim(line);
      int emptyIndex = line.indexOf(" ");
      int lastEmptyIndex = line.lastIndexOf(" ");
      if (emptyIndex == -1) {
        throw new IllegalArgumentException("illegal no blank " + line);
      }
      int p = Integer.parseInt(line.substring(0, emptyIndex));
      int q = Integer.parseInt(line.substring(lastEmptyIndex + 1));
      client.solve(unionFindImpl, p, q);//call silent solve to disable log for dramatic performance improvement
    }
  }
}