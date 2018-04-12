package algorithms.sedgewick.strings.substring;

/**
 * Created by Chen Li on 2018/4/12.
 */
public class BruteForceSubstringSearcherTest extends SubstringSearcherTest {

  @Override
  protected SubstringSearcher createInstance() {
    return new BruteForceSubstringSearcher();
  }
}