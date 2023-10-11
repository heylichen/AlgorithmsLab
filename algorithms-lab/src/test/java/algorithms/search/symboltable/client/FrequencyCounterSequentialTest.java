package algorithms.search.symboltable.client;

import algorithms.fundamentals.sub3_collection.impl.VisualAccumulatorImpl;
import algorithms.search.ST;
import algorithms.search.symboltable.SequentialSearchST;
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
    //processed words count and distinct words count
    visualAccumulator = new VisualAccumulatorImpl(28690, 5128);
    st.setVisualAccumulator(visualAccumulator);
    return st;
  }

  @Test
  public void testCount() throws Exception {
    super.testCount("algorithms/sedgewick/ch3_search/symboltable/client/tale.txt", 8);
    logger.info("trails :{}, avg {}", visualAccumulator.getN(), visualAccumulator.avg());
    Thread.sleep(10000);
  }
}