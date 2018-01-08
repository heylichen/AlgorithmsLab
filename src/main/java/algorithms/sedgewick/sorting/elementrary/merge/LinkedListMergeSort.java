package algorithms.sedgewick.sorting.elementrary.merge;

import lombok.Getter;
import lombok.Setter;

public class LinkedListMergeSort<T extends Comparable> {

  public Node<T> sort(Node<T> head) {
    if (head == null || head.next == null) {
      return head;
    }
    int length = 0;
    Node<T> current = head;
    while (current != null) {
      length++;
      current = current.next;
    }
    Node<T> newHead = head;
    int unit = 1;
    while (unit < length) {
      //do merge sub arrays
      newHead = sortSublist(newHead, unit);
      unit += unit;
    }
    return newHead;
  }

  protected Node<T> sortSublist(Node<T> head, int length) {
    Node<T> current = head;
    Node<T> previous = null;
    Node<T> left = null;
    Node<T> right = null;
    Node<T> newHead = null;
    while (true) {
      left = current;
      for (int i = 0; i < length && current != null; i++) {
        current = current.next;
      }
      right = current;
      if (right == null) {
        return newHead;
      }
      Node<T> merged = merge(left, right, length);
      if (previous != null) {
        previous.next = merged;
      } else {
        newHead = merged;
      }
      //move length
      current = merged;
      int move = length * 2;
      for (int i = 0; i < move && current != null; i++) {
        previous = current;
        current = current.next;
      }
    }
  }

  protected Node<T> merge(Node<T> left, Node<T> right, int length) {
    if (right == null) {
      return left;
    }
    Node<T> merged = null;
    int leftCount = 0;
    int rightCount = 0;

    if (less(right.getItem(), left.getItem())) {
      merged = right;
      right = right.getNext();
      rightCount++;
    } else {
      merged = left;
      left = left.getNext();
      leftCount++;
    }
    Node<T> current = merged;
    while (true) {
      if (leftCount >= length) {
        current.next = right;
        //stop return
        break;
      } else if (rightCount >= length || right == null) {
        current.next = left;
        left = left.next;
        leftCount++;
      } else if (less(right.getItem(), left.getItem())) {
        current.next = right;
        right = right.next;
        rightCount++;
      } else {
        current.next = left;
        left = left.next;
        leftCount++;
      }
      current = current.next;
    }

    return merged;
  }

  @Getter
  @Setter
  public static class Node<T extends Comparable> {

    private T item;
    private Node<T> next;

    public Node(T item, Node<T> next) {
      this.item = item;
      this.next = next;
    }

    public Node() {
    }
  }

  protected boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
  }
}
