package algorithms.kd;

import algorithms.kd.dist.Distance;
import algorithms.kd.nn.KTargetDistance;
import algorithms.kd.nn.NodeDistance;
import algorithms.kd.nn.TargetDistance;
import algorithms.kd.nn.WithinRadius;
import algorithms.kd.pivot.PivotSelector;
import algorithms.kd.pivot.RandomSamplePivotSelector;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lichen
 * @email heylichen@qq.com
 * @date 2022-5-12 18:42
 */
public class KDTree<T> {
  @Getter
  private KDNode<T> root;
  // k dimensions
  private final int k;
  private final PivotSelector pivotSelector;
  private final HyperRectangle INFINITE_RECT;

  public KDTree(int k) {
    this(k, new RandomSamplePivotSelector());
  }

  public KDTree(int k, PivotSelector pivotSelector) {
    this.k = k;
    this.pivotSelector = pivotSelector;
    INFINITE_RECT = HyperRectangle.getInfiniteRect(k);
  }

  /**
   * build a k-d tree from points, more balance is better.
   * balance is dependent on the pivotSelector implementation you choose.
   *
   * @param entryList
   */
  public KDNode<T> build(List<Entry<T>> entryList) {
    return root = buildKdTree(entryList, 0);
  }

  /**
   * exact search
   *
   * @param keyVector
   * @return
   */
  public T get(double[] keyVector) {
    if (root == null) {
      return null;
    }
    KDNode<T> node = root.get(keyVector);
    return node == null ? null : node.getData();
  }

  /**
   * closed range query, it's a general range query, including these cases:
   * if some dimension range is full range, it's a partial range query.
   * if some dimension range is an exact value, it's a partial match query.
   * if all dimensions range is exact value, it's an exact match query.
   *
   * @param range
   * @return
   */
  public List<Entry<T>> getByRange(HyperRectangle range) {
    List<Entry<T>> result = new ArrayList<>();
    root.getByRange(INFINITE_RECT, range, result);
    return result;
  }

  /**
   * insert a node, duplicate key not allowed
   *
   * @param point
   */
  public void insert(Point point, T data) {
    KDNode<T> newNode = new KDNode<>(point, data);
    root = insert(newNode, root, 0);
  }

  /**
   * get the nearest neighbor point in this kd tree to target
   * target may not exist in this kd tree.
   * note: getNearestNeighbor can be implemented by direct calling getNearestNeighbor(target, distance, 1)
   * but I leave the implementation here for learning purpose. A learner can read this method first.
   *
   * @param target   the coordinates of the target key point
   * @param distance the distance calculation method suitable for your need
   * @return
   */
  public Entry<T> getNearestNeighbor(double[] target, Distance distance) {
    if (root == null) {
      return null;
    }
    TargetDistance targetDistance = new TargetDistance(target, distance);
    NodeDistance<T> r = root.getNearestNeighbor(targetDistance, INFINITE_RECT, Double.MAX_VALUE);
    return r == null ? null : r.getEntry();
  }

  /**
   * get the k nearest neighbor points in this kd tree to target
   * target may not exist in this kd tree.
   * @param target the coordinates of the target key point
   * @param distance the distance calculation method suitable for your need
   * @param count count of the nearest neighbor points
   * @return
   */
  public List<Entry<T>> getNearestNeighbor(double[] target, Distance distance, int count) {
    if (root == null) {
      return null;
    }
    KTargetDistance<T> targetDistance = new KTargetDistance<>(target, distance, count);
    root.getKNearestNeighbor(targetDistance, INFINITE_RECT);
    return targetDistance.getEntryList();
  }

  /**
   * find all nodes in radius to target
   * @param target
   * @param distance
   * @param radius
   * @return
   */
  public List<Entry<T>> getWithinRadius(double[] target, Distance distance, double radius) {
    if (root == null) {
      return null;
    }
    WithinRadius<T> targetDistance = new WithinRadius<>(target, distance, radius);
    root.getWithinRadius(targetDistance, INFINITE_RECT);
    return targetDistance.getEntryList();
  }

  private KDNode<T> insert(KDNode<T> newNode, KDNode<T> node, int d) {
    if (node == null) {
      newNode.setDimension(d);
      return newNode;
    }
    Point point = newNode.getPoint();
    int nextD = nextD(d);
    double targetV = point.getInDimension(d);
    double currentV = node.getPoint().getInDimension(d);

    if (node.getPoint().equalsWithKey(point)) {
      throw new IllegalArgumentException("duplicate key!");
    }

    if (targetV < currentV) {
      node.setLeft(insert(newNode, node.getLeft(), nextD));
    } else {
      node.setRight(insert(newNode, node.getRight(), nextD));
    }
    return node;
  }

  private int nextD(int d) {
    return (d + 1) % k;
  }

  private KDNode<T> buildKdTree(List<Entry<T>> entryList, int d) {
    if (entryList == null || entryList.isEmpty()) {
      return null;
    }
    if (entryList.size() == 1) {
      KDNode<T> node = new KDNode<>(entryList.get(0), null, null);
      node.setDimension(d);
      return node;
    }
    int nextD = (d + 1) % k;
    Entry<T> pivot = pivotSelector.extractPivot(entryList, d, (e, dimension) -> e.getKey().getInDimension(dimension));
    double pivotValue = pivot.getKey().getInDimension(d);
    List<Entry<T>> left = new ArrayList<>();
    List<Entry<T>> right = new ArrayList<>();

    for (Entry entry : entryList) {
      double v = entry.getKey().getInDimension(d);
      if (v < pivotValue) {
        left.add(entry);
      } else {
        right.add(entry);
      }
    }
    KDNode<T> leftChild = buildKdTree(left, nextD);
    KDNode<T> rightChild = buildKdTree(right, nextD);
    KDNode<T> node = new KDNode<T>(pivot, leftChild, rightChild);
    node.setDimension(d);
    return node;
  }
}
