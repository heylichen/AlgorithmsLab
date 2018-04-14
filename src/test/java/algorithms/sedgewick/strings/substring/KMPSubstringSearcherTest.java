package algorithms.sedgewick.strings.substring;

import org.junit.Test;

/**
 * Created by Chen Li on 2018/4/14.
 */
public class KMPSubstringSearcherTest extends SubstringSearcherTest {

  @Override
  protected SubstringSearcher createInstance() {
    return new KMPSubstringSearcher();
  }

  @Test
  public void constructDFATest() {
    KMPSubstringSearcher kmp = new KMPSubstringSearcher();
    int[][] dfa = kmp.constructDFA("ababac");
    for (int[] rows : dfa) {
      for (int columns : rows) {
        System.out.print(columns+ " ");
      }
      System.out.println("\n");
    }
  }
}