package algorithms.strings.substring;

/**
 * Created by Chen Li on 2018/4/12.
 */
public class BruteForceSearcherTest extends SubstringSearcherTest {

  @Override
  protected SubstringSearcher createInstance() {
    return new BruteForceSearcher();
  }
}