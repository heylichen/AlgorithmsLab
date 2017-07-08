package algorithms.sedgewick.ch1_fundamentals.sub3_collection.exercises.linkedlist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Chen Li on 2017/4/29.
 */
public class CircularLinkedListQueueImplTest {
  @Test
  public void testCircularLinkedQueue() {
    CircularLinkedListQueueImpl<Integer> queue = new CircularLinkedListQueueImpl<>();
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      queue.enqueue(i);
      list.add(i);
    }
    int i = 0;
    for (Integer integer : queue) {
      Integer expected = list.get(i++);
      assertEquals(expected, integer);
    }
  }

  @Test
  public void testDequeue() {
    CircularLinkedListQueueImpl<Integer> queue = new CircularLinkedListQueueImpl<>();
    queue.enqueue(1);
    Integer value = queue.dequeue();
    assertEquals(1, (int) value);
    assertEquals(true, queue.isEmpty());
  }

  @Test
  public void testEnqueueAndDequeue() {
    CircularLinkedListQueueImpl<Integer> queue = new CircularLinkedListQueueImpl<>();
    for (int i = 0; i < 10; i++) {
      queue.enqueue(i);
    }

    for (int i = 0; i < 10; i++) {
      Integer value = queue.dequeue();
      assertEquals(i, (int) value);
    }
    assertEquals(true, queue.isEmpty());
  }

}