package algorithms.sedgewick.ch3_search.symboltable;


import org.apache.commons.lang3.tuple.Pair;

import java.util.Iterator;

/**
 * Created by Chen Li on 2017/6/13.
 * use linked list as it's internal data structure
 */
public class SequentialSearchST<K, V> extends AbstractST<K, V> {

  private Node first; // first node in the linked list
  private int size = 0;

  private class Node { // linked-list node
    K key;
    V val;
    Node next;

    public Node(K key, V val, Node next) {
      this.key = key;
      this.val = val;
      this.next = next;
    }
  }

  @Override
  public V get(K key) {
    Node foundNode = findNode(key).getRight();
    return foundNode == null ? null : foundNode.val;
  }

  /**
   * @param key
   * @return Pair, left: previous node of the found node,
   * right: the found node
   */
  private Pair<Node, Node> findNode(K key) {
    if (first == null || key == null) {
      return Pair.of(null, null);
    }
    Node found = null;
    Node previousOfFound = null;
    Node current = first;
    while (current != null) {
      if (current.next != null && key.equals(current.next.key)) {
        previousOfFound = current;
      }
      if (key.equals(current.key)) {
        found = current;
        break;
      } else {
        current = current.next;
      }
    }
    return Pair.of(previousOfFound, found);
  }

  @Override
  public void put(K key, V value) {
    Node found = findNode(key).getRight();
    if (found == null) {
      Node newNode = new Node(key, value, first);
      first = newNode;
      size++;
    } else {
      found.val = value;
    }
  }

  @Override
  public void delete(K key) {
    Pair<Node, Node> previousAndFound = findNode(key);
    if (previousAndFound.getRight() == null) {
      return;
    }
    if (previousAndFound.getLeft() == null) {
      if (previousAndFound.getRight() != null) {
        throw new IllegalStateException("status error");
      }
      first = first.next;
      return;
    }
    Node previousNode = previousAndFound.getLeft();
    Node found = previousNode.next;
    previousNode.next = found.next;
    size--;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterable<K> keys() {
    return new Iterable<K>() {
      @Override
      public Iterator<K> iterator() {
        return new KeysIterator(first);
      }
    };
  }

  private class KeysIterator implements Iterator<K> {
    private Node current;

    public KeysIterator(Node current) {
      this.current = current;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public K next() {
      K key = current.key;
      current = current.next;
      return key;
    }
  }

}