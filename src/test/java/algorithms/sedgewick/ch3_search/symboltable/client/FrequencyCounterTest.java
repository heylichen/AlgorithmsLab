package algorithms.sedgewick.ch3_search.symboltable.client;

import algorithms.sedgewick.ch3_search.symboltable.ST;

/**
 * Created by Chen Li on 2017/6/18.
 */
public abstract class FrequencyCounterTest {
  private final ST<String, Integer> st;

  public FrequencyCounterTest(ST<String, Integer> st) {
    this.st = st;
  }

  protected void testCount(String classPath, int minLength) throws Exception {
    FrequencyCounter fc = new FrequencyCounter();
    fc.setSt(st);
    fc.count(classPath, minLength);
  }
}