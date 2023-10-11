package algorithms.strings.tries.practice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import algorithms.strings.alphabet.Alphabet;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/6/14.
 */
@Getter
@Setter
public class Trie<V> {

  private Alphabet alphabet;
  private Node<V> root;

  public void put(String key, V value) {
    root = put(root, key, value, 0);
  }

  /**
   * if Node exists, it's links must not be null, may be an empty links array
   */
  private Node<V> put(Node<V> node, String key, V value, int i) {
    if (node == null) {
      node = new Node<>();
      node.setLinks(new Node[alphabet.radix()]);
    }
    if (i == key.length()) {
      node.setValue(value);
    } else {
      char ch = key.charAt(i);
      int index = alphabet.toIndex(ch);
      Node<V> nextNode = node.links[index];
      nextNode = put(nextNode, key, value, i + 1);
      node.links[index] = nextNode;
    }

    return node;
  }

  public V get(String key) {
    Node<V> targetNode = getNode(key);
    return targetNode == null ? null : targetNode.getValue();
  }

  private Node<V> getNode(String key) {
    Node<V> current = root;
    int i = 0;
    while (i < key.length() && current != null && current.links != null) {
      char ch = key.charAt(i);
      int index = alphabet.toIndex(ch);
      current = current.links[index];
      i++;
    }

    if (i == key.length() && current != null) {
      return current;
    }
    return null;
  }

  public void clear() {
    this.root = null;
  }

  public void delete(String key) {
    this.root = delete(root, key, 0);
  }

  private Node delete(Node node, String key, int i) {
    if (node == null) {
      return node;
    }

    if (i == key.length()) {
      node.setValue(null);
    } else {
      char ch = key.charAt(i);
      int index = alphabet.toIndex(ch);
      Node nextNode = node.getLinks()[index];
      nextNode = delete(nextNode, key, i + 1);
      node.getLinks()[index] = nextNode;
    }
    //if all links null
    boolean hasChildren = false;
    for (Node node1 : node.getLinks()) {
      if (node1 != null) {
        hasChildren = true;
        break;
      }
    }
    if (!hasChildren) {
      node = null;
    }
    return node;
  }

  public Collection<String> keysWithPrefix(String key) {
    Node<V> fromNode = getNode(key);
    if (fromNode == null) {
      return Collections.emptyList();
    }
    List<String> keys = new ArrayList<>();
    if (fromNode.getValue() != null) {
      keys.add(key);
    }
    collectKeysWithPrefix(fromNode, key, keys);
    return keys;
  }

  public Collection<String> keys() {
    if (root == null) {
      return Collections.emptyList();
    }
    List<String> keys = new ArrayList<>();
    collectKeysWithPrefix(root, "", keys);
    return keys;
  }

  /**
   * depth first search, a iterative method, 遍历
   */
  private void collectKeysWithPrefix(Node node, String prefix, Collection<String> keys) {
    for (int i = 0; i < node.getLinks().length; i++) {
      Node next = node.getLinks()[i];
      if (next == null) {
        continue;
      }
      char ch = alphabet.toChar(i);
      String nextPrefix = prefix + ch;
      if (next.getValue() != null) {
        keys.add(nextPrefix);
      }
      if (next.getLinks() != null) {
        collectKeysWithPrefix(next, prefix + ch, keys);
      }
    }
  }

  public String longestPrefixOf(String key) {
    Node<V> current = root;
    int i = 0;
    int maxIndex = 0;
    while (i < key.length() && current != null) {
      char ch = key.charAt(i);
      int index = alphabet.toIndex(ch);
      current = current.links[index];
      i++;
      if (current != null && current.getValue() != null) {
        maxIndex = i;
      }
    }

    return maxIndex > 0 ? key.substring(0, maxIndex) : null;
  }

  @Getter
  @Setter
  private class Node<V> {
    private V value;
    private Node<V>[] links;
  }
}
