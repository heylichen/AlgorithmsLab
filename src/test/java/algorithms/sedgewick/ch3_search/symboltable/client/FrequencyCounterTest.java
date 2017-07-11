package algorithms.sedgewick.ch3_search.symboltable.client;

import algorithms.sedgewick.ch3_search.symboltable.ST;

/**
 * Created by Chen Li on 2017/6/18.
 */
public abstract class FrequencyCounterTest {


  protected void testCount(String classPath, int minLength) throws Exception {
    ST<String, Integer> st = createST();
    FrequencyCounter fc = new FrequencyCounter();
    fc.setSt(st);
    fc.count(classPath, minLength);
  }

  protected abstract ST<String, Integer> createST();
}