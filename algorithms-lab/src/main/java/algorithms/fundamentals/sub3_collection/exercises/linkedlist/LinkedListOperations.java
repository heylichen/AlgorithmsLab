package algorithms.fundamentals.sub3_collection.exercises.linkedlist;

import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Chen Li on 2017/4/26.
 */
public class LinkedListOperations<Item extends Integer> implements Iterable<Item> {

  private int n;         // number of elements on queue
  private Node first;    // beginning of queue
  private Node last;     // end of queue

  // helper linked list class
  public class Node {
    private Item item;
    private Node next;
  }

  /**
   * 1.3.19 Give a code fragment that removes the last node in a linked list whose first node
   * is first.
   */
  public void deleteLastNodeFromFirst() {
    if (isEmpty()) {
      return;
    }
    if (size() == 1) {
      clear();
      return;
    }
    Node nodeBeforeLast = first;
    while (!isLast(nodeBeforeLast.next)) {
      nodeBeforeLast = nodeBeforeLast.next;
    }
    nodeBeforeLast.next = null;
    n--;
    last = nodeBeforeLast;
    check();
  }

  /**
   * 1.3.20 Write a method delete() that takes an int argument k and deletes the kth element in a linked list, if it exists.
   *
   * @param k
   */
  public void deleteNodeAtIndex(int k) {
    if (k < 0 || k >= size()) {
      throw new IndexOutOfBoundsException();
    }
    if (size() == 1) {
      clear();
      return;
    }

    n--;

    if (k == 0) {
      Node oldFirst = first;
      first = first.next;
      oldFirst.next = null;
      return;
    }
    //find the k-1 th node
    Node current = first;
    while (k > 1) {
      current = current.next;
    }
    Node deleted = current.next;
    current.next = deleted.next;
    deleted.next = null;

    if (last == deleted) {
      last = current;
    }
  }

  /**
   * 1.3.21 Write a method find() that takes a linked list and a string key as arguments
   * and returns true if some node in the list has key as its item field, false otherwise.
   *
   * @param key
   * @return
   */
  public boolean findItem(Item key) {
    if (isEmpty()) {
      return false;
    }
    Node current = first;
    while (current != null && !current.item.equals(key)) {
    }
    return current != null;
  }

  /**
   * 1.3.24 Write a method removeAfter() that takes a linked-list RedBlackNode as argument and
   * removes the node following the given one (and does nothing if the argument or the next
   * field in the argument node is null).
   */
  public void removeAfter(Node node) {
    if (node == null || node.next == null) {
      return;
    }
    node.next = node.next.next;
  }

  /**
   * 1.3.25 Write a method insertAfter() that takes two linked-list RedBlackNode arguments and
   * inserts the second after the first on its list (and does nothing if either argument is null).
   */
  public void insertAfter(Node firstNode, Node secondNode) {
    if (firstNode == null || secondNode == null) {
      return;
    }
    Node firstNodeNext = firstNode.next;
    Node secondNodeTail = secondNode;
    while (secondNodeTail.next != null) {
      secondNodeTail = secondNodeTail.next;
    }

    firstNode.next = secondNode;
    secondNodeTail.next = firstNodeNext;
  }

  /**
   * 1.3.26 Write a method remove() that takes a linked list and a string key as arguments
   * and removes all of the nodes in the list that have key as its item field.
   */
  public void removeByItem(Item key) {
    if (key == null) {
      throw new IllegalArgumentException();
    }
    if (isEmpty()) {
      return;
    }

    while (first != null && first.item.equals(key)) {
      n--;
      first = first.next;
    }
    if (first == null) {
      last = null;
      return;
    }
    Node pre = first;
    while (pre.next != null) {
      while (pre.next.item.equals(key)) {
        n--;
        pre.next = pre.next.next;
      }
      if (pre.next != null) {
        pre = pre.next;
      }
    }
    last = pre;
  }

  /**
   * 1.3.27  Write a method max() that takes a reference to the first node in a linked list as
   * argument and returns the value of the maximum key in the list. Assume that all keys are
   * positive integers, and return 0 if the list is empty.
   */
  public Item findMax() {
    if (isEmpty()) {
      return null;
    }
    Item max = null;
    Node current = first;
    while (current != null) {
      if (max == null || (current.item != null && current.item.intValue() > max.intValue())) {
        max = current.item;
      }
      current = current.next;
    }
    return max;
  }


