package algorithms.search.symboltable.client;

import algorithms.search.ST;
import algorithms.search.symboltable.SequentialSearchST;

/**
 * Created by Chen Li on 2017/7/9.
 */
public class SequenceSTTraceClientTest extends TraceSTClientTest {
  @Override
  protected ST<String, Integer> createST() {
    return new SequentialSearchST<>();
  }
}
