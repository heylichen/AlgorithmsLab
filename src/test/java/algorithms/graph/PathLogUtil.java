package algorithms.graph;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Chen Li on 2018/5/27.
 */
public class PathLogUtil {

  public static final String pathToString(Iterable<Integer> vertices) {
    if (vertices == null) {
      return StringUtils.EMPTY;
    }
    List<Integer> collection = new ArrayList<>();
    for (Integer vertex : vertices) {
      collection.add(vertex);
    }
    return StringUtils.join(collection, "->");
  }

  public static final String edgesPathToString(Iterable<DirectedEdge> edges) {
    Set<Integer> set = new LinkedHashSet<>();
    for (DirectedEdge edge : edges) {
      set.add(edge.from());
      set.add(edge.to());
    }
    return StringUtils.join(set, "->");
  }
}
