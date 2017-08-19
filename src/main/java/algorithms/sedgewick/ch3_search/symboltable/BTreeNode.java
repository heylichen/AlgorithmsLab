package algorithms.sedgewick.ch3_search.symboltable;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

/**
 * Created by Chen Li on 2017/8/1.
 */
@Getter
@Setter
public class BTreeNode<K extends Comparable<K>, V> {
  private Object[] keys;
  private Object[] values;
  private BTreeNode<K, V>[] children;
  private boolean leaf;
  private int size;
  private int t;
  private int maxSize;

  public BTreeNode() {
  }

  public BTreeNode(int t) {
    this.t = t;
    this.maxSize = 2 * t - 1;
    this.keys = new Object[maxSize];
    this.values = new Object[maxSize];
    this.children = (BTreeNode<K, V>[]) new BTreeNode[maxSize + 1];
    this.size = 0;
  }


  /**
   * this node is leaf
   */
  private void deleteFromLeaf(int atIndex) {
    Preconditions.checkState(isLeaf(), "this method must be called on leaf node!");
    int index = atIndex + 1;
    if (index >= 0 && index < size) {
      for (int i = index; i < size; i++) {
        keys[i - 1] = keys[i];
      }
    }
    size--;
  }

  private K getReplaceKey( Pair<BTreeNode<K, V>, Integer> replacor ){
    int index = replacor.getRight();
    return (K)replacor.getLeft().getKeys()[index];
  }

  private void deleteFromInternalNode(int atIndex) {
    Preconditions.checkState(!isLeaf(), "this method must be called on internal node!");
    BTreeNode<K, V> left = children[atIndex];
    if (!left.isMinDegree()) {
      Pair<BTreeNode<K, V>, Integer> replacor = left.max();
      K replaceKey =  getReplaceKey(replacor);
      keys[atIndex] = replaceKey;
      left.delete(replaceKey);
      return;
    }

    BTreeNode<K, V> right = children[atIndex + 1];
    if (!right.isMinDegree()) {
      Pair<BTreeNode<K, V>, Integer> replacor = right.min();
      K replaceKey =  getReplaceKey(replacor);
      keys[atIndex] = replaceKey;
      right.delete(replaceKey);
      return;
    }
    //both left and right is at min degree
    K key = (K) keys[atIndex];
    shiftLeftOnePosition(keys, atIndex + 1, size - 1);
    shiftLeftOnePosition(children, atIndex + 1, size);
    copyTo(right, 0, left, t, t - 1);
    children[atIndex] = left;
    size--;
    //recursively delete
    left.increaseSize(t);
    left.deleteFromCurrentNode(t - 1);
  }

  private void deleteAndReplace(Pair<BTreeNode<K, V>, Integer> replacor, int atIndex) {
    BTreeNode<K, V> replaceNode = replacor.getKey();
    int index = replacor.getRight();
    K replaceV = (K) replaceNode.getKeys()[index];

    replaceNode.deleteFromLeaf(index);

    keys[atIndex] = replaceV;
  }

  private void deleteFromCurrentNode(int atIndex) {
    if (isLeaf()) {
      deleteFromLeaf(atIndex);
    } else {
      deleteFromInternalNode(atIndex);
    }
  }

  public void delete(K key) {
    if (size == 0) {
      return;
    }
    int rank = rank(key);

    if (isKeyAt(key, rank)) {
      deleteFromCurrentNode(rank);
      return;
    }
    //move to specific child node
    BTreeNode<K, V> targetChild = children[rank];
    if (!targetChild.isMinDegree()) {
      targetChild.delete(key);
      return;
    }

    BTreeNode<K, V> leftSibling = null;
    if (rank - 1 >= 0) {
      leftSibling = children[rank - 1];
      if (!leftSibling.isMinDegree()) {
        moveChildFromLeftSibling(rank);
        children[rank].delete(key);
        return;
      }
    }
    BTreeNode<K, V> rightSibling = null;
    if (rank + 1 <= size) {
      rightSibling = children[rank + 1];
      if (!rightSibling.isMinDegree()) {
        moveChildFromRightSibling(rank);
        children[rank].delete(key);
        return;
      }
    }
    //
    if (leftSibling != null) {
      mergeMinSiblings(leftSibling, targetChild, rank - 1);
      leftSibling.delete(key);
      return;
    }
    if (rightSibling != null) {
      mergeMinSiblings(targetChild, rightSibling, rank);
      targetChild.delete(key);
      return;
    }
  }


