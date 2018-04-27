package algorithms.search.symboltable;

import com.alibaba.fastjson.JSON;

import java.util.NoSuchElementException;

/**
 * Created by Chen Li on 2017/7/24.
 */
public class RedBlackBST<K extends Comparable<K>, V> extends AbstractOrderedST<K, V> {
  private RedBlackNode<K, V> root;

  public boolean isRed(RedBlackNode x) {
    if (x == null) {
      return false;
    }
    return x.getColor() == RedBlackNode.RED;
  }

  @Override
  public V get(K key) {
    RedBlackNode<K, V> found = findNode(root, key);
    return found == null ? null : found.getValue();
  }

  private RedBlackNode findNode(RedBlackNode<K, V> root, K key) {
    RedBlackNode<K, V> current = root;
    RedBlackNode<K, V> found = null;
    int compares = 0;
    while (current != null) {
      int compare = key.compareTo(current.getKey());
      compares++;
      if (compare == 0) {
        found = current;
        break;
      } else if (compare > 0) {
        current = current.getRight();
      } else {
        current = current.getLeft();
      }
    }
    addDataValue(compares);
    return found;
  }

  @Override
  public void put(K key, V value) {
    root = put(root, key, value);
    root.setColor(RedBlackNode.BLACK);
  }

  private RedBlackNode put(RedBlackNode<K, V> h, K key, V value) {
    if (h == null) {
      return new RedBlackNode(key, value, 1, RedBlackNode.RED);
    }
    int compare = key.compareTo(h.getKey());
    if (compare > 0) {
      h.setRight(put(h.getRight(), key, value));
    } else if (compare < 0) {
      h.setLeft(put(h.getLeft(), key, value));
    } else {
      h.setValue(value);
    }
    updateNodeCount(h);

    if (isRed(h.getRight()) && !isRed(h.getLeft())) {
      h = rotateLeft(h);
    }
    if (isRed(h.getLeft()) && isRed(h.getLeft().getLeft())) {
      h = rotateRight(h);
    }
    if (isRed(h.getRight()) && isRed(h.getLeft())) {
      flipColors(h);
    }
    return h;
  }

  /***************************************************************************
   *  Red-black tree deletion.
   ***************************************************************************/

  /**
   * Removes the smallest key and associated value from the symbol table.
   *
   * @throws NoSuchElementException if the symbol table is empty
   */
  public void deleteMin() {
    if (isEmpty()) throw new NoSuchElementException("BST underflow");

    // if both children of root are black, set root to red
    if (!isRed(root.getLeft()) && !isRed(root.getRight()))
      root.setColor(RedBlackNode.RED);

    root = deleteMin(root);
    if (!isEmpty()) root.setColor(RedBlackNode.BLACK);
    // assert check();
  }

  // delete the key-value pair with the minimum key rooted at h
  private RedBlackNode deleteMin(RedBlackNode h) {
    if (h.getLeft() == null)
      return null;

    if (!isRed(h.getLeft()) && !isRed(h.getLeft().getLeft()))
      h = moveRedLeft(h);

    h.setLeft(deleteMin(h.getLeft()));
    return balance(h);
  }

  // Assuming that h is red and both h.left and h.left.left
  // are black, make h.left or one of its children red.
  private RedBlackNode moveRedLeft(RedBlackNode h) {
    // assert (h != null);
    // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

    flipColors(h);
    if (isRed(h.getRight().getLeft())) {
      h.setRight(rotateRight(h.getRight()));
      h = rotateLeft(h);
      flipColors(h);
    }
    return h;
  }

  // Assuming that h is red and both h.right and h.right.left
  // are black, make h.right or one of its children red.
  private RedBlackNode moveRedRight(RedBlackNode h) {
    // assert (h != null);
    // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
    flipColors(h);
    if (isRed(h.getLeft().getLeft())) {
      h = rotateRight(h);
      flipColors(h);
    }
    return h;
  }

  // restore red-black tree invariant
  private RedBlackNode balance(RedBlackNode h) {
    // assert (h != null);

    if (isRed(h.getRight())) h = rotateLeft(h);
    if (isRed(h.getLeft()) && isRed(h.getLeft().getLeft())) h = rotateRight(h);
    if (isRed(h.getLeft()) && isRed(h.getRight())) flipColors(h);

    updateNodeCount(h);
    return h;
  }

  @Override
  public String toString() {
    return JSON.toJSONString(root);
  }

  @Override
  public K min() {
    return null;
  }

  @Override
  public K max() {
    return null;
  }

  @Override
  public K floor(K key) {
    return null;
  }

  @Override
  public K ceiling(K key) {
    return null;
  }

  @Override
  public int rank(K key) {
    return 0;
  }

  @Override
  public K select(int rank) {
    return null;
  }

  @Override
  public Iterable<K> keys(K low, K high) {
    return null;
  }

  @Override
  public void delete(K key) {

  }

  @Override
  public int size() {
    return 0;
  }

  private RedBlackNode rotateLeft(RedBlackNode h) {
    RedBlackNode rightNode = h.getRight();
    h.setRight(rightNode.getLeft());
    rightNode.setLeft(h);
    rightNode.setColor(h.getColor());
    h.setColor(RedBlackNode.RED);
    //count
    rightNode.setCount(h.getCount());
    updateNodeCount(h);
    return rightNode;
  }

  private void flipColors(RedBlackNode h) {
    h.getLeft().setColor(RedBlackNode.BLACK);
    h.getRight().setColor(RedBlackNode.BLACK);
    h.setColor(RedBlackNode.RED);
  }

  private RedBlackNode rotateRight(RedBlackNode h) {
    RedBlackNode x = h.getLeft();
    h.setLeft(x.getRight());
    x.setRight(h);
    x.setColor(h.getColor());
    h.setColor(RedBlackNode.RED);
    //count
    x.setCount(h.getCount());
    updateNodeCount(h);
    return x;
  }

  private void updateNodeCount(RedBlackNode current) {
    if (current != null) {
      current.setCount(1 + size(current.getLeft()) + size(current.getRight()));
    }
  }

  private int size(RedBlackNode node) {
    return node == null ? 0 : node.getCount();
  }

}