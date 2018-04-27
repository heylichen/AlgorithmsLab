package algorithms.search.symboltable.client;

import algorithms.fundamentals.sub3_collection.impl.VisualAccumulatorImpl;
import algorithms.sedgewick.search.symboltable.BinarySearchST;
import algorithms.sedgewick.ch3_search.symboltable.ST;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Chen Li on 2017/6/18.
 */
public class FrequencyCounterBinarySearchTest extends FrequencyCounterTest {
  private Logger logger = LoggerFactory.getLogger(FrequencyCounterSequentialTest.class);
  private VisualAccumulatorImpl visualAccumulator;

  @Override
  protected ST<String, Integer> createST() {
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    //visualAccumulator = new VisualAccumulatorImpl(28690, 600);
    st.setVisualAccumulator(visualAccumulator);
    return st;
  }

  @Test
  public void testCount() throws Exception {
    super.testCount("algorithms/sedgewick/ch3_search/symboltable/client/tale.txt", 4);
   // logger.info("trails :{}, avg {}", visualAccumulator.getN(), visualAccumulator.avg());
   // Thread.sleep(10000);
  }
}