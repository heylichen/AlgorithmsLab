package algorithms.sedgewick.strings.substring;

import algorithms.sedgewick.strings.Lowercase;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/4/14.
 */
public class KMPSearcherTest extends SubstringSearcherTest {

  @Override
  protected SubstringSearcher createInstance() {
    return new KMPSearcher(new Lowercase());
  }

  @Test
  public void constructDFATest() {
    KMPSearcher kmp = new KMPSearcher(new Lowercase());
    int[][] dfa = kmp.constructDFA("ababac");
    for (int[] rows : dfa) {
      for (int columns : rows) {
        System.out.print(columns + " ");
      }
      System.out.println("\n");
    }
  }
}