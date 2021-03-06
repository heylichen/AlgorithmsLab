package algorithms.graph;

import java.io.IOException;
import java.util.List;

import algorithms.utils.FileHeadLinesBatchIterable;
import com.google.common.base.Splitter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Chen Li on 2018/5/11.
 */
@Slf4j
public class EdgeWeightedGraphFactory {

  @Getter
  @Setter
  private Splitter splitter = Splitter.on(" ").omitEmptyStrings().trimResults();

  public <T extends EdgeWeightedGraph> T loadGraph(T impl, String edgesFilePath) {
    try (FileHeadLinesBatchIterable linesBatchIterable =
             new FileHeadLinesBatchIterable(edgesFilePath, 1000, 2, 1024 * 1024 * 4)) {
      int verticesCount = Integer.parseInt(StringUtils.trim(linesBatchIterable.getHeadline(0)));
      impl.init(verticesCount);
      int edgesCount = Integer.parseInt(StringUtils.trim(linesBatchIterable.getHeadline(1)));
      for (List<String> lines : linesBatchIterable) {
        addEdges(impl, lines);
      }
      return impl;
    } catch (IOException e) {
      log.error("error reading file", e);
      return null;
    }
  }

  private void addEdges(EdgeWeightedGraph impl, List<String> lines) {
    for (String line : lines) {
      List<String> pair = splitter.splitToList(line);
      int p = Integer.parseInt(pair.get(0));
      int q = Integer.parseInt(pair.get(1));
      double weight = Double.parseDouble(pair.get(2));
      impl.addEdge(new EdgeImpl(p, q, weight));
    }
  }
}
