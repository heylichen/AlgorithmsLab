package algorithms.search.symboltable;

/**
 * Created by Chen Li on 2017/6/21.
 */
public class BinarySearchSTTest extends OrderedSTTest {
  @Override
  public OrderedST<String, String> createOrderedST() {
    return new BinarySearchST<String, String>();
  }
}