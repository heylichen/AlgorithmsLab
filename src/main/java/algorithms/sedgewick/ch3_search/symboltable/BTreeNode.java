package algorithms.sedgewick.ch3_search.symboltable;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Triple;

/**
 * Created by Chen Li on 2017/8/1.
 */
@Getter
@Setter
public class BTreeNode<K, V> {
  private Object[] keys;
  private Object[] values;
  private BTreeNode<K, V>[] children;
  private boolean leaf;

  public BTreeNode() {

  }

  public BTreeNode(int keySize) {
    this.keys = new Object[keySize];
    this.values = new Object[keySize];
    this.children = (BTreeNode<K, V>[]) new BTreeNode[keySize + 1];
  }

  public int getKeyCount() {
    return keys == null ? 0 : keys.length;
  }

  public void splitChild(int i) {
    BTreeNode<K, V> childNode = children[i];
    Triple<BTreeNode<K, V>, K, BTreeNode<K, V>> triple = childNode.splitByHalf();
    insertKeyWithChildren(i, triple);
  }

  public Triple<BTreeNode<K, V>, K, BTreeNode<K, V>> splitByHalf() {
    int middleIndex = getKeyCount() / 2;
    int halfSize = middleIndex;
    BTreeNode<K, V> left = new BTreeNode<>(halfSize);
    BTreeNode<K, V> right = new BTreeNode<>(halfSize);

    copy(this, left, 0, halfSize);
    copy(this, right, middleIndex + 1, halfSize);
    K middleKey = (K) this.keys[middleIndex];
    return Triple.of(left, middleKey, right);
  }

  public void insertKeyWithChildren(int from, Triple<BTreeNode<K, V>, K, BTreeNode<K, V>> triple) {
    shiftRight(from);
    keys[from] = triple.getMiddle();
    children[from] = triple.getLeft();
    children[from + 1] = triple.getRight();
  }

  private void shiftRight(int from) {
    int size = getKeyCount();
    int childrenSize = size + 1;
    Object[] newKeys = new Object[size + 1];
    Object[] newValues = new Object[size + 1];
    BTreeNode<K, V>[] newChildren = new BTreeNode[childrenSize + 1];

    extendCopyArray(keys, newKeys, from, size - from);
    extendCopyArray(values, newValues, from, size - from);

    extendCopyArray(children, newChildren, from + 1, size - from);
    this.keys = newKeys;
    this.values = newValues;
    this.children = newChildren;
  }

  private void extendCopyArray(Object[] from, Object[] to, int start, int offset) {
    if (from == null || start < 0) {
      return;
    }
    if (from.length >= start) {
      System.arraycopy(from, 0, to, 0, start);
    }

    if (start < from.length && start + 1 < to.length && start + offset <= from.length) {
      System.arraycopy(from, start, to, start + 1, offset);
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
}
