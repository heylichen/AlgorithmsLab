package algorithms.kd;

import lombok.Getter;

/**
 * for storing intermediate results, mainly for
 * nearest neighbor search
 *
 * @param <T>
 */
@Getter
class NodeRectangle<T> {
  private final KDNode<T> nearerNode;
  private final HyperRectangle nearerRectangle;
  private final KDNode<T> furtherNode;
  private final HyperRectangle furtherRectangle;

  public NodeRectangle(KDNode<T> nearerNode, HyperRectangle nearerRectangle, KDNode<T> furtherNode, HyperRectangle furtherRectangle) {
    this.nearerNode = nearerNode;
    this.nearerRectangle = nearerRectangle;
    this.furtherNode = furtherNode;
    this.furtherRectangle = furtherRectangle;
  }
}
