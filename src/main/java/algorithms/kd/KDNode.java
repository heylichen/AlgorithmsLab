package algorithms.kd;

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


  public KDNode<T> get(double[] keyVector) {
    int currentDimension = getDimension();
    double targetV = keyVector[currentDimension];
    double currentV = getPoint().getInDimension(currentDimension);

    if (getPoint().equalsWithKey(keyVector)) {
      return this;
    }
    if (targetV < currentV) {
      return left != null ? left.get(keyVector) : null;
    } else {
      return right != null ? right.get(keyVector) : null;
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

  public NNResult<T> getNearestNeighbor(NN1TargetDistance targetDistance,
                                        HyperRectangle rectangle,
                                        double currentMinDist) {
    double[] targetKey = targetDistance.getKeyVector();
    int currentDimension = getDimension();
    double pivot = this.point.getInDimension(currentDimension);
    List<HyperRectangle> subRectangles = rectangle.cutByPivot(currentDimension, pivot);
    HyperRectangle leftRectangle = subRectangles.get(0);
    HyperRectangle rightRectangle = subRectangles.get(1);

    double targetV = targetKey[currentDimension];
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

    KDNode<T> nearestNode = null;
    double minDist = currentMinDist;
    //first check nearer node
    if (nearerNode != null) {
      NNResult<T> tmp = nearerNode.getNearestNeighbor(targetDistance, nearerRectangle, minDist);
      if (tmp != null && tmp.getDist() < minDist) {
        minDist = tmp.getDist();
        nearestNode = tmp.getNode();
      }
    }

    // check if the further rectangle within distance of minDist of target, if not ,
    // no need to search further rectangle or this node
    if (furtherRectangle.getDistance(targetKey, targetDistance.getDistance()) < minDist) {
      // first check this node
      double thisDistance = targetDistance.getDistance(this.point.getCoordinates(), targetKey);
      if (thisDistance < minDist) {
        nearestNode = this;
        minDist = thisDistance;
      }

      //then further rectangle
      if (furtherNode != null) {
        NNResult<T> tmp = furtherNode.
                              getNearestNeighbor(targetDistance, furtherRectangle, minDist);
        if (tmp != null && tmp.getDist() < minDist) {
          minDist = tmp.getDist();
          nearestNode = tmp.getNode();
        }
      }
    }

    return nearestNode == null ? null : new NNResult<>(nearestNode, minDist);
  }
}
