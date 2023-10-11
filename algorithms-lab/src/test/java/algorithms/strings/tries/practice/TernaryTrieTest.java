package algorithms.strings.tries.practice;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/6/17.
 */
public class TernaryTrieTest {

  @Test
  public void put() {
    TernaryTrie<Integer> trie = new TernaryTrie<>();
    String key = "abcd";
    Integer value = 100;
    trie.put(key, value);

    Integer v = trie.get(key);
    Assert.assertEquals("", value, v);
  }

  @Test
  public void deleteTest() {
    TernaryTrie<Integer> trie = new TernaryTrie<>();
    List<String> keys = Arrays.asList("sea", "abcd", "tea", "seaa", "seab", "seac", "seabde");
    for (String key : keys) {
      trie.put(key, 1);
    }
    Assert.assertEquals("", keys.size(), trie.size());
    for (String key : keys) {
      Assert.assertEquals("", 1, trie.get(key).intValue());
    }
    int count = keys.size();
    for (String key : keys) {
      trie.delete(key);
      count--;
      Assert.assertEquals("", count, trie.size());
    }
    Assert.assertTrue(trie.isEmpty());
  }

  @Test
  public void withPrefixTest() {
    TernaryTrie<Integer> trie = new TernaryTrie<>();
    Set<String> expectedKeysPrefixA = Sets.newHashSet("a", "ab", "abc", "abcd", "abef", "abefg", "abfg");
    Collection<String> keys = null;
    for (String key : expectedKeysPrefixA) {
      trie.put(key, 1);
    }
    keys = trie.keysWithPrefix("a");
    System.out.println(StringUtils.join(keys, ", "));
    Assert.assertEquals("", expectedKeysPrefixA, new HashSet<>(keys));

    Collection<String> keys2 = trie.keysWithPrefix("ab");
    System.out.println(StringUtils.join(keys2, ", "));

    trie.clear();
    trie.put("a", 1);
    keys = trie.keysWithPrefix("a");
    Assert.assertEquals("", 1, keys.size());

  }

  @Test
  public void longestPrefixOf() {
    TernaryTrie<Integer> trie = new TernaryTrie<>();
    trie.put("a", 1);
    trie.put("ab", 1);
    trie.put("abc", 1);
    trie.put("abcd", 1);
    trie.put("abef", 1);
    trie.put("abefg", 1);
    trie.put("abfg", 1);

    System.out.println(trie.longestPrefixOf("abefgh"));
    System.out.println(trie.longestPrefixOf("cd"));
  }
}