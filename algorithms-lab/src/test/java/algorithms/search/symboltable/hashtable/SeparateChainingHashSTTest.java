package algorithms.search.symboltable.hashtable;

import algorithms.search.ST;
import algorithms.search.symboltable.SearchSTTest;

/**
 * Created by Chen Li on 2017/6/17.
 */
public class SeparateChainingHashSTTest extends SearchSTTest {
  @Override
  public ST<String, String> createST() {
    return new SeparateChainingHashST<>();
  }


}