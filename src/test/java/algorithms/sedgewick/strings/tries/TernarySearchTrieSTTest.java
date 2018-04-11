package algorithms.sedgewick.strings.tries;

/**
 * Created by Chen Li on 2018/4/8.
 */
public class TernarySearchTrieSTTest extends AbstractStringSTTest {

  @Override
  protected StringST<Integer> createST() {
    TernarySearchTrieST<Integer> st = new TernarySearchTrieST<>();
    return st;
  }
}