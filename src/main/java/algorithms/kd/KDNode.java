package algorithms.kd;

import algorithms.kd.nn.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author lichen
 * @email heylichen@qq.com
 * @date 2022-5-12 18:37
 */
@Getter
@Setter
public class KDNode<T> {
  private final Point point;
  private final T data;
  private KDNode<T> left;
  private KDNode<T> right;
  private Integer dimension;

  public KDNode(Point point, T data, KDNode<T> left, KDNode<T> right) {
    this.point = point;
    this.left = left;
    this.right = right;
    this.data = data;
  }

  public KDNode(Entry<T> entry, KDNode<T> left, KDNode<T> right) {
    this.point = entry.getKey();
    this.left = left;
    this.right = right;
    this.data = entry.getValue();
  }

  public KDNode(Point point, T data) {
    this(point, data, null, null);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("(").append(point.toString()).append(":").append(data).append("@").append(dimension).append(")");
    return sb.toString();
  }


  public KDNode<T> get(Point target) {
    int currentDimension = getDimension();
    double targetV = target.getInDimension(currentDimension);
    double currentV = getPoint().getInDimension(currentDimension);

    if (getPoint().equalsWithKey(target)) {
      return this;
    }
    if (targetV < currentV) {
      return left != null ? left.get(target) : null;
    } else {
      return right != null ? right.get(target) : null;
    }
  }

  public void getByRange(HyperRectangle currentTreeRange, HyperRectangle targetRange, List<Entry<T>> result) {
    if (HyperRectangle.isPointInClosedRange(this.point, targetRange)) {
      result.add(getEntry());
    }

    int currentDimension = getDimension();
    double pivot = this.point.getInDimension(currentDimension);
    List<HyperRectangle> subRectangles = currentTreeRange.cutByPivot(currentDimension, pivot);
    HyperRectangle leftRectangle = subRectangles.get(0);
    HyperRectangle rightRectangle = subRectangles.get(1);

    if (leftRectangle.hasIntersectionWith(targetRange) && left != null) {
      left.getByRange(leftRectangle, targetRange, result);
    }

    if (rightRectangle.hasIntersectionWith(targetRange) && right != null) {
      right.getByRange(rightRectangle, targetRange, result);
    }
  }

  private Entry<T> getEntry() {
    return new Entry<>(point, data);
  }


  public NodeDistance<T> getNearestNeighbor(TargetDistance targetDistance,
                                            HyperRectangle rectangle,
                                            double currentMinDist) {
    NodeRectangle<T> nodeRectangle = determineAccessOrder(targetDistance, rectangle);

    double minDist = currentMinDist;
    KDNode<T> nearestNode = null;
    //first call getNearestNeighbor recursively on nearer node
    KDNode<T> nearerNode = nodeRectangle.getNearerNode();
    HyperRectangle nearerRectangle = nodeRectangle.getNearerRectangle();
    if (nearerNode != null) {
      NodeDistance<T> nearerResult = nearerNode.getNearestNeighbor(targetDistance, nearerRectangle, minDist);
      if (nearerResult != null && nearerResult.getDist() < minDist) {
        minDist = nearerResult.getDist();
        nearestNode = nearerResult.getNode();
      }
    }

    // check if the further rectangle is within distance of minDist of target, if not ,
    // no need to search further rectangle or this node
    KDNode<T> furtherNode = nodeRectangle.getFurtherNode();
    HyperRectangle furtherRectangle = nodeRectangle.getFurtherRectangle();
    if (furtherRectangle.getDistanceTo(targetDistance) < minDist) {
      // first check this node
      double thisDistance = targetDistance.calculateDistanceTo(this.point);
      if (thisDistance < minDist) {
        nearestNode = this;
        minDist = thisDistance;
      }

      //then further rectangle
      if (furtherNode != null) {
        NodeDistance<T> tmp = furtherNode.
            getNearestNeighbor(targetDistance, furtherRectangle, minDist);
        if (tmp != null && tmp.getDist() < minDist) {
          minDist = tmp.getDist();
          nearestNode = tmp.getNode();
        }
      }
    }

    return nearestNode == null ? null : new NodeDistance<>(nearestNode, minDist);
  }

