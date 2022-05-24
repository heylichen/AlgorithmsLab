package algorithms.kd.nn;

import algorithms.kd.Entry;
import algorithms.kd.Point;
import algorithms.kd.dist.Distance;
import lombok.Getter;

import java.util.*;

@Getter
public class WithinRadius<T> extends TargetDistance {
  private final double radius;
  private final PriorityQueue<NodeDistance<T>> minPq;

  public WithinRadius(Point target, Distance distance, double radius) {
    super(target, distance);
    this.radius = radius;
    Comparator<NodeDistance<T>> maxComparator = Comparator.comparingDouble(NodeDistance::getDist);
    this.minPq = new PriorityQueue<>(maxComparator);
  }

  public void addNode(NodeDistance<T> result) {
    minPq.add(result);
  }

  public List<Entry<T>> getEntryList() {
    if (minPq.isEmpty()) {
      return Collections.emptyList();
    }
    List<Entry<T>> result = new ArrayList<>(minPq.size());
    for (NodeDistance<T> tnnResult : minPq) {
      result.add(tnnResult.getEntry());
    }
    return result;
  }
}
