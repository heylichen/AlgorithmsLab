package algorithms.kd.nn;

import algorithms.kd.Entry;
import algorithms.kd.KDNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodeDistance<T> {
  private KDNode<T> node;
  private double dist;

  public NodeDistance(KDNode<T> node, double dist) {
    this.node = node;
    this.dist = dist;
  }

  public NodeDistance() {
  }

  private static final NodeDistance NONE = new NodeDistance<>(null, Double.MAX_VALUE);

  public static <T> NodeDistance<T> getNone() {
    return NONE;
  }

  public Entry<T> getEntry() {
    return node == null ? null : new Entry<>(node.getPoint(), node.getData());
  }
}