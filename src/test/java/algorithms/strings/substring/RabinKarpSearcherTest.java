package algorithms.strings.substring;

import algorithms.strings.alphabet.Lowercase;
import algorithms.strings.alphabet.NumericAlphabet;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/4/17.
 */
public class RabinKarpSearcherTest extends SubstringSearcherTest {

  @Override
  protected SubstringSearcher createInstance() {
    return new RabinKarpSearcher(new Lowercase(), 1111111111111111111L);
  }

  @Test
  public void radix10Test() {
    SubstringSearcher searcher = new RabinKarpSearcher(new NumericAlphabet(), 1111111111111111111L);
    searcher.compile("26535");
    int index = searcher.search("3141592653589793");
    Assert.assertEquals(6, index);
  }
}