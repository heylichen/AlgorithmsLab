package algorithms.sedgewick.ch3_search.symboltable.client;

import algorithms.sedgewick.ch3_search.symboltable.BinarySearchST;
import org.junit.Test;

/**
 * Created by Chen Li on 2017/6/18.
 */
public class FrequencyCounterBinarySearchTest extends FrequencyCounterTest {


  public FrequencyCounterBinarySearchTest() {
    super(new BinarySearchST<String, Integer>());
  }

  @Test
  public void testCount() throws Exception {
    super.testCount("algorithms/sedgewick/ch3_search/symboltable/client/tale.txt", 2);
  }
}