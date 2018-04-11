package algorithms.sedgewick.strings.tries;

/**
 * Created by Chen Li on 2018/4/7.
 */
public class TrieSTTest extends AbstractStringSTTest{



  @Override
  protected StringST<Integer> createST() {
    TrieST<Integer> st = new TrieST<>();
    return st;
  }

}