package algorithms.search.symboltable.skiplist;

import algorithms.search.symboltable.AbstractST;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * a simple lazy skip list implementation.
 * when init, level count is 1
 * only when adding key values, will expand levels on need.
 * when deleting mappings, will not remove empty levels in case may need them later.
 * @param <K>
 * @param <V>
 */
public class LazySkipList<K extends Comparable<K>, V> extends AbstractST<K, V> {
  //top level head
  private Node<K, V> head;
  //top level tail
  private Node<K, V> tail;
  //convenient but not required
  private final int maxLevel;
  private int size;

  public LazySkipList(int maxLevel) {
    this.maxLevel = maxLevel;
    head = new HeadNode<>();
    tail = new TailNode<>();
    tail.insertAfter(head);
    this.size = 0;
  }

  public V get(K key) {
    Node<K, V> node = getNode(key);
    return node.compareToKey(key) == 0 ? node.value : null;
  }

  public void put(K key, V value) {
    //find node
    Node<K, V> current = head;
    Deque<Node<K, V>> stack = new LinkedList<>();
    while (true) {
      //skip
      while (current.next.compareToKey(key) <= 0) {
        current = current.next;
      }
      //move to level below
      if (current.bottom == null) {
        //leaf level
        break;
      } else {
        stack.push(current);
        current = current.bottom;
      }
    }
    //on the leaf level
    while (current.next.compareToKey(key) <= 0) {
      current = current.next;
    }
    if (current.compareToKey(key) == 0) {
      current.setValue(value);
      return;
    }
    //insert new node
    Node<K, V> bottom = new Node<>(key, value);
    bottom.insertAfter(current);
    this.size++;

    int levels = 1;
    while (levels <= maxLevel && promote()) {
      Node<K, V> upperLevelNode = stack.isEmpty() ? null : stack.pop();
      if (upperLevelNode == null) {
        newHeadTail();
        upperLevelNode = head;
      }

      Node<K, V> currentNewLevelNode = new Node<>(key, value);
      currentNewLevelNode.insertAfter(upperLevelNode);
      bottom.bindTop(currentNewLevelNode);
      bottom = currentNewLevelNode;
    }
  }

  private void newHeadTail() {
    Node<K, V> newHead = new HeadNode<>();
    Node<K, V> newTail = new TailNode<>();
    newTail.insertAfter(newHead);
    head.bindTop(newHead);
    tail.bindTop(newTail);

    head = newHead;
    tail = newTail;
  }

  private boolean promote() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  public void delete(K key) {
    Node<K, V> node = getNode(key);
    if (node.compareToKey(key) != 0) {
      return;
    }
    this.size--;
    while (node != null) {
      node.deleteHorizontally();
      node = node.top;
    }
    //may delete empty levels optionally
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterable<K> keys() {
    List<K> keys = new ArrayList<>(size);
    Node<K, V> node = this.head;
    while (node.bottom != null) {
      node = node.bottom;
    }

    while (node != null) {
      if (node.getKey() != null) {
        keys.add(node.key);
      }
      node = node.next;
    }
    return keys;
  }

  /**
   * return largest node, such that node.key<=key
   *
   * @param key
   * @return
   */
  private Node<K, V> getNode(K key) {
    Node<K, V> current = head;
    while (true) {
      //skip
      while (current.next.compareToKey(key) <= 0) {
        current = current.next;
      }
      //move to level below
      if (current.bottom == null) {
        //leaf level
        break;
      } else {
        current = current.bottom;
      }
    }
    //on the leaf level
    while (current.next.compareToKey(key) <= 0) {
      current = current.next;
    }
    return current;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Node<K, V> current = head;
    while (current != null) {
      genOneLine(sb, current);
      current = current.bottom;
    }
    return sb.toString();
  }

  private void genOneLine(StringBuilder sb, Node<K, V> current) {
    Node<K, V> node = current;
    while (node != null) {
      if (node.key != null) {
        sb.append(node.key);
        if (node.top != null) {
          sb.append("(").append(node.top.key).append(")");
        }
        sb.append(" ");
      }
      node = node.next;
    }
    sb.append("\n");
  }

  @Getter
  @Setter
  private static class Node<K extends Comparable<K>, V> {
    private Node<K, V> pre;
    private Node<K, V> next;
    private Node<K, V> top;
    private Node<K, V> bottom;
    private K key;
    private V value;

    public Node() {
    }

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public int compareToKey(K k) {
      return this.key.compareTo(k);
    }

    public void insertAfter(Node<K, V> node) {
      Node<K, V> oldNext = node.next;
      this.pre = node;
      this.next = oldNext;

      node.next = this;
      if (oldNext != null) {
        oldNext.pre = this;
      }
    }

    public void bindTop(Node<K, V> node) {
      this.top = node;
      node.bottom = this;
    }

    public void deleteHorizontally() {
      Node<K, V> pre = this.pre;
      Node<K, V> next = this.next;

      pre.next = next;
      next.pre = pre;
      this.pre = null;
      this.next = null;
    }
  }

  @Getter
  @Setter
  private static class HeadNode<K extends Comparable<K>, V> extends Node<K, V> {
    public int compareToKey(K k) {
      return -1;
    }
  }

  @Getter
  @Setter
  private static class TailNode<K extends Comparable<K>, V> extends Node<K, V> {
    public int compareToKey(K k) {
      return 1;
    }
  }
}