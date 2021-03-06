package algorithms.search.symboltable.client;

import algorithms.fundamentals.sub3_collection.impl.VisualAccumulatorImpl;
import algorithms.search.ST;
import algorithms.search.symboltable.hashtable.SeparateChainingHashST;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Chen Li on 2017/6/18.
 */
public class FrequencyCounterSeparateChainingTest extends FrequencyCounterTest {
  private Logger logger = LoggerFactory.getLogger(FrequencyCounterSequentialTest.class);
  private VisualAccumulatorImpl visualAccumulator;
  private SeparateChainingHashST<String, Integer> st;

  @Override
  protected ST<String, Integer> createST() {
    visualAccumulator = new VisualAccumulatorImpl(26706, 20);
    visualAccumulator.setDraw(true);
    st = new SeparateChainingHashST<>(997);
     st.setVisualAccumulator(visualAccumulator);
    st.init();
    return st;
  }

  @Test
  public void testCount() throws Exception {
    super.testCount("algorithms/sedgewick/ch3_search/symboltable/client/tale.txt", 8);
    System.out.println(visualAccumulator.getN());
    System.out.println("ok");
//    Map<Integer, Integer> map = st.sizeDistribute();
//    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//      System.out.println(entry.getKey() + "\t" + entry.getValue());
//    }
  }
}