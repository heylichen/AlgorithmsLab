package algorithms.graph;

import java.util.List;

import algorithms.utils.FileHeadLinesBatchIterable;
import com.google.common.base.Splitter;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Chen Li on 2018/4/29.
 */
public class UndirectedGraphFactory {

  @Getter
  @Setter
  private String edgesFilePath;
  private Splitter splitter = Splitter.on(" ").omitEmptyStrings().trimResults();

  public Graph loadGraph() {
    return loadGraph(edgesFilePath);
  }

  private Graph loadGraph(String edgesFilePath) {
    FileHeadLinesBatchIterable linesBatchIterable =
        new FileHeadLinesBatchIterable(edgesFilePath, 1000, 2, 1024 * 1024 * 4);

    int verticesCount = Integer.parseInt(StringUtils.trim(linesBatchIterable.getHeadline(0)));
    Graph impl = new UndirectedGraphImpl(verticesCount);
    int edgesCount = Integer.parseInt(StringUtils.trim(linesBatchIterable.getHeadline(1)));
    for (List<String> lines : linesBatchIterable) {
      addEdges(impl, lines);
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