  private NodeRectangle<T> determineAccessOrder(TargetDistance targetDistance,
                                                HyperRectangle rectangle) {
    int currentDimension = getDimension();
    double pivot = this.point.getInDimension(currentDimension);
    List<HyperRectangle> subRectangles = rectangle.cutByPivot(currentDimension, pivot);
    HyperRectangle leftRectangle = subRectangles.get(0);
    HyperRectangle rightRectangle = subRectangles.get(1);
    //assume the node contain target is nearer to target, try it first, in hope
    // to decrease minDist as early as possible
    double targetV = targetDistance.getTargetCoordinateIn(currentDimension);
    boolean targetInLeft = targetV < pivot;

    KDNode<T> nearerNode;
    HyperRectangle nearerRectangle;
    KDNode<T> furtherNode;
    HyperRectangle furtherRectangle;
    if (targetInLeft) {
      nearerNode = left;
      nearerRectangle = leftRectangle;
      furtherNode = right;
      furtherRectangle = rightRectangle;
    } else {
      nearerNode = right;
      nearerRectangle = rightRectangle;
      furtherNode = left;
      furtherRectangle = leftRectangle;
    }
    return new NodeRectangle<>(nearerNode, nearerRectangle, furtherNode, furtherRectangle);
  }

  /**
   * nearest nodes are stored in NNKTargetDistance.maxPq
   *
   * @param targetDistance
   * @param rectangle
   */
  public void getKNearestNeighbor(KTargetDistance<T> targetDistance,
                                  HyperRectangle rectangle) {
    NodeRectangle<T> nodeRectangle = determineAccessOrder(targetDistance, rectangle);

    //first try nearer node
    KDNode<T> nearerNode = nodeRectangle.getNearerNode();
    if (nearerNode != null) {
      HyperRectangle nearerRectangle = nodeRectangle.getNearerRectangle();
      nearerNode.getKNearestNeighbor(targetDistance, nearerRectangle);
    }

    double minDist = targetDistance.getMaxDistance();
    // check if the further rectangle within distance of minDist of target, if not ,
    // no need to search further rectangle or this node
    KDNode<T> furtherNode = nodeRectangle.getFurtherNode();
    HyperRectangle furtherRectangle = nodeRectangle.getFurtherRectangle();
    if (!targetDistance.gotEnough() ||
        furtherRectangle.getDistanceTo(targetDistance) < minDist) {
      // first check this node
      double thisDistance = targetDistance.calculateDistanceTo(this.point);
      if (!targetDistance.gotEnough() || thisDistance < minDist) {
        targetDistance.addNode(new NodeDistance<>(this, thisDistance));
      }

      //then further rectangle
      if (furtherNode != null) {
        furtherNode.getKNearestNeighbor(targetDistance, furtherRectangle);
      }
    }
  }

  /**
   * find all nodes in radius to target
   *
   * @param targetDistance
   * @param rectangle
   */
  public void getWithinRadius(WithinRadius<T> targetDistance,
                              HyperRectangle rectangle) {
    NodeRectangle<T> nodeRectangle = determineAccessOrder(targetDistance, rectangle);

    //first check nearer node
    KDNode<T> nearerNode = nodeRectangle.getNearerNode();
    if (nearerNode != null) {
      HyperRectangle nearerRectangle = nodeRectangle.getNearerRectangle();
      nearerNode.getWithinRadius(targetDistance, nearerRectangle);
    }

    double radius = targetDistance.getRadius();
    // check if the further rectangle is within distance(radius) of target, if not ,
    // no need to search further rectangle or this node
    KDNode<T> furtherNode = nodeRectangle.getFurtherNode();
    HyperRectangle furtherRectangle = nodeRectangle.getFurtherRectangle();
    if (furtherRectangle.getDistanceTo(targetDistance) <= radius) {
      // first check this node
      double thisDistance = targetDistance.calculateDistanceTo(this.point);
      if (thisDistance <= radius) {
        targetDistance.addNode(new NodeDistance<>(this, thisDistance));
      }

      //then further rectangle
      if (furtherNode != null) {
        furtherNode.getWithinRadius(targetDistance, furtherRectangle);
      }
    }
  }
}
