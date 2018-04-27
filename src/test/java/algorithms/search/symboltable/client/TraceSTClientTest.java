package algorithms.search.symboltable.client;

import algorithms.sedgewick.ch3_search.symboltable.ST;
import org.junit.Test;

/**
 * Created by Chen Li on 2017/7/9.
 */
public abstract class TraceSTClientTest {
  protected abstract ST<String, Integer> createST();

  @Test
  public void testSt() throws Exception {
    TraceSTClient client = new TraceSTClient();
    client.setSt(createST());
    client.generateTableAndPrint(10);
  }
}