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
    BTreeNode<String, String> node = newNode(7,0);
    Triple<BTreeNode<String, String>, String, BTreeNode<String, String>> triple = node.splitByHalf();
    System.out.println(JSON.toJSONString(triple));
  }


  @Test
  public void testInsertNode() throws Exception {
    BTreeNode<String, String> node = newNode(7,0);
    node.insertKeyWithChildren(3, genTriple());

    System.out.println(JSON.toJSONString(node));
  }

  @Test
  public void splitChild() throws Exception {
    BTreeNode<String, String> parentNode = newNode(5,10);

    BTreeNode<String, String> node = newNode(7,0);
    parentNode.getChildren()[3] = node;

    System.out.println(JSON.toJSONString(parentNode));

    parentNode.splitChild(3);
    System.out.println(JSON.toJSONString(parentNode));
  }

  private Triple<BTreeNode<String, String>, String, BTreeNode<String, String>> genTriple() {
    BTreeNode<String, String> left = new BTreeNode<>(1);
    left.getKeys()[0] = "-1";
    BTreeNode<String, String> right = new BTreeNode<>(1);
    right.getKeys()[0] = "-2";
    return Triple.of(left, "-100", right);
  }

  private BTreeNode<String, String> newNode(int size, int keyBase) {
    BTreeNode<String, String> node = new BTreeNode<>(size);
    for (int i = 0; i < size; i++) {
      node.getKeys()[i] = keyBase+ i + "";
      node.getValues()[i] = keyBase+ i + "";
    }

    BTreeNode<String, String>[] children = new BTreeNode[size + 1];
    for (int i = 0; i <= size; i++) {
      BTreeNode<String, String> child = new BTreeNode<>(1);
      child.getKeys()[0] = keyBase+ i + "";
      children[i] = child;
    }
    node.setChildren(children);
    return node;
  }
}