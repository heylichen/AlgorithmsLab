package algorithms.sedgewick.strings.tries;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
    map.put("sells", 2);
    map.put("sea", 3);
    map.put("shells", 4);
    map.put("by", 5);
    map.put("the", 6);
    map.put("sea", 7);
    map.put("shore", 8);
    return map;
  }

  @Test
  public void keysTest() {
    Map<String, Integer> map = genData();

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      String key = entry.getKey();
      Integer expectedV = map.get(key);
      st.put(key, expectedV);
    }
    Set<String> actualSet = new HashSet<>();
    for (String s : st.keys()) {
      actualSet.add(s);
    }
    Assert.assertEquals(map.keySet(), actualSet);
  }

  @Test
  public void keysWithPrefixTest() {
    Map<String, Integer> map = genData();

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      String key = entry.getKey();
      Integer expectedV = map.get(key);
      st.put(key, expectedV);
    }

    assertEqualKeys(st.keysWithPrefix("sh"), "she", "shells", "shore");
  }

  @Test
  public void longestPrefixOfTest() {
    Map<String, Integer> map = genData();

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      String key = entry.getKey();
      Integer expectedV = map.get(key);
      st.put(key, expectedV);
    }
    Assert.assertEquals("she", st.longestPrefixOf("she"));
    Assert.assertEquals("she", st.longestPrefixOf("shell"));
    Assert.assertEquals("shells", st.longestPrefixOf("shellshort"));
  }

  private void assertEqualKeys(Iterable<String> actualKeys, String... expectedKeys) {
    Set<String> expected = new HashSet<>();
    for (String expectedKey : expectedKeys) {
      expected.add(expectedKey);
    }

    Set<String> actual = new HashSet<>();
    for (String s : actualKeys) {
      actual.add(s);
    }
    Assert.assertEquals(expected, actual);
  }
}