  public void insertNonFull(K key) {
    int i = size() - 1;
    if (isLeaf()) {
      int rank = rank(key);
      if (rank < size() && key.equals(keys[rank])) {
        //found
        return;
      }
      shiftRightOnePosition(keys, rank, size() - 1);
      keys[rank] = key;
      size += 1;
      //disk write
      return;
    }
    // not a leaf node
    while (i >= 0 && (key.compareTo((K) keys[i]) < 0)) {
      i = i - 1;
    }
    i = i + 1;
    //disk read x.children[i]
    BTreeNode<K, V> child = children[i];
    if (child.isFull()) {
      this.splitChild(i);
      if (key.compareTo((K) keys[i]) > 0) {
        i = i + 1;
      }
      child = children[i];
    }
    child.insertNonFull(key);
  }

  public void increaseSize(int amount) {
    size += amount;
  }

  public void decreaseSize(int amount) {
    size -= amount;
  }

  public void splitChild(int i) {
    BTreeNode<K, V> childNode = children[i];
    Triple<BTreeNode<K, V>, K, BTreeNode<K, V>> triple = childNode.splitByHalf();
    insertKeyWithChildren(i, triple);
  }

  public Triple<BTreeNode<K, V>, K, BTreeNode<K, V>> splitByHalf() {
    int middleIndex = size() / 2;
    int halfSize = middleIndex;
    BTreeNode<K, V> left = new BTreeNode<>(t);
    BTreeNode<K, V> right = new BTreeNode<>(t);

    copy(this, left, 0, halfSize);
    copy(this, right, middleIndex + 1, halfSize);
    left.setSize(halfSize);
    left.setLeaf(this.isLeaf());
    right.setSize(halfSize);
    right.setLeaf(this.isLeaf());
    K middleKey = (K) this.keys[middleIndex];
    return Triple.of(left, middleKey, right);
  }

  public void insertKeyWithChildren(int from, Triple<BTreeNode<K, V>, K, BTreeNode<K, V>> triple) {
    shiftRight(from);
    keys[from] = triple.getMiddle();
    children[from] = triple.getLeft();
    children[from + 1] = triple.getRight();
    increaseSize(1);
  }

  private void shiftRight(int from) {
    int size = size();

    shiftRightOnePosition(keys, from, size - 1);
    shiftRightOnePosition(values, from, size - 1);
    shiftRightOnePosition(children, from + 1, size);
  }

  private void shiftRightOnePosition(Object[] arr, int startPos, int endPos) {
    for (int i = endPos + 1; i > startPos; i--) {
      arr[i] = arr[i - 1];
    }
  }

  private void shiftLeftOnePosition(Object[] arr, int startPos, int endPos) {
    for (int i = startPos; i <= endPos; i++) {
      arr[i - 1] = arr[i];
    }
  }

  private BTreeNode<K, V> copy(BTreeNode<K, V> from, BTreeNode<K, V> to, int fromIndex, int size) {
    for (int i = 0; i < size; i++) {
      int keyIndex = fromIndex + i;

      to.getKeys()[i] = from.getKeys()[keyIndex];
      to.getValues()[i] = from.getValues()[keyIndex];
      to.getChildren()[i] = from.getChildren()[keyIndex];
    }
    to.getChildren()[size] = from.getChildren()[fromIndex + size];
    return to;
  }

