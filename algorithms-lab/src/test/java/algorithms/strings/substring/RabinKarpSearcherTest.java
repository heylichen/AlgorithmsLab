package algorithms.strings.substring;

import algorithms.context.AppTestContext;
import algorithms.strings.alphabet.Alphabet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Chen Li on 2018/4/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestContext.class)
public class RabinKarpSearcherTest extends SubstringSearcherTest {

  @Autowired
  private Alphabet numericAlphabet;

  @Autowired
  private Alphabet lowercaseAlphabet;

  @Override
  protected SubstringSearcher createInstance() {
    return new RabinKarpSearcher(lowercaseAlphabet, 1111111111111111111L);
  }

  @Test
  public void radix10Test() {
    SubstringSearcher searcher = new RabinKarpSearcher(numericAlphabet, 1111111111111111111L);
    searcher.compile("26535");
    int index = searcher.search("3141592653589793");
    Assert.assertEquals(6, index);
  }
}