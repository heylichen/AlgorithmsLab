package algorithms.sedgewick.ch3_search.symboltable;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

/**
 * Created by Chen Li on 2017/8/2.
 */
public class BTreeNodeTest {


  @Test
  public void splitByHalfTest() throws Exception {
    BTreeNode<String, String> node = newFullNode(4, 0, 7);
    Triple<BTreeNode<String, String>, String, BTreeNode<String, String>> triple = node.splitByHalf();
    System.out.println(JSON.toJSONString(triple));
  }


  @Test
  public void testInsertNode() throws Exception {
    BTreeNode<String, String> node = newFullNode(4, 0, 5);
    node.insertKeyWithChildren(3, genTriple());

    System.out.println(JSON.toJSONString(node));
  }

  @Test
  public void splitChild() throws Exception {
    BTreeNode<String, String> parentNode = newFullNode(4, 10, 5);

    BTreeNode<String, String> node = newFullNode(4, 0, 7);
    parentNode.getChildren()[3] = node;

    parentNode.splitChild(3);
    System.out.println(JSON.toJSONString(parentNode));
  }

  @Test
  public void splitChildWithEmptyParent() throws Exception {
    BTreeNode<String, String> parentNode = newFullNode(4, 0, 0);
    BTreeNode<String, String> node = newFullNode(4, 0, 7);
    parentNode.getChildren()[0] = node;

    parentNode.splitChild(0);
    System.out.println(JSON.toJSONString(parentNode));
  }

  private Triple<BTreeNode<String, String>, String, BTreeNode<String, String>> genTriple() {
    BTreeNode<String, String> left = new BTreeNode<>(4);
    left.getKeys()[0] = "-1";
    left.setSize(1);
    BTreeNode<String, String> right = new BTreeNode<>(4);
    right.getKeys()[0] = "-2";
    right.setSize(1);
    return Triple.of(left, "-100", right);
  }

  private BTreeNode<String, String> newFullNode(int t, int keyBase, int nodeCount) {
    int size = nodeCount;
    BTreeNode<String, String> node = new BTreeNode<>(t);
    for (int i = 0; i < size; i++) {
      node.getKeys()[i] = keyBase + i + "";
      node.getValues()[i] = keyBase + i + "";
    }
    node.setSize(size);

    BTreeNode<String, String>[] children = new BTreeNode[2 * t];
    for (int i = 0; i <= size; i++) {
      BTreeNode<String, String> child = new BTreeNode<>(t);
      child.getKeys()[0] = keyBase + i + "";
      children[i] = child;
      child.setSize(1);
    }
    node.setChildren(children);
    return node;
  }
}