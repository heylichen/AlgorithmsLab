package algorithms.search.symboltable.skiplist;

import algorithms.search.ST;
import algorithms.search.symboltable.SearchSTTest;
import org.junit.Test;

public class SkipListTest extends SearchSTTest {

  @Override
  public ST<String, String> createST() {
    return new LazySkipList<>(6);
  }

  @Test
  public void testViewState() {
    LazySkipList<Integer, Integer> bean = new LazySkipList<>(4);
    for (int i = 15; i >= 0; i--) {
      bean.put(i, i);
    }
    System.out.println("->"+bean.get(4));
    System.out.println();
    System.out.println(bean.toString());
  }
}
