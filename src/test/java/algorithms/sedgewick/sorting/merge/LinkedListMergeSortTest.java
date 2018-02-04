package algorithms.sedgewick.sorting.merge;

import algorithms.sedgewick.sorting.merge.LinkedListMergeSort;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/1/8.
 */
public class LinkedListMergeSortTest {

  @Test
  public void sortLargeArrayTest() {
    sortArrayBySize(1000);
  }

  protected void sortArrayBySize(int size) {
    LinkedListMergeSort.Node<Integer> head = null;
    LinkedListMergeSort.Node<Integer> current = null;

    for (int i = 0; i < size; i++) {
      int value = size - i;
      LinkedListMergeSort.Node<Integer> node = new LinkedListMergeSort.Node<Integer>(value, null);
      if (current == null) {
        head = node;
        current = node;
      } else {
        current.setNext(node);
        current = node;
      }
    }
    LinkedListMergeSort sort = new LinkedListMergeSort();
    LinkedListMergeSort.Node<Integer> sorted = sort.sort(head);

    Assert.assertTrue("must be sorted", isSorted(sorted));
  }

  private boolean isSorted(LinkedListMergeSort.Node<Integer> head) {
    if (head == null || head.getNext() == null) {
      return true;
    }
    while (head != null && head.getNext() != null) {
      if (head.getItem().compareTo(head.getNext().getItem()) > 0) {
        return false;
      }
      head = head.getNext();
    }
    return true;
  }
}