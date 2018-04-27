package algorithms.fundamentals.sub3_collection.exercises.linkedlist;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by Chen Li on 2017/4/28.
 */
public class CircularLinkedListQueueImpl<Item> implements Iterable<Item> {
  private Node last;
  private int size;

  public CircularLinkedListQueueImpl() {
    last = null;
    size = 0;
  }

  public int size() {
    return size();
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void enqueue(Item item) {
    Node node = new Node();
    node.item = item;
    node.next = node;
    if (last == null) {
      size++;
      last = node;
      return;
    }
    node.next = last.next;
    last.next = node;
    last = node;
    size++;
  }

  public Item dequeue() {
    if (size == 0) {
      throw new IllegalStateException("can not dequeue from an empty queue!");
    }

    if (size == 1) {
      Item item = last.item;
      size = 0;
      last = null;
      return item;
    }
    size--;
    Node deletedNode = last.next;
    last.next = deletedNode.next;
    deletedNode.next = null;

    return deletedNode.item;
  }

  @Override
  public Iterator<Item> iterator() {
    return new QueueIterator();
  }

  private class QueueIterator implements Iterator<Item> {
    private Node current = last == null ? null : last.next;
    private int read = 0;


    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super Item> action) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
      return read < size;
    }

    @Override
    public Item next() {
      if (!hasNext()) {
        throw new IllegalStateException("no more elements!");
      }
      Item item = current.item;
      current = current.next;
      read++;
      return item;
    }
  }

  private class Node {
    private Item item;
    private Node next;
  }
}
