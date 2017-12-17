package algorithms.sedgewick.ch3_search.symboltable.client;

import algorithms.sedgewick.ch1_fundamentals.sub3_collection.impl.VisualAccumulatorImpl;
import algorithms.sedgewick.ch3_search.symboltable.ST;
import algorithms.sedgewick.ch3_search.symboltable.hashtable.SeparateChainingHashST;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Chen Li on 2017/6/18.
 */
public class FrequencyCounterSeparateChainingTest extends FrequencyCounterTest {
  private Logger logger = LoggerFactory.getLogger(FrequencyCounterSequentialTest.class);
  private VisualAccumulatorImpl visualAccumulator;
  private SeparateChainingHashST<String, Integer> st;

  @Override
  protected ST<String, Integer> createST() {
    st = new SeparateChainingHashST<>(1000);
    return st;
  }

  @Test
  public void testCount() throws Exception {
    super.testCount("algorithms/sedgewick/ch3_search/symboltable/client/tale.txt", 4);
    Map<Integer, Integer> map = st.sizeDistribute();
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      System.out.println(entry.getKey() + "\t" + entry.getValue());
    }
  }
}