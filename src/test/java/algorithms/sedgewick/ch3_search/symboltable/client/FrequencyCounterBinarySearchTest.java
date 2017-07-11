package algorithms.sedgewick.ch3_search.symboltable.client;

import algorithms.sedgewick.ch1_fundamentals.sub3_collection.impl.VisualAccumulator;
import algorithms.sedgewick.ch1_fundamentals.sub3_collection.impl.VisualAccumulatorImpl;
import algorithms.sedgewick.ch3_search.symboltable.BinarySearchST;
import algorithms.sedgewick.ch3_search.symboltable.ST;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

/**
 * Created by Chen Li on 2017/6/18.
 */
public class FrequencyCounterBinarySearchTest extends FrequencyCounterTest {


  private VisualAccumulator visualAccumulator;

  @Override
  protected ST<String, Integer> createST() {
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    visualAccumulator = new VisualAccumulatorImpl(14350, 5800);
    st.setVisualAccumulator(visualAccumulator);
    return st;
  }

  @Test
  public void testCount() throws Exception {
    super.testCount("algorithms/sedgewick/ch3_search/symboltable/client/tale.txt", 8);
    StdOut.println(visualAccumulator);
  }
}