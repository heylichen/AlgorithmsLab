package algorithms.sedgewick.strings.tries;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

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
    int compare = c - node.getCharacter();
    if (compare > 0) {
      Node<V> child = node.getGreater();
      child = putNode(child, key, d, value);
      node.setGreater(child);
    } else if (compare < 0) {
      Node<V> child = node.getLess();
      child = putNode(child, key, d, value);
      node.setLess(child);
    } else if (d == key.length() - 1) {
      node.setValue(value);
      return node;
    } else {
      Node<V> child = node.getEqual();
      child = putNode(child, key, d + 1, value);
      node.setEqual(child);
    }
    return node;
  }

  public Iterable<String> keys() {
    return keysWithPrefix("");
  }

  public Iterable<String> keysWithPrefix(String s) {
    Queue<String> q = new LinkedList<>();
    if (s == null || s.length() == 0) {
      collect(root, "", q);
      return q;
    }

    Node<V> node = getNode(root, s, 0);

    if (node == null) {
      return q;
    }
    if (node.getValue() != null) {
      q.add(s);
    }
    Node<V> fromNode = node.getEqual();
    collect(fromNode, s, q);
    return q;
  }

  private void collect(Node<V> node, String pre, Queue<String> queue) {
    if (node == null) {
      return;
    }
    if (node.getValue() != null) {
      queue.add(pre + node.getCharacter());
    }
    collect(node.getLess(), pre, queue);
    collect(node.getEqual(), pre + node.getCharacter(), queue);
    collect(node.getGreater(), pre, queue);
  }

  public String longestPrefixOf(String s) {
    if (s == null || s.length() == 0) {
      return null;
    }
    int index = search(root, s, 0, 0);
    return s.substring(0, index);
  }

  private int search(Node<V> node, String key, int d, int length) {
    if (node == null) {
      return length;
    }
    char c = key.charAt(d);
    int compare = c - node.getCharacter();
    if (compare == 0) {
      if (node.getValue() != null) {
        length = d + 1;
      }
      if (d == key.length() - 1) {
        return length;
      }
      return search(node.getEqual(), key, d + 1, length);
    } else if (compare < 0) {
      return search(node.getLess(), key, d, length);
    } else {
      return search(node.getGreater(), key, d, length);
    }
  }

  public Iterable<String> keysThatMatch(String pattern) {
    if (pattern == null || pattern.length() == 0) {
      return Collections.emptyList();
    }
    Queue<String> q = new LinkedList<>();
    collect(root, "", pattern, q);
    return q;
  }

  private void collect(Node<V> node, String pre, String pattern, Queue<String> queue) {
    if (node == null) {
      return;
    }
    int d = pre.length();
    char nextPatternChar = pattern.charAt(d);
    int compare = nextPatternChar - node.getCharacter();
    if (nextPatternChar == '.' || compare == 0) {
      pre = pre + node.getCharacter();
      if (pre.length() == pattern.length() && node.getValue() != null) {
        queue.add(pre);
        return;
      }
      collect(node.getEqual(), pre, pattern, queue);
    } else if (compare < 0) {
      collect(node.getLess(), pre, pattern, queue);
    } else {
      collect(node.getGreater(), pre, pattern, queue);
    }
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
