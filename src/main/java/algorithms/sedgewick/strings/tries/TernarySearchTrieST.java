package algorithms.sedgewick.strings.tries;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/4/8.
 */
public class TernarySearchTrieST<V> {

  private Node<V> root;

  public V get(String key) {
    Node<V> node = getNode(root, key, 0);
    if (node != null) {
      return node.getValue();
    } else {
      return null;
    }
  }

  private Node<V> getNode(Node<V> node, String key, int d) {
    if (node == null || d == key.length()) {
      return null;
    }
    char c = key.charAt(d);
    int compare = c - node.getCharacter();
    if (compare > 0) {
      return getNode(node.getGreater(), key, d);
    } else if (compare < 0) {
      return getNode(node.getLess(), key, d);
    } else if (d == key.length() - 1) {
      return node;
    } else {
      return getNode(node.getEqual(), key, d + 1);
    }
  }

  public void put(String key, V value) {
    root = putNode(root, key, 0, value);
  }

  private Node<V> putNode(Node<V> node, String key, int d, V value) {
    char c = key.charAt(d);
    if (node == null) {
      node = new Node<>(c);
    }
    if (d == key.length() - 1) {
      node.setValue(value);
      return node;
    }
    int compare = c - node.getCharacter();
    if (compare > 0) {
      Node<V> child = node.getGreater();
      child = putNode(child, key, d, value);
      node.setGreater(child);
    } else if (compare < 0) {
      Node<V> child = node.getLess();
      child = putNode(child, key, d, value);
      node.setLess(child);
    } else {
      Node<V> child = node.getEqual();
      child = putNode(child, key, d + 1, value);
      node.setEqual(child);
    }
    return node;
  }

  public boolean contains(String key) {
    return get(key) != null;
  }

  @Getter
  @Setter
  private static class Node<V> {

    private V value;
    private char character;
    private Node<V> less;
    private Node<V> equal;
    private Node<V> greater;

    public Node(char character) {
      this.character = character;
    }
  }
}
