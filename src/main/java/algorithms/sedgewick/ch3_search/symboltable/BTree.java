package algorithms.sedgewick.ch3_search.symboltable;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2017/8/1.
 */
@Getter
@Setter
public class BTree<K extends Comparable<K>, V> {
  private BTreeNode<K, V> root;
  private int t;

  public BTree(int t) {
    this.t = t;
  }

  public void init() {
    BTreeNode<K, V> node = createNode();
    node.setLeaf(true);
    node.setSize(0);
    root = node;
  }

  private BTreeNode<K, V> createNode() {
    return new BTreeNode<>(this.t);
  }


  public void insert(K key) {
    BTreeNode<K, V> r = root;
    if (r.isFull()) {
      BTreeNode<K, V> s = createNode();
      root = s;
      s.setLeaf(false);
      s.setSize(0);
      s.getChildren()[0] = r;
      s.splitChild(0);
      s.insertNonFull(key);
    } else {
      root.insertNonFull(key);
    }
  }

  public void delete(K key) {
    if (root != null) {
      root.delete(key);
      if (root.size() == 0) {
        root = root.getChildren()[0];
      }
    }
  }

  public BTreeNode<K, V> getRoot() {
    return root;
  }
}
