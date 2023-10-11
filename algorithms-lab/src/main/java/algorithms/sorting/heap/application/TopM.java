package algorithms.sorting.heap.application;

import java.util.Stack;

import algorithms.sorting.PriorityQueue;
import algorithms.sorting.heap.PriorityQueueFactory;
import edu.princeton.cs.algs4.In;

/**
 * Created by Chen Li on 2018/2/10.
 */
public class TopM {

  public String[] getTop(In in, int top) {
    final PriorityQueue<String> minPQ = new PriorityQueueFactory().minPQ(top + 1);

    while (!in.isEmpty()) {
      minPQ.insert(in.readString());
      if (minPQ.size() > top) {
        minPQ.pop();
      }
    }

    Stack<String> stack = new Stack<>();
    while (!minPQ.isEmpty()) {
      String v = minPQ.pop();
      stack.push(v);
    }
    int size = stack.size();
    int i = 0;
    String[] array = new String[size];
    while (!stack.isEmpty()){
      array[i++] = stack.pop();
    }
    return array;
  }
}
