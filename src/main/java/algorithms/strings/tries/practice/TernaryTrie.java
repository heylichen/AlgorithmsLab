package algorithms.strings.tries.practice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/6/17.
 */
public class TernaryTrie<V> {

  private Node<V> root;
  private int count = 0;

  public V get(String key) {
    Node<V> node = getNode(root, key, 0);
    return node == null ? null : node.getValue();
  }

  private Node<V> getNode(Node<V> node, String key, int i) {
    if (node == null) {
      return null;
    }

    char ch = key.charAt(i);
    int compare = ch - node.character;
    if (compare == 0) {
      if (i == key.length() - 1) {
        return node;
      } else {
        return getNode(node.equal, key, i + 1);
      }
    } else if (compare < 0) {
      return getNode(node.less, key, i);
    } else {
      return getNode(node.greater, key, i);
    }
  }

  public void put(String key, V value) {
    root = putNode(root, key, value, 0);
  }

  private Node<V> putNode(Node<V> node, String key, V value, int i) {
    char ch = key.charAt(i);
    if (node == null) {
      node = new Node<>();
      node.character = ch;
    }
    int compare = ch - node.character;
    if (compare == 0) {
      if (i == key.length() - 1) {
        if (node.value == null) {
          count++;
        }
        node.value = value;
      } else {
        Node equal = putNode(node.equal, key, value, i + 1);
        node.equal = equal;
      }
    } else if (compare < 0) {
      Node less = putNode(node.less, key, value, i);
      node.less = less;
    } else {
      Node greater = putNode(node.greater, key, value, i);
      node.greater = greater;
    }
    return node;
  }

  public void delete(String key) {
    root = deleteNode(root, key, 0);
  }

  public void clear() {
    this.root = null;
  }

  private Node<V> deleteNode(Node<V> node, String key, int i) {
    if (node == null) {
      return null;
    }
    char ch = key.charAt(i);
    int compare = ch - node.character;
    if (compare == 0) {
      if (i == key.length() - 1) {
        if (node.value != null) {
          count--;
        }
        node.value = null;
      } else {
        Node equal = deleteNode(node.equal, key, i + 1);
        node.equal = equal;
      }
    } else if (compare < 0) {
      Node less = deleteNode(node.less, key, i);
      node.less = less;

    } else {
      Node greater = deleteNode(node.greater, key, i);
      node.greater = greater;
    }
    if (node.value == null && node.equal == null && node.less == null && node.greater == null) {
      node = null;
    }
    return node;
  }

  public Collection<String> keysWithPrefix(String prefix) {
    if (isEmpty()) {
      return Collections.emptyList();
    }
    Collection<String> keys = new ArrayList<>();
    Node fromNode = getNode(root, prefix, 0);
    collect(fromNode, prefix.substring(0, prefix.length() - 1), keys);
    return keys;
  }

  private void collect(Node node, String prefix, Collection<String> keys) {
    if (node == null) {
      return;
    }

    if (node.value != null) {
      keys.add(prefix + node.character);
    }
    if (node.less != null) {
      collect(node.less, prefix, keys);
    }
    if (node.greater != null) {
      collect(node.greater, prefix, keys);
    }
    if (node.getEqual() != null) {
      collect(node.equal, prefix + node.character, keys);
    }
  }

  public String longestPrefixOf(String key) {
    if (root == null) {
      return null;
    }
    int max = 0;
    Node current = root;
    int i = 0;
    while (i < key.length() && current != null) {
      char currentChar = key.charAt(i);
      int compare = currentChar - current.character;
      if (compare == 0) {
        if (current.value != null) {
          max = i;
        }
        current = current.equal;
        i++;
      } else if (compare < 0) {
        current = current.less;
      } else {
        current = current.greater;
      }
    }
    return max > 0 ? key.substring(0, max + 1) : null;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return count;
  }

  @Getter
  @Setter
  private class Node<V> {

    private Node<V> less;
    private Node<V> equal;
    private Node<V> greater;
    private V value;
    private char character;
  }
}
