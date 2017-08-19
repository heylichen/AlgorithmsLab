package algorithms.sedgewick.ch3_search.symboltable;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Chen Li on 2017/8/6.
 */
public class BTreeTest {
  @Test
  public void addOneNode() throws Exception {
    BTree<Integer, Integer> btree = new BTree<>(4);
    btree.init();

    btree.insert(0);
    System.out.println(JSON.toJSONString(btree.getRoot()));
  }

  @Test
  public void addToRootFull() throws Exception {
    BTree<Integer, Integer> btree = new BTree<>(4);
    btree.init();

    for (int i = 0; i < 7; i++) {
      btree.insert(i);
    }
    System.out.println(JSON.toJSONString(btree.getRoot()));
  }

  @Test
  public void addToRootFullAndOneMore() throws Exception {
    BTree<Integer, Integer> btree = new BTree<>(4);
    btree.init();

    for (int i = 0; i < 7; i++) {
      btree.insert(i);
    }

    assertTrue("root node should be full!", btree.getRoot().isFull());

    btree.insert(7);
    System.out.println(JSON.toJSONString(btree.getRoot()));
  }

  @Test
  public void addToRootFull2() throws Exception {
    BTree<Integer, Integer> btree = new BTree<>(4);
    btree.init();

    for (int i = 0; i < 11; i++) {
      btree.insert(i);
    }
    System.out.println(JSON.toJSONString(btree.getRoot()));
    btree.insert(11);
    System.out.println(JSON.toJSONString(btree.getRoot()));
  }

  @Test
  public void addToRootFull200() throws Exception {
    BTree<Integer, Integer> btree = new BTree<>(4);
    btree.init();

    for (int i = 0; i < 20; i++) {
      btree.insert(i);
    }
    System.out.println(JSON.toJSONString(btree.getRoot()));

  }

  @Test
  public void deleteTest() throws Exception {
    BTree<Integer, Integer> btree = new BTree<>(2);
    btree.init();

    for (int i = 0; i < 20; i++) {
      btree.insert(i);
    }
    //System.out.println(JSON.toJSONString(btree.getRoot()));
    btree.delete(19);
    System.out.println();
    System.out.println(JSON.toJSONString(btree.getRoot()));
  }

  @Test
  public void deleteInternalNodeTest() throws Exception {
    BTree<Integer, Integer> btree = new BTree<>(2);
    btree.init();

    for (int i = 0; i < 20; i++) {
      btree.insert(i);
    }
    //System.out.println(JSON.toJSONString(btree.getRoot()));
    btree.delete(11);
    System.out.println();
    System.out.println(JSON.toJSONString(btree.getRoot()));
  }
}