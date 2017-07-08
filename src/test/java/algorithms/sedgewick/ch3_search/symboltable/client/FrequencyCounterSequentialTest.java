package algorithms.sedgewick.ch3_search.symboltable.client;

import algorithms.sedgewick.ch3_search.symboltable.SequentialSearchST;
import org.junit.Test;

/**
 * Created by Chen Li on 2017/6/18.
 */
public class FrequencyCounterSequentialTest extends FrequencyCounterTest {


  public FrequencyCounterSequentialTest() {
    super(new SequentialSearchST<>());
  }

  @Test
  public void testCount() throws Exception {
    super.testCount("algorithms/sedgewick/ch3_search/symboltable/client/tale.txt", 2);
  }
}