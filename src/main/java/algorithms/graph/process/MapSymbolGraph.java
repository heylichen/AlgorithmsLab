package algorithms.graph.process;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algorithms.graph.Graph;
import algorithms.utils.FileLinesIterable;
import com.google.common.base.Splitter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Chen Li on 2018/5/5.
 */
@Slf4j
public class MapSymbolGraph implements SymbolGraph {

  private Map<String, Integer> keyNameMap;
  private String[] keys;
  @Getter
  @Setter
  private Graph graph;
  @Getter
  @Setter
  private String filePath;
  @Getter
  @Setter
  private String delimiter;

  public void init() {
    keyNameMap = new HashMap<>();
    Splitter splitter = Splitter.on(delimiter).omitEmptyStrings();
    try (FileLinesIterable linesBatchIterable =
             new FileLinesIterable(filePath, 1024 * 1024 * 4)) {
      for (String line : linesBatchIterable) {
        List<String> keyList = splitter.splitToList(line);
        for (String key : keyList) {
          if (!keyNameMap.containsKey(key)) {
            int index = keyNameMap.size();
            keyNameMap.put(key, index);
          }
        }
      }
      //
    } catch (IOException ex) {
      log.error("error init ", ex);
      return;
    }
    keys = new String[keyNameMap.size()];
    for (Map.Entry<String, Integer> entry : keyNameMap.entrySet()) {
      String key = entry.getKey();
      int v = entry.getValue();
      keys[v] = key;
    }

    graph.init(keyNameMap.size());
    try (FileLinesIterable linesBatchIterable =
             new FileLinesIterable(filePath, 1024 * 1024 * 4)) {
      for (String line : linesBatchIterable) {
        List<String> keyList = splitter.splitToList(line);
        String first = keyList.get(0);
        int firstV = keyNameMap.get(first);
        for (String key : keyList.subList(1, keyList.size())) {
          graph.addEdge(firstV, keyNameMap.get(key));
        }
      }
    } catch (IOException ex) {
      log.error("error init ", ex);
      return;
    }
  }

  @Override
  public boolean contains(String key) {
    return keyNameMap.containsKey(key);
  }

  @Override
  public int index(String key) {
    Integer index = keyNameMap.get(key);
    if (index == null) {
      return -1;
    }
    return index;
  }

  @Override
  public String name(int v) {
    return keys[v];
  }
}
