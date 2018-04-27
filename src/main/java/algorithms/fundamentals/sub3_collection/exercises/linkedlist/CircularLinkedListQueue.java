package algorithms.fundamentals.sub3_collection.exercises.linkedlist;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by lichen2 on 2017/1/13.
 */
public class CircularLinkedListQueue<T> implements Iterable<T> {

  private Node<T> last;
  private int count;

  public void enqueue(T item) {
    Node<T> t = new Node();
    t.item = item;
    count++;

    if (last == null) {
      last = t;
      t.next = last;
    } else {
      Node<T> next = last.next;
      last.next = t;
      t.next = next;
      last = t;
    }
  }

  public void reverseEnqueue(T item) {
    Node<T> t = new Node();
    t.item = item;
    count++;

    if (last == null) {
      last = t;
      t.next = last;
    } else {
      Node<T> next = last.next;
      last.next = t;
      t.next = next;
    }
  }

  public T dequeue() {
    if (last == null) {
      return null;
    }
    count--;
    T item = last.next.item;
    if (last.next == last) {
      last = null;
      return item;
    }
    Node<T> next = last.next.next;
    last.next.next = null;
    last.next = next;
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

    private int currentCount = 0;
    private Node<T> current = last == null ? null : last.next;

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {

    }

    @Override
    public boolean hasNext() {
      return currentCount < count;
    }

    @Override
    public T next() {
      T item = current.item;
      current = current.next;
      currentCount++;
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

  public CircularLinkedListQueue<T> reverse(CircularLinkedListQueue<T> queue) {
    //initialize
    count = 0;
    last = null;
    //
    for (T t : queue) {
      reverseEnqueue(t);
    }
    return this;
  }

  public static void main(String[] args) {
    CircularLinkedListQueue<Integer> queue = new CircularLinkedListQueue<>();
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


  }


}
