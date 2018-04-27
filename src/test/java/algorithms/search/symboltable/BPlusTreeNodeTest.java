package algorithms.search.symboltable;

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