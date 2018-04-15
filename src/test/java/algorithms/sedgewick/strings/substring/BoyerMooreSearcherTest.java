package algorithms.sedgewick.strings.substring;

import algorithms.sedgewick.strings.Lowercase;

/**
 * Created by Chen Li on 2018/4/15.
 */
public class BoyerMooreSearcherTest extends SubstringSearcherTest {

  @Override
  protected SubstringSearcher createInstance() {
    return new BoyerMooreSearcher(new Lowercase());
  }

}