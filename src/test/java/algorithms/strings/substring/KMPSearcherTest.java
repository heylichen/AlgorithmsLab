package algorithms.strings.substring;

import algorithms.context.AppTestContext;
import algorithms.strings.alphabet.Alphabet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Chen Li on 2018/4/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestContext.class)
public class KMPSearcherTest extends SubstringSearcherTest {

  @Autowired
  private Alphabet lowercaseAlphabet;

  @Override
  protected SubstringSearcher createInstance() {
    return new KMPSearcher(lowercaseAlphabet);
  }

  @Test
  public void constructDFATest() {
    KMPSearcher kmp = new KMPSearcher(lowercaseAlphabet);
    int[][] dfa = kmp.constructDFA("ababac");
    for (int[] rows : dfa) {
      for (int columns : rows) {
        System.out.print(columns + " ");
      }
      System.out.println("\n");
    }
  }
}