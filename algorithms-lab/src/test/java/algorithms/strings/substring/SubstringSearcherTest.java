package algorithms.strings.substring;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/4/12.
 */
public abstract class SubstringSearcherTest {

  protected abstract SubstringSearcher createInstance();

  @Test
  public void smallSearchHitTest() {
    StringBuilder sb = new StringBuilder();
    sb.append("findinahaystackneedleina");

    String pattern = "needle";
    SubstringSearcher searcher = createInstance();
    searcher.compile(pattern);
    int position = searcher.search(sb.toString());
    Assert.assertEquals(15, position);
  }

  @Test
  public void searchHitTest() {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < 1000; i++) {
      sb.append("aab");
    }
    sb.append("aac");

    String pattern = "aac";
    SubstringSearcher searcher = createInstance();
    searcher.compile(pattern);
    int position = searcher.search(sb.toString());
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
    searcher.compile(pattern);
    int position = searcher.search(sb.toString());
    Assert.assertEquals(-1, position);
  }
}