package algorithms.sedgewick.ch3_search.symboltable;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

/**
 * Created by Chen Li on 2017/8/19.
 */
public class BPlusTreeNodeTest {
  @Test
  public void testSplit() throws Exception {
    BPlusTreeNode<String, String> bplusTree = newFullNode(2, 10, 4);
    System.out.println(JSON.toJSONString(bplusTree));

    Triple<BPlusTreeNode<String, String>, String, BPlusTreeNode<String, String>> triple = bplusTree.splitIfOverflow();
    System.out.println(JSON.toJSONString(triple.getLeft()));
    System.out.println(JSON.toJSONString(triple.getMiddle()));
    System.out.println(JSON.toJSONString(triple.getRight()));

  }

  @Test
  public void testGuardInternalNode() throws Exception {
    BPlusTreeNode<String, String> node = new BPlusTreeNode<>(2);
    for (int i = 0; i < 1; i++) {
      node.getKeys()[i] = 13 + i + "";
      node.getValues()[i] = 13 + i + "";
    }

    BPlusTreeNode<String, String> leftChild = new BPlusTreeNode<>(2);
    setKeyValue(leftChild, 0, "9");
    setKeyValue(leftChild, 1, "11");
    leftChild.setLeaf(false);
    leftChild.setSize(2);

    BPlusTreeNode<String, String> rightChild = new BPlusTreeNode<>(2);
    setKeyValue(rightChild, 0, "16");
    rightChild.setLeaf(false);
    rightChild.setSize(1);


    node.setSize(1);
    node.setLeaf(false);
    node.getChildren()[0] = leftChild;
    node.getChildren()[1] = rightChild;

    BPlusTree.guardInternalChildNode(node, 1, rightChild);
    System.out.println("ok");
  }

  @Test
  public void testGuardInternalNode2() throws Exception {
    BPlusTreeNode<String, String> node = new BPlusTreeNode<>(2);
    for (int i = 0; i < 1; i++) {
      node.getKeys()[i] = 13 + i + "";
      node.getValues()[i] = 13 + i + "";
    }

    BPlusTreeNode<String, String> rightChild = new BPlusTreeNode<>(2);
    setKeyValue(rightChild, 0, "16");
    setKeyValue(rightChild, 1, "18");
    rightChild.setLeaf(false);
    rightChild.setSize(2);

    BPlusTreeNode<String, String> leftChild = new BPlusTreeNode<>(2);
    setKeyValue(leftChild, 0, "11");
    leftChild.setLeaf(false);
    leftChild.setSize(1);


    node.setSize(1);
    node.setLeaf(false);
    node.getChildren()[0] =leftChild ;
    node.getChildren()[1] = rightChild;

    BPlusTree.guardInternalChildNode(node, 0, leftChild);
    System.out.println("ok");
  }

  private void setKeyValue(BPlusTreeNode<String, String> node, int at, String key) {
    node.getKeys()[at] = key;
    node.getValues()[at] = key;
    return;
  }

  private BPlusTreeNode<String, String> newFullNode(int t, int keyBase, int keyCount) {
    int size = keyCount;
    BPlusTreeNode<String, String> node = new BPlusTreeNode<>(t);
    for (int i = 0; i < size; i++) {
      node.getKeys()[i] = keyBase + i + "";
      node.getValues()[i] = keyBase + i + "";
    }
    node.setSize(size);

    BPlusTreeNode<String, String>[] children = new BPlusTreeNode[2 * t + 1];
    for (int i = 0; i <= size; i++) {
      BPlusTreeNode<String, String> child = new BPlusTreeNode<>(t);
      child.getKeys()[0] = keyBase + i + "";
      children[i] = child;
      child.setSize(1);
      child.setLeaf(true);
    }
    node.setChildren(children);
    return node;
  }
}