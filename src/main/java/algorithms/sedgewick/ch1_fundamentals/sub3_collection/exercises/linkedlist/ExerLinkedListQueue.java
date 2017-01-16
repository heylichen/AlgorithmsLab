package algorithms.sedgewick.ch1_fundamentals.sub3_collection.exercises.linkedlist;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by lichen2 on 2017/1/13.
 */
public class ExerLinkedListQueue<T> implements Iterable<T> {

  private Node<T> first;
  private Node<T> last;
  private int count;

  public void enqueue(T item) {
    Node t = new Node();
    t.item = item;
    count++;

    if (first == null) {
      t.next = null;
      first = t;
      last = t;
    } else {
      t.next = last.next;
      last.next = t;
      last = t;
    }
  }

  public T dequeue() {
    if (first == null) {
      return null;
    }
    count--;
    T item = first.item;
    Node<T> next = first.next;
    first.next = null;
    first = next;
    if (first == null) {
      last = null;
    }
    return item;
  }

  public boolean isEmpty() {
    return count == 0;
  }

  public int size() {
    return count;
  }

  @Override
  public Iterator<T> iterator() {
    return new QueueIterator();
  }

  private class QueueIterator implements Iterator<T> {

    private Node<T> current = first;

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {

    }

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public T next() {
      T item = current.item;
      current = current.next;
      return item;
    }
  }

  public class Node<T> {

    private T item;
    private Node<T> next;

  }

  /**
   * 1.3.20 Write a method delete() that takes an int argument k and deletes the kth element
   * in a linked list, if it exists. index k start from 0
   */
  public void delete(int k) {
    if (k + 1 >= count) {
      return;
    }
    int previous = k - 1;
    Node<T> current = first;
    for (int i = 0; i < previous; i++) {
      current = current.next;
    }
    Node<T> next = current.next.next;
    current.next.next = null;
    current.next = next;
  }

  public Node<T> getNodeAt(int k) {
    if (k + 1 >= count) {
      return null;
    }
    Node<T> current = first;
    for (int i = 0; i < k; i++) {
      current = current.next;
    }
    return current;
  }

  /**
   * 1.3.24 Write a method removeAfter() that takes a linked-list Node as argument and
   * removes the node following the given one (and does nothing if the argument or the next
   * field in the argument node is null).
   */
  public void removeAfter(Node<T> node) {
    if (node == null || node.next == null) {
      return;
    }
    Node<T> next = node.next.next;
    node.next.next = null;
    node.next = next;
  }

  /**
   * 1.3.25 Write a method insertAfter() that takes two linked-list Node arguments and
   * inserts the second after the first on its list (and does nothing if either argument is null).
   */
  public void insertAfter(Node<T> node, Node<T> newNode) {
    if (node == null) {
      return;
    }
    newNode.next = node.next;
    node.next = newNode;
  }

  /**
   * 1.3.26 Write a method remove() that takes a linked list and a string key as arguments
   * and removes all of the nodes in the list that have key as its item field.
   */
  public void remove(T key) {
    if (isEmpty()) {
      return;
    }
    //check first node
    while (first != null && first.item.equals(key)) {
      first = first.next;
      count--;
    }
    if (first == null) {
      last = null;
      count = 0;
      return;
    }
    //iterate
    Node<T> current = first;
    while (current.next != null) {
      if (current.next.item.equals(key)) {
        Node<T> next = current.next.next;
        current.next.next = null;
        current.next = next;
        count--;
      } else {
        current = current.next;
      }
    }
  }

  public T max(Comparator<T> comparator) {
    if (first == null) {
      return null;
    }
    T max = null;
    Node<T> current = first;
    while (current != null) {
      T item = current.item;
      if (max == null || comparator.compare(item, max) > 0) {
        max = item;
      }
      current = current.next;
    }
    return max;
  }

  public T recursiveMax(Comparator<T> comparator) {
    return recursiveMax(comparator, first);
  }

  /**
   * attention, Vulnerable to stack overflow error
   */
  public T recursiveMax(Comparator<T> comparator, Node<T> queue) {
    if (queue == null) {
      return null;
    }

    T headValue = queue.item;
    T tailValue = recursiveMax(comparator, queue.next);
    int comp = comparator.compare(headValue, tailValue);
    if (comp > 0) {
      return headValue;
    } else {
      return tailValue;
    }
  }

  /**
   * 1.3.27 Write a method recursiveMax() that takes a reference to the first node in a linked list as
   * argument and returns the value of the maximum key in the list. Assume that all keys are
   * positive integers, and return 0 if the list is empty.
   */


  public static void main(String[] args) {
    ExerLinkedListQueue<Integer> queue = new ExerLinkedListQueue<>();
    for (int i = 0; i < 10; i++) {
      queue.enqueue(i);
    }

    for (Integer i : queue) {
      StdOut.printf("%s ", i);
    }
    StdOut.print("\n");
    queue.delete(2);
    for (Integer i : queue) {
      StdOut.printf("%s ", i);
    }

    //removeafter
    queue = new ExerLinkedListQueue<>();
    for (int i = 0; i < 10; i++) {
      queue.enqueue(i);
    }
    ExerLinkedListQueue.Node node3 = queue.getNodeAt(3);
    System.out.println("\n-->" + node3.item);

    queue.removeAfter(node3);
    for (Integer i : queue) {
      StdOut.printf("%s ", i);
    }

    ExerLinkedListQueue.Node node30 = queue.new Node<Integer>();
    node30.item = 30;

    queue.insertAfter(node3, node30);
    System.out.println();
    for (Integer i : queue) {
      StdOut.printf("%s ", i);
    }
  }


}
