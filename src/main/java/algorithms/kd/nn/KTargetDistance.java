package algorithms.kd.nn;

import algorithms.kd.Entry;
import algorithms.kd.dist.Distance;
import lombok.Getter;

import java.util.*;

/**
 * constant param of top k nearest neighbor algorithm
 */
@Getter
public class KTargetDistance<T> extends TargetDistance {
  private final Integer count;
  // 优先级队列，存距离target最近的点集合。队列内按距离target大小倒序作为优先级。即离最远的target放在head.
  // a PriorityQueue, ordered by node distance to target, reversed (furthest node in head). This pq is used
  // to keep the nodes which are the top k (k == count) nearest nodes to target. This pq.size <= count in the whole
  // algorithm.
  // we use a max distance PriorityQueue, to be able to get the max distance to target quickly when maxPq is full,
  // simply by maxPq.peek().
  // to make sure nodes left in pq are the nearest to target of all nodes,
  // we remove the head and add the new node if the pq is full (size >= count).
  private PriorityQueue<NNResult<T>> maxPq;

  public KTargetDistance(double[] target, Distance distance, Integer count) {
    super(target, distance);
    this.count = count;


    Comparator<NNResult<T>> maxComparator = (a, b) -> -Double.compare(a.getDist(), b.getDist());
    this.maxPq = new PriorityQueue<>(count, maxComparator);
  }

  public double getMaxDistance() {
    if (maxPq.isEmpty()) {
      return Double.MAX_VALUE;
    }
    return maxPq.peek().getDist();
  }

  public boolean gotEnough() {
    return maxPq.size() >= count;
  }

  public void addNode(NNResult<T> node) {
    if (gotEnough()) {
      // although maxPq is a max distance PriorityQueue, head is the node which is the furthest node to target
      // by removing the furthest node from head, we can keep nearest nodes in queue.
      maxPq.poll();
    }
    maxPq.add(node);
  }

  public List<Entry<T>> getEntryList() {
    if (maxPq.isEmpty()) {
      return Collections.emptyList();
    }
    Deque<Entry<T>> stack = new ArrayDeque<>();
    while (!maxPq.isEmpty()) {
      stack.push(maxPq.poll().getEntry());
    }
    List<Entry<T>> list = new ArrayList<>(stack);
    return list;
  }
}
