package algorithms.strings.tries.practice200201;

import algorithms.context.AppTestContext;
import algorithms.strings.alphabet.Alphabet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Chen Li on 2018/4/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestContext.class)
public class TrieSTTest   {

  @Autowired
  private Alphabet lowercaseAlphabet;

  @Test
  public void testPutGet() {
    TrieST<Integer> trieST = newSt();
    System.out.println(trieST.get("she"));
    System.out.println(trieST.get("sell"));
    System.out.println(trieST.get("sea"));
    System.out.println(trieST.get("shells"));

    System.out.println(trieST.get("shellsa"));



  }

  @Test
  public void testKeys() {
    TrieST<Integer> trieST = newSt();
    for (String key : trieST.keys()) {
      System.out.println(key);
    }
    System.out.println();
    for (String key : trieST.keysWithPrefix("se")) {
      System.out.println(key);
    }
  }

  @Test
  public void testLongestPrefix() {
    TrieST<Integer> trieST = newSt();

    System.out.println(trieST.longestPrefixOf("abc"));
    System.out.println(trieST.longestPrefixOf("shells"));
    System.out.println(trieST.longestPrefixOf("shellsabc"));
  }

  @Test
  public void testKeysThatMatch() {
    TrieST<Integer> trieST = newSt();

    System.out.println(trieST.keysThatMatch("sh."));
    System.out.println(trieST.keysThatMatch("sh.lls"));
    System.out.println(trieST.keysThatMatch("s.."));
    System.out.println(trieST.keysThatMatch("s..a"));
  }

  @Test
  public void testDeleteKey() {
    TrieST<Integer> trieST = newSt();
    System.out.println(trieST.keys());

    trieST.delete("sell");
    System.out.println(trieST.keys());

    trieST.delete("she");
    System.out.println(trieST.keys());

    trieST.delete("sea");
    System.out.println(trieST.keys());

    trieST.delete("shells");
    System.out.println(trieST.keys());
  }

  private TrieST<Integer> newSt() {
    TrieST<Integer> trieST = new TrieST<>(lowercaseAlphabet);
    trieST.put("she", 1);
    trieST.put("sell", 2);
    trieST.put("sea", 3);
    trieST.put("shells", 4);
    return trieST;
  }
}