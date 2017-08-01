package algorithms.sedgewick.ch3_search.symboltable;

import org.junit.Test;

/**
 * Created by Chen Li on 2017/7/29.
 */
public class RedBlack24BSTTest {
  @Test
  public void addNodes() throws Exception {
    RedBlack24BST<Integer, Integer> bst = new RedBlack24BST<>();


    for (int i = 0; i < 10; i++) {
      Integer key = i + 1;
      bst.put(key, key);
      System.out.println(bst.toString()+",");
    }



  }
}