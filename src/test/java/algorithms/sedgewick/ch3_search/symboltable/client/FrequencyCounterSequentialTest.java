package algorithms.sedgewick.ch3_search.symboltable.client;

import algorithms.sedgewick.ch1_fundamentals.sub3_collection.impl.VisualAccumulatorImpl;
import algorithms.sedgewick.ch3_search.symboltable.ST;
import algorithms.sedgewick.ch3_search.symboltable.SequentialSearchST;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Chen Li on 2017/6/18.
 */
public class FrequencyCounterSequentialTest extends FrequencyCounterTest {
  private Logger logger = LoggerFactory.getLogger(FrequencyCounterSequentialTest.class);
  private VisualAccumulatorImpl visualAccumulator;

  @Override
  protected ST<String, Integer> createST() {
    SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
    visualAccumulator = new VisualAccumulatorImpl(38946, 5128);
    st.setVisualAccumulator(visualAccumulator);
    return st;
  }

  @Test
  public void testCount() throws Exception {
    super.testCount("algorithms/sedgewick/ch3_search/symboltable/client/tale.txt", 8);
    logger.info("trails :{}, avg {}", visualAccumulator.getN(), visualAccumulator.avg());
    // StdOut.println(visualAccumulator);
  }
}