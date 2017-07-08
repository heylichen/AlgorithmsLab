package algorithms.sedgewick.ch3_search.symboltable;

/**
 * Created by Chen Li on 2017/6/17.
 */
public class SequentialSearchSTTest extends SearchSTTest {
  @Override
  public ST<String, String> createST() {
    return new BinarySearchST<String, String>();
  }
}