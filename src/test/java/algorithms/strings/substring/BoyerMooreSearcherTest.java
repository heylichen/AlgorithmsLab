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
 * Created by Chen Li on 2018/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestContext.class)
public class BoyerMooreSearcherTest extends SubstringSearcherTest {

  @Autowired
  private Alphabet lowercaseAlphabet;

  @Override
  protected SubstringSearcher createInstance() {
    return new BoyerMooreSearcher(lowercaseAlphabet);
  }

  @Test
  public void goodCharacterHeuristicTest() {
    StringBuilder sb = new StringBuilder();
    sb.append("abaababacabab");

    String pattern = "cabab";
    SubstringSearcher searcher = new BoyerMooreSearcher(lowercaseAlphabet);
    searcher.compile(pattern);
    int position = searcher.search(sb.toString());
    Assert.assertEquals(8, position);
  }
}