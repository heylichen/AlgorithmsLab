package algorithms.search.symboltable.hashtable;

import algorithms.sedgewick.ch3_search.symboltable.ST;
import algorithms.sedgewick.search.symboltable.SearchSTTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Chen Li on 2017/12/17.
 */
public class LinearProbeHashSTTest extends SearchSTTest {
  @Override
  public ST<String, String> createST() {
    return new LinearProbingHashST<>();
  }

  public void resizeWhenPutTest() throws Exception {
    LinearProbingHashST<String, String> st = new LinearProbingHashST<>(19);
    for (int i = 1; i <= 100; i++) {
      String key = i + "";
      st.put(key, key);
    }
  }

  @Test
  public void testDeleteAndGet() throws Exception {
    LinearProbingHashST<String, String> st = new LinearProbingHashST<>(20);
    for (int i = 1; i <= 10; i++) {
      st.put(i + "", i + "");
    }
    st.delete("5");

    String value = st.get("7");
    assertEquals("value wrong!", "7", value);
  }
}
