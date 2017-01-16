package algorithms.sedgewick.ch1_fundamentals.sub3_collection.exercises.linkedlist;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by Chen Li on 2017/1/14.
 */
public class DoubleLinkedQueue<T> implements Iterable<T> {
  private DoubleNode<T> last;
  private int count = 0;

  public class DoubleNode<T> {
    private T item;
    private DoubleNode<T> pre;
    private DoubleNode<T> next;
  }


  public void enqueue(T item) {
    DoubleNode t = new DoubleNode();
    t.item = item;
    count++;

    if (last == null) {
      last = t;
      last.pre = last;
      last.next = last;
    } else {
      DoubleNode<T> nextNode = last.next;
      last.next = t;
      t.next = nextNode;

      nextNode.pre = t;
      t.pre = last;

      last = t;
    }
  }

  public T dequeue() {
    if (last == null) {
      return null;
    }
    count--;
    T item = last.next.item;
    //modify linked list
    if (count == 0) {
      last = null;
      return item;
    }

    DoubleNode<T> delNode = last.next;
    DoubleNode<T> nextNode = delNode.next;

    delNode.pre = null;
    delNode.next = null;

    last.next = nextNode;
    nextNode.pre = last;
    return item;
  }

  public boolean isEmpty() {
    return count == 0;
  }

  @Override
  public Iterator<T> iterator() {
    return new QueueIterator();
  }

  private class QueueIterator implements Iterator<T> {
    private int iteratedCount = 0;
    private DoubleNode<T> current = last == null ? null : last.next;

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {

    }

    @Override
    public boolean hasNext() {
      return iteratedCount < count;
    }

    @Override
    public T next() {
      if (current == null) {
        throw new IllegalStateException("can not dequeue when queue is Empty!");
      }
      T item = current.item;
      current = current.next;
      iteratedCount++;
      return item;
    }
  }

  //------------------------- tool methods for exercises
  public DoubleNode<T> firstNode() {
    return last == null ? null : last.next;
  }

  public DoubleNode<T> lastNode() {
    return last;
  }

  public DoubleNode<T> nodeAt(int k) {
    if (k < 0 || k >= count) {
      return null;
    }
    DoubleNode<T> current = last.next;
    for (int i = 0; i <= k; i++) {
      current = current.next;
    }
    return current;
  }

  public DoubleNode<T> newNode(T item) {
    DoubleNode<T> node = new DoubleNode<>();
    node.item = item;
    return node;
  }

  /**
   * remove a given node
   */
  public T removeNode(DoubleNode<T> that) {
    if (that == null) {
      return null;
    }
    //check that is in linked list, or comment this logic here, check it at the client side for performance
    if (that != last) {
      DoubleNode<T> current = last.pre;
      while (current != last && current != that) {
        current = current.pre;
      }
      if (current != that) {
        throw new IllegalStateException("can not remove a node that is not in this queue!");
      }
    }

    count--;
    if (count == 0) {
      last = null;
      return that.item;
    }
    DoubleNode<T> pre = that.pre;
    DoubleNode<T> next = that.next;

    that.pre = null;
    that.next = null;

    pre.next = next;
    next.pre = pre;

    if (that == last) {
      last = pre;
    }
    return that.item;
  }

  public void insertBefore(DoubleNode<T> that, DoubleNode<T> t) {
    count++;
    if (that == null) {
      last = t;
      return;
    }
    DoubleNode<T> pre = that.pre;
    insertNodeAfter(pre, t);
  }


  public void insertNodeAfter(DoubleNode<T> that, DoubleNode<T> t) {
    count++;
    if (that == null) {
      last = t;
      return;
    }

    DoubleNode<T> nextNode = that.next;
    that.next = t;
    t.next = nextNode;

    nextNode.pre = t;
    t.pre = that;
    //attention, if insert after last node, after inserting, change last variable to reference the inserted one.
    if (that == last) {
      last = t;
    }
  }


  public void insertAtBegin(T item) {
    DoubleNode t = new DoubleNode();
    t.item = item;
    count++;

    if (last == null) {
      last = t;
      last.pre = last;
      last.next = last;
    } else {
      DoubleNode<T> nextNode = last.next;
      last.next = t;
      t.next = nextNode;

      nextNode.pre = t;
      t.pre = last;

//      last = t; //comment this line to enable insert at the begining of the queue
    }
  }

  public void insertAtEnd(T item) {
    enqueue(item);
  }

  public T removeFromBegin() {
    return removeNode(firstNode());
  }

  public T removeFromEnd() {
    return removeNode(last);
  }

  public static void main(String[] args) {
//    DoubleLinkedQueue<Integer> q = new DoubleLinkedQueue<>();
//    for (int i = 0; i < 3; i++) {
//      q.enqueue(i);
//    }
//    for (Integer item : q) {
//      StdOut.printf("%s ", item);
//    }
//    StdOut.printf("\n");
//
//    while (!q.isEmpty()) {
//      Integer item = q.dequeue();
//      StdOut.printf("%s ", item);
//    }
//    StdOut.printf("\n");
//    StdOut.printf("isEmpty:%s ", q.isEmpty());

    testRemoveNode();
  }

  private static void testInsertAfterNode() {
    DoubleLinkedQueue<Integer> q = new DoubleLinkedQueue<>();
    q.enqueue(1);
    DoubleLinkedQueue<Integer>.DoubleNode<Integer> lastNode = q.lastNode();
    DoubleLinkedQueue<Integer>.DoubleNode<Integer> node = q.newNode(2);
    q.insertNodeAfter(lastNode, node);
    System.out.println("ok");
  }

  private static void testRemoveNode() {
    DoubleLinkedQueue<Integer> q = new DoubleLinkedQueue<>();
    populateValues(q, 3);


    StdOut.printf("\nvalues: ");
    while (!q.isEmpty()) {
      Integer v = q.removeNode(q.last);
      StdOut.printf("%s ", v);
    }


    populateValues(q, 3);
    StdOut.printf("\nafter reset, ");
    printQueue(q);

    StdOut.printf("\nvalues: ");
    while (!q.isEmpty()) {
      Integer v = q.removeNode(q.firstNode());
      StdOut.printf("%s ", v);
    }

    populateValues(q, 3);
    q.removeNode(q.newNode(1));

  }

  private static void populateValues(DoubleLinkedQueue<Integer> q, int size) {
    for (int i = 0; i < size; i++) {
      q.enqueue(i);
    }
  }

  private static void printQueue(DoubleLinkedQueue<Integer> q) {
    StdOut.printf("values: ");
    for (Integer v : q) {
      StdOut.printf("%s ", v);
    }
  }

}
