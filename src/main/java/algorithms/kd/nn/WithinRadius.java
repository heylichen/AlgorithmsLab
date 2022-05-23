package algorithms.kd.nn;

import algorithms.kd.Entry;
import algorithms.kd.dist.Distance;
import lombok.Getter;

import java.util.*;

@Getter
public class WithinRadius<T> extends TargetDistance {
  private final double radius;
  private final PriorityQueue<NNResult<T>> minPq;

  public WithinRadius(double[] target, Distance distance, double radius) {
    super(target, distance);
    this.radius = radius;
    Comparator<NNResult<T>> maxComparator = Comparator.comparingDouble(NNResult::getDist);
    this.minPq = new PriorityQueue<>(maxComparator);
  }

  public void addNode(NNResult<T> result) {
    minPq.add(result);
  }

  public List<Entry<T>> getEntryList() {
    if (minPq.isEmpty()) {
      return Collections.emptyList();
    }
    List<Entry<T>> result = new ArrayList<>(minPq.size());
    for (NNResult<T> tnnResult : minPq) {
      result.add(tnnResult.getEntry());
    }
    return result;
  }
}
