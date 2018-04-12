package algorithms.sedgewick.strings.substring;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/4/12.
 */
public abstract class SubstringSearcherTest {

  protected abstract SubstringSearcher createInstance();

  @Test
  public void searchHitTest() {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < 1000; i++) {
      sb.append("aab");
    }
    sb.append("aac");


    String pattern = "aac";
    SubstringSearcher searcher = createInstance();
    int position = searcher.search(pattern, sb.toString());
    Assert.assertEquals(3000, position);
  }

  @Test
  public void searchMissTest() {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < 1000; i++) {
      sb.append("aab");
    }
    sb.append("aac");


    String pattern = "aad";
    SubstringSearcher searcher = createInstance();
    int position = searcher.search(pattern, sb.toString());
    Assert.assertEquals(-1, position);
  }
}