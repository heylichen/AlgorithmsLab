package algorithms.graph;

import java.util.Iterator;
import java.util.List;

import algorithms.utils.FileLinesBatchIterable;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Chen Li on 2018/4/29.
 */
public class UndirectedGraphFactory {

  private Splitter splitter = Splitter.on(" ").omitEmptyStrings().trimResults();

  public Graph loadEdges(String edgesFilePath) {

    FileLinesBatchIterable linesBatchIterable =
        new FileLinesBatchIterable(edgesFilePath, 1000, 1024 * 1024 * 4);
    Iterator<List<String>> iterator = linesBatchIterable.iterator();
    if (!iterator.hasNext()) {
      throw new IllegalArgumentException("config illegal");
    }
    List<String> lines = iterator.next();
    if (lines.size() < 3) {
      throw new IllegalArgumentException("config illegal");
    }
    int verticesCount = Integer.parseInt(StringUtils.trim(lines.get(0)));
    Graph impl = new UndirectedGraphImpl(verticesCount);
    int edgesCount = Integer.parseInt(StringUtils.trim(lines.get(1)));
    addEdges(impl, lines.subList(2, lines.size()));
    while (iterator.hasNext()) {
      addEdges(impl, iterator.next());
    }
    return impl;
  }

  private void addEdges(Graph impl, List<String> lines) {
    for (String line : lines) {
      List<String> pair = splitter.splitToList(line);
      int p = Integer.parseInt(pair.get(0));
      int q = Integer.parseInt(pair.get(1));
      impl.addEdge(p, q);
    }
  }
}
