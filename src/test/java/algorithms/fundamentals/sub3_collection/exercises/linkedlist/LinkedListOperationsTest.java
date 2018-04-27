package algorithms.fundamentals.sub3_collection.exercises.linkedlist;

import algorithms.fundamentals.sub3_collection.exercises.linkedlist.LinkedListOperations;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Chen Li on 2017/4/26.
 */
public class LinkedListOperationsTest {
  @Test
  public void deleteLastNodeFromFirstInEmptyQueue() throws Exception {
    LinkedListOperations<Integer> intQueue = new LinkedListOperations();
    intQueue.deleteLastNodeFromFirst();
    assertTrue("delete last node from first node from an empty queue, size==0", intQueue.size() == 0);
  }

  @Test
  public void deleteLastNodeFromFirst() throws Exception {
    for (int i = 1; i < 10; i++) {
      LinkedListOperations<Integer> intQueue = new LinkedListOperations();
      List<Integer> ints = new ArrayList<>();
      for (int j = 0; j < i; j++) {
        ints.add(j);
        intQueue.enqueue(j);
      }
      intQueue.deleteLastNodeFromFirst();
      assertEquals(intQueue.size(), i - 1);

      int k = 0;
      for (Integer integer : intQueue) {
        Integer value = ints.get(k++);
        assertEquals(value, integer);
      }
    }
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void deleteAtIndexWhenEmpty() throws Exception {
    LinkedListOperations<Integer> intQueue = new LinkedListOperations();
    intQueue.deleteNodeAtIndex(0);
  }

  @Test
  public void deleteAtIndexStart() {
    LinkedListOperations<Integer> intQueue = new LinkedListOperations();
    intQueue.enqueue(1);
    intQueue.deleteNodeAtIndex(0);
    assertTrue(intQueue.size() == 0);
  }

  @Test
  public void deleteAtIndexStartWith2Nodes() throws Exception {
    LinkedListOperations<Integer> intQueue = new LinkedListOperations();
    intQueue.enqueue(1);
    intQueue.enqueue(2);
    intQueue.deleteNodeAtIndex(0);
    assertTrue(intQueue.size() == 1);
    assertEquals(2, (int) intQueue.dequeue());
  }

  @Test
  public void deleteAtIndexEndWith2Nodes() throws Exception {
    LinkedListOperations<Integer> intQueue = new LinkedListOperations();
    intQueue.enqueue(1);
    intQueue.enqueue(2);
    intQueue.deleteNodeAtIndex(1);
    assertTrue(intQueue.size() == 1);
    assertEquals(1, (int) intQueue.dequeue());
  }

  @Test
  public void testFindMax() throws Exception {
    LinkedListOperations<Integer> intQueue = new LinkedListOperations();
    for (int i = 0; i < 100; i++) {
      intQueue.enqueue(100 - i);
    }
    assertEquals(100, intQueue.findMax().intValue());
  }

  @Test
  public void testRecursivelyFindMax() throws Exception {
    LinkedListOperations<Integer> intQueue = new LinkedListOperations();
    for (int i = 0; i < 100; i++) {
      intQueue.enqueue(100 - i);
    }
    intQueue.enqueue(101);
    assertEquals(101, intQueue.recursivelyFindMax().intValue());
  }
}