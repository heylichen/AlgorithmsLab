package algorithms.sedgewick.strings.tries;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/4/8.
 */
public class TernarySearchTrieSTTest {

  private TernarySearchTrieST<Integer> st = new TernarySearchTrieST<>();

  @Test
  public void putGetTest() {
    Map<String, Integer> map = genData();

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      String key = entry.getKey();
      Integer expectedV = map.get(key);
      st.put(key, expectedV);
    }

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      String key = entry.getKey();
      Integer expectedV = map.get(key);
      Integer actual = st.get(key);
      Assert.assertEquals(expectedV, actual);
    }
  }

  private Map<String, Integer> genData() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("she", 1);
//    map.put("sells", 2);
//    map.put("sea", 3);
//    map.put("shells", 4);
//    map.put("by", 5);
//    map.put("the", 6);
//    map.put("sea", 7);
//    map.put("shore", 8);
    return map;
  }
}