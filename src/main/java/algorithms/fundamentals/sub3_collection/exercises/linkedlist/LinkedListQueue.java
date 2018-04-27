package algorithms.fundamentals.sub3_collection.exercises.linkedlist;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by lichen2 on 2017/1/13.
 */
public class LinkedListQueue<T> implements Iterable<T> {

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

  private class Node<T> {

    private T item;
    private Node<T> next;

    public Node() {
    }

    public Node(T item, Node next) {
      this.item = item;
      this.next = next;
    }
  }


  public static void main(String[] args) {
    LinkedListQueue<Integer> queue = new LinkedListQueue<>();
    for (int i = 0; i < 100; i++) {
      queue.enqueue(i);
    }

    for (Integer i : queue) {
      StdOut.printf("%s ", i);
    }
    StdOut.print("\n");
    List<Integer> list = new ArrayList<>();
    while (!queue.isEmpty()) {
      StdOut.printf("%s ", queue.dequeue());
    }
    StdOut.print("\n");
    StdOut.printf("isEmpty: %s ", queue.isEmpty());
    test();
  }

  private static void test() {
    LinkedListQueue<Integer> queue = new LinkedListQueue<>();
    for (int i = 0; i < 3; i++) {
      queue.enqueue(i);
    }

    while (!queue.isEmpty()) {
      queue.dequeue();
    }
    System.out.println("ok");

    for (int i = 0; i < 3; i++) {
      queue.enqueue(i);
    }
    System.out.println("ok");
  }
}
