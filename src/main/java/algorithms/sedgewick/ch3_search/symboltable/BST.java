package algorithms.sedgewick.ch3_search.symboltable;

import com.alibaba.fastjson.JSON;

import java.util.Random;
import java.util.Stack;

/**
 * Created by Chen Li on 2017/6/28.
 * binary search tree implimentation
 */
public class BST<K extends Comparable<K>, V> extends AbstractOrderedST<K, V> {
  private Node<K, V> root;

  @Override
  public V get(K key) {
    Node<K, V> current = root;
    V found = null;
    while (current != null) {
      int compare = key.compareTo(current.getKey());
      if (compare == 0) {
        found = current.getValue();
        break;
      } else if (compare > 0) {
        current = current.getRight();
      } else {
        current = current.getLeft();
      }
    }
    return found;
  }

  @Override
  public void put(K key, V value) {
    if (root == null) {
      root = new Node(key, value, 1);
      return;
    }
    Stack<Node<K, V>> stack = new Stack<>();
    Node<K, V> current = root;
    Node<K, V> parent = null;
    int compare = 0;
    while (current != null) {
      stack.push(current);
      compare = key.compareTo(current.getKey());
      if (compare == 0) {
        current.setValue(value);
        return;
      } else if (compare < 0) {
        parent = current;
        current = current.getLeft();
      } else {
        parent = current;
        current = current.getRight();
      }
    }
    current = new Node(key, value, 1);
    if (compare < 0) {
      parent.setLeft(current);
    } else {
      parent.setRight(current);
    }
    //recalculate
    while (!stack.isEmpty()) {
      Node node = stack.pop();
      node.setCount(size(node.getLeft()) + size(node.getRight()) + 1);
    }
  }

  @Override
  public K min() {
    if (isEmpty()) {
      return null;
    }
    Node<K, V> current = root;
    while (current.getLeft() != null) {
      current = current.getLeft();
    }
    return current.getKey();
  }

  @Override
  public K max() {
    if (isEmpty()) {
      return null;
    }
    Node<K, V> current = root;
    while (current.getRight() != null) {
      current = current.getRight();
    }
    return current.getKey();
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
    return size(root);
  }

  protected int size(Node node) {
    if (node == null) {
      return 0;
    } else {
      return node.getCount();
    }
  }

  public Node<K, V> getRoot() {
    return root;
  }

  public static void main(String[] args) {
    Random rand = new Random();

    BST<Integer, Integer> bst = new BST<>();
    for (int i = 0; i < 400; i++) {
      Integer v = rand.nextInt(1000) + 1;
      bst.put(v, v);
    }

    System.out.println(JSON.toJSONString(bst.getRoot()));
  }
}