  /**
   * 1.3.28 Develop a recursive solution to the previous question.
   */
  public Item recursivelyFindMax() {
    return doFindMax(first);
  }

  private Item doFindMax(Node node) {
    if (node == null) {
      return null;
    }
    if (node.next == null) {
      return node.item;
    }
    Item a = node.item;
    Item b = doFindMax(node.next);
    if (a == null && b == null) {
      return null;
    } else if (a == null && b != null) {
      return b;
    } else if (a != null && b == null) {
      return a;
    } else if (a.intValue() > b.intValue()) {
      return a;
    } else {
      return b;
    }
  }

  private void clear() {
    if (size() == 1) {
      first = null;
      last = null;
      n = 0;
      return;
    }
  }

  private boolean isLast(Node node) {
    if (node == null) {
      throw new UnsupportedOperationException("node must not be null!");
    }
    return node.next == null;
  }

  /**
   * Initializes an empty queue.
   */
  public LinkedListOperations() {
    first = null;
    last = null;
    n = 0;
    assert check();
  }

  /**
   * Is this queue empty?
   *
   * @return true if this queue is empty; false otherwise
   */
  public boolean isEmpty() {
    return first == null;
  }

  /**
   * Returns the number of items in this queue.
   *
   * @return the number of items in this queue
   */
  public int size() {
    return n;
  }

  /**
   * Returns the item least recently added to this queue.
   *
   * @return the item least recently added to this queue
   * @throws java.util.NoSuchElementException if this queue is empty
   */
  public Item peek() {
    if (isEmpty()) throw new NoSuchElementException("Queue underflow");
    return first.item;
  }

  /**
   * Adds the item to this queue.
   *
   * @param item the item to add
   */
  public void enqueue(Item item) {
    Node oldlast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    if (isEmpty()) first = last;
    else oldlast.next = last;
    n++;
    assert check();
  }

  /**
   * Removes and returns the item on this queue that was least recently added.
   *
   * @return the item on this queue that was least recently added
   * @throws java.util.NoSuchElementException if this queue is empty
   */
  public Item dequeue() {
    if (isEmpty()) throw new NoSuchElementException("Queue underflow");
    Item item = first.item;
    first = first.next;
    n--;
    if (isEmpty()) last = null;   // to avoid loitering
    assert check();
    return item;
  }

  /**
   * Returns a string representation of this queue.
   *
   * @return the sequence of items in FIFO order, separated by spaces
   */
  public String toString() {

    StringBuilder s = new StringBuilder();
    for (Item item : this)
      s.append(item + " ");
    return s.toString();
  }

  // check internal invariants
  private boolean check() {
    if (n < 0) {
      return false;
    } else if (n == 0) {
      if (first != null) return false;
      if (last != null) return false;
    } else if (n == 1) {
      if (first == null || last == null) return false;
      if (first != last) return false;
      if (first.next != null) return false;
    } else {
      if (first == null || last == null) return false;
      if (first == last) return false;
      if (first.next == null) return false;
      if (last.next != null) return false;

      // check internal consistency of instance variable n
      int numberOfNodes = 0;
      for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
        numberOfNodes++;
      }
      if (numberOfNodes != n) return false;

      // check internal consistency of instance variable last
      Node lastNode = first;
      while (lastNode.next != null) {
        lastNode = lastNode.next;
      }
      if (last != lastNode) return false;
    }

    return true;
  }


  /**
   * Returns an iterator that iterates over the items in this queue in FIFO order.
   *
   * @return an iterator that iterates over the items in this queue in FIFO order
   */
  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  // an iterator, doesn't implement remove() since it's optional
  private class ListIterator implements Iterator<Item> {
    private Node current = first;

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      Item item = current.item;
      current = current.next;
      return item;
    }
  }


  /**
   * Unit tests the {@code LinkedQueue} data type.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    LinkedQueue<String> queue = new LinkedQueue<String>();
    while (!StdIn.isEmpty()) {
      String item = StdIn.readString();
      if (!item.equals("-"))
        queue.enqueue(item);
      else if (!queue.isEmpty())
        StdOut.print(queue.dequeue() + " ");
    }
    StdOut.println("(" + queue.size() + " left on queue)");
  }

}