  private void copyTo(BTreeNode<K, V> src, int srcPosition, BTreeNode<K, V> to, int destPosition, int length) {

    int fromIndex = srcPosition;
    int toIndex = destPosition;
    for (int i = 0; i < length; i++) {
      to.getKeys()[toIndex] = src.getKeys()[fromIndex];
      to.getValues()[toIndex] = src.getValues()[fromIndex];
      to.getChildren()[toIndex] = src.getChildren()[fromIndex];

      fromIndex++;
      toIndex++;
    }
    to.getChildren()[toIndex] = src.getChildren()[fromIndex];
  }

  //tools


  private void moveChildFromRightSibling(int childIndex) {
    BTreeNode<K, V> left = children[childIndex];
    BTreeNode<K, V> right = children[childIndex + 1];

    BTreeNode<K, V> orphanChild = right.getChildren()[0];
    K movingKey = (K) right.getKeys()[0];

    //shrink right
    right.shiftLeftOnePosition(right.getKeys(), 1, right.getSize() - 1);
    right.shiftLeftOnePosition(right.getChildren(), 1, right.getSize());
    right.decreaseSize(1);

    K parentKey = (K) keys[childIndex];
    keys[childIndex] = movingKey;

    int leftSize = left.size;
    left.getKeys()[leftSize] = parentKey;
    left.getChildren()[leftSize + 1] = orphanChild;
    left.increaseSize(1);
  }

  private void moveChildFromLeftSibling(int childIndex) {
    BTreeNode<K, V> left = children[childIndex - 1];
    BTreeNode<K, V> right = children[childIndex];

    BTreeNode<K, V> orphanChild = left.getChildren()[left.getSize()];
    K movingKey = (K) left.getKeys()[left.getSize() - 1];

    //shrink
    left.decreaseSize(1);

    K parentKey = (K) keys[childIndex - 1];
    keys[childIndex - 1] = movingKey;

    right.shiftRightOnePosition(right.getKeys(), 0, right.getSize() - 1);
    right.shiftRightOnePosition(right.getChildren(), 0, right.getSize());
    right.increaseSize(1);

    right.getKeys()[0] = parentKey;
    right.getChildren()[0] = orphanChild;
  }

  private void mergeMinSiblings(BTreeNode<K, V> left, BTreeNode<K, V> right, int startChildIndex) {
    K key = (K) keys[startChildIndex];
    shiftLeftOnePosition(keys, startChildIndex + 1, size - 1);
    shiftLeftOnePosition(children, startChildIndex + 1, size);
    this.decreaseSize(1);

    //merge to new node
    copyTo(right, 0, left, t, t - 1);
    left.getKeys()[t - 1] = key;
    left.increaseSize(t);

    this.children[startChildIndex] = left;
  }

  //--------------status
  public boolean isMinDegree() {
    return size == t - 1;
  }

  private boolean isKeyAt(K key, int rank) {
    return rank < size && key.compareTo((K) keys[rank]) == 0;
  }

  public int rank(K key) {
    int left = 0;
    int right = size - 1;
    while (left <= right) {
      int middle = left + (right - left) / 2;
      int compare = key.compareTo((K) keys[middle]);

      if (compare == 0) {
        return middle;
      } else if (compare < 0) {
        right = middle - 1;
      } else {
        left = middle + 1;
      }
    }
    return left;
  }


  public int size() {
    return size;
  }

  public boolean isFull() {
    return size == maxSize;
  }

  public int getChildrenSize() {
    return this.isLeaf() ? 0 : size() + 1;
  }

  public Pair<BTreeNode<K, V>, Integer> max() {
    if (isLeaf()) {
      return Pair.of(this, size - 1);
    }
    return children[size].max();
  }

  public Pair<BTreeNode<K, V>, Integer> min() {
    if (isLeaf()) {
      return Pair.of(this, 0);
    }
    return children[0].min();
  }
}
