package algorithms.strings.tries;

import java.util.LinkedList;
import java.util.Queue;

import algorithms.sedgewick.strings.Alphabet;
import algorithms.sedgewick.strings.Lowercase;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/4/7.
 */
@Getter
@Setter
public class TrieST<V> implements StringST<V> {

  private Node<V> root;
  private int size = 0;
  private Alphabet alphabet = new Lowercase();

  @Override
  public V get(String key) {
    Node<V> node = getNode(root, key, 0);
    if (node != null) {
      return node.getValue();
    } else {
      return null;
    }
  }

  private Node<V> getNode(Node<V> node, String key, int d) {
    if (node == null) {
      return null;
    }
    if (d == key.length()) {
      return node;
    }
    int index = toIndex(key, d);
    return getNode(node.next(index), key, d + 1);
  }

  @Override
  public void put(String key, V value) {
    root = putNode(root, key, 0, value);
  }

  private Node<V> putNode(Node<V> node, String key, int d, V value) {
    boolean add = false;
    if (node == null) {
      node = new Node<>(getRadix());
      add = true;
    }
    if (d == key.length()) {
      if (add) {
        size++;
      }
      node.setValue(value);
      return node;
    }
    int index = toIndex(key, d);
    Node<V> child = putNode(node.next(index), key, d + 1, value);
    node.setNext(index, child);
    return node;
  }

  private int getRadix() {
    return alphabet.radix();
  }

  @Override
  public boolean contains(String key) {
    return get(key) != null;
  }

  @Override
  public void delete(String key) {
    root = doDelete(root, key, 0);
  }

  private Node<V> doDelete(Node<V> node, String key, int d) {
    if (node == null) {
      return null;
    }
    if (d == key.length()) {
      if (node.getValue() != null) {
        size--;
      }
      node.setValue(null);
    } else {
      int index = toIndex(key, d);
      Node<V> child = node.next(index);
      child = doDelete(child, key, d + 1);
      node.setNext(index, child);
    }
    return checkNode(node);
  }

  private Node<V> checkNode(Node node) {
    if (node.getValue() != null) {
      return node;
    }
    if (node.next == null || node.next.length == 0) {
      return null;
    }

    for (Node node1 : node.next) {
      if (node1 != null) {
        return node;
      }
    }
    return null;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterable<String> keys() {
    return keysWithPrefix("");
  }

  @Override
  public String longestPrefixOf(String s) {
    int index = search(root, s, 0, 0);
    return s.substring(0, index);
  }

  private int search(Node<V> node, String key, int d, int length) {
    if (node == null) {
      return length;
    }
    if (node.getValue() != null) {
      length = d;
    }
    if (key.length() == d) {
      return length;
    }
    int index = toIndex(key, d);
    return search(node.next(index), key, d + 1, length);
  }

  @Override
  public Iterable<String> keysWithPrefix(String s) {
    Queue<String> q = new LinkedList<>();
    collect(getNode(root, s, 0), s, q);
    return q;
  }

  private void collect(Node<V> node, String pre, Queue<String> queue) {
    if (node == null) {
      return;
    }
    if (node.getValue() != null) {
      queue.add(pre);
    }
    for (int i = 0; i < node.getNext().length; i++) {
      collect(node.next(i), pre + toChar((char) i), queue);
    }
  }

  @Override
  public Iterable<String> keysThatMatch(String pattern) {
    Queue<String> q = new LinkedList<>();
    collect(root, "", pattern, q);
    return q;
  }

  private void collect(Node<V> node, String pre, String pattern, Queue<String> queue) {
    if (node == null) {
      return;
    }
    int d = pre.length();
    if (d == pattern.length()) {
      if (node.getValue() != null) {
        queue.add(pre);
      }
      return;
    }
    char nextPatternChar = pattern.charAt(d);

    for (int i = 0; i < node.getNext().length; i++) {
      char nextChar = toChar(i);
      if (nextChar == nextPatternChar || nextPatternChar == '.') {
        collect(node.next(i), pre + nextChar, pattern, queue);
      }
    }
  }

  private int toIndex(String s, int i) {
    return alphabet.toIndex(s.charAt(i));
  }

  private char toChar(int i) {
    return alphabet.toChar(i);
  }

  @Getter
  @Setter
  private static class Node<V> {

    private V value;
    private Node<V>[] next;

    public Node(int r) {
      next = new Node[r];
    }

    public Node<V> next(int index) {
      return next[index];
    }

    public void setNext(int index, Node<V> node) {
      next[index] = node;
    }
  }
}
