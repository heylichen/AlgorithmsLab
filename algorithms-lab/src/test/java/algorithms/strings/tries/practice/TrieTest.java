package algorithms.strings.tries.practice;

import java.util.Collection;

import algorithms.context.AppTestContext;
import algorithms.strings.alphabet.Alphabet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Chen Li on 2018/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestContext.class)
public class TrieTest {

  @Autowired
  private Alphabet lowercaseAlphabet;

  @Test
  public void put() {
    Trie<Integer> trie = new Trie<>();
    trie.setAlphabet(lowercaseAlphabet);
    String key = "abcd";
    Integer value = 100;
    trie.put(key, value);

    Integer v = trie.get(key);
    Assert.assertEquals("", value, v);

  }


  @Test
  public void deleteTest() {
    Trie<Integer> trie = new Trie<>();
    trie.setAlphabet(lowercaseAlphabet);
    String key = "abcd";
    trie.put(key, 100);
    trie.delete(key);
    Integer v = trie.get(key);
    Assert.assertNull(v);
  }

  @Test
  public void withPrefixTest() {
    Trie<Integer> trie = new Trie<>();
    trie.setAlphabet(lowercaseAlphabet);
    Collection<String> keys = null;
//    trie.put("a", 1);
//    trie.put("ab", 1);
//    trie.put("abc", 1);
//    trie.put("abcd", 1);
//    trie.put("abef", 1);
//    trie.put("abefg", 1);
//    trie.put("abfg", 1);
//    Collection<String> keys = trie.keysWithPrefix("a");
//    System.out.println(StringUtils.join(keys, ", "));
//    Assert.assertEquals("", 7, keys.size());
//
//    Collection<String> keys2 = trie.keysWithPrefix("ab");
//    System.out.println(StringUtils.join(keys2, ", "));

    trie.clear();
    trie.put("a", 1);
    keys = trie.keysWithPrefix("a");
    Assert.assertEquals("", 1, keys.size());

  }

  @Test
  public void keysTest() {
    Trie<Integer> trie = new Trie<>();
    trie.setAlphabet(lowercaseAlphabet);
    trie.put("a", 1);
    trie.put("ab", 1);
    trie.put("abc", 1);
    trie.put("abcd", 1);
    trie.put("abef", 1);
    trie.put("abefg", 1);
    trie.put("abfg", 1);
    Collection<String> keys = trie.keys();
    Assert.assertEquals("", 7, keys.size());
  }

  @Test
  public void longestPrefixOf() {
    Trie<Integer> trie = new Trie<>();
    trie.setAlphabet(lowercaseAlphabet);
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