package algorithms.strings.substring;

import algorithms.strings.alphabet.Lowercase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/4/15.
 */
public class BoyerMooreSearcherTest extends SubstringSearcherTest {

  @Override
  protected SubstringSearcher createInstance() {
    return new BoyerMooreSearcher(new Lowercase());
  }

  @Test
  public void goodCharacterHeuristicTest() {
    StringBuilder sb = new StringBuilder();
    sb.append("abaababacabab");

    String pattern = "cabab";
    SubstringSearcher searcher = new BoyerMooreSearcher(new Lowercase());
    searcher.compile(pattern);
    int position = searcher.search(sb.toString());
    Assert.assertEquals(8, position);
  }
}