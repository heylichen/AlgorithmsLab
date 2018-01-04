package algorithms.sedgewick.sorting.elementrary.merge;

import lombok.Getter;
import lombok.Setter;

public class LinkedListMergeSort<T extends Comparable> {


  public void sort(Node<T> head) {
    if (head.next == null) {
      return;
    }
  }

  protected void sortSubslist(Node<T> head, int length) {
    Node<T> lastNode = null;
    Node<T> left = head;
    Node<T> right = left;
    while (true) {
      for (int i = 0; i < length && right != null; i++) {
        right = right.next;
      }
      Node<T> newHead = merge(left, right, length);
      if (lastNode != null) {
        lastNode.next = newHead;
      }
      lastNode =
    }
  }

  protected Node<T> merge(Node<T> preNode, Node<T> left, Node<T> right, int length) {
    if (right == null) {
      return;
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
