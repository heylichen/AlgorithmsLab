package algorithms.kd;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class NNResult<T> {
  private KDNode<T> node;
  private double dist;

  public NNResult(KDNode<T> node, double dist) {
    this.node = node;
    this.dist = dist;
  }

  public NNResult() {
  }

  private static final NNResult NONE = new NNResult<>(null, Double.MAX_VALUE);

  public static <T> NNResult<T> getNone() {
    return NONE;
  }
}