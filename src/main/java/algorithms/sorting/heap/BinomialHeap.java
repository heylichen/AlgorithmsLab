package algorithms.sorting.heap;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/7/28.
 */
@Getter
@Setter
public class BinomialHeap<K extends Comparable<K>> {

  private BinomialHeapNode<K> root;

  public BinomialHeapNode<K> insert(K key) {
    BinomialHeapNode<K> newNode = new BinomialHeapNode<>(key);
    if (root == null) {
      root = newNode;
      return root;
    }
    if (newNode.compareDegreeTo(root) < 0) {
      newNode.setRightSibling(root);
      root = newNode;
    } else {
      newNode.setRightSibling(root.getRightSibling());
      root.setRightSibling(newNode);
    }
    //keep
    keepDegree();
    //order by degree
    return newNode;
  }

  public void merge(BinomialHeap<K> another) {
    if (another == null || another.isEmpty()) {
      return;
    }
    //merge root nodes
    root = mergeNodes(this.getRoot(), another.getRoot());
    keepDegree();
  }

  private BinomialHeapNode<K> mergeNodes(BinomialHeapNode<K> nodeA, BinomialHeapNode<K> nodeB) {
    //merge root nodes
    BinomialHeapNode<K> thisNode = nodeA;
    BinomialHeapNode<K> anotherNode = nodeB;
    BinomialHeapNode<K> rootNode = null;
    BinomialHeapNode<K> currentNode = null;

    while (thisNode != null && anotherNode != null) {
      BinomialHeapNode<K> minNode = null;
      if (thisNode.compareDegreeTo(anotherNode) < 0) {
        minNode = thisNode;
        thisNode = thisNode.getRightSibling();
      } else {
        minNode = anotherNode;
        anotherNode = anotherNode.getRightSibling();
      }
      if (rootNode == null) {
        rootNode = minNode;
        currentNode = rootNode;
      } else {
        currentNode.setRightSibling(minNode);
        currentNode = minNode;
      }
    }
    if (thisNode != null) {
      currentNode.setRightSibling(thisNode);
    } else {
      currentNode.setRightSibling(anotherNode);
    }
    return rootNode;
  }

  public boolean isEmpty() {
    return root == null;
  }

  private void keepDegree() {
    BinomialHeapNode<K> current = root;

    while (current != null && current.getRightSibling() != null) {
      BinomialHeapNode<K> sibling = current.getRightSibling();
      if (!current.equalDegree(sibling)) {
        break;
      }
      //same degree
      if (current.compareTo(sibling) < 0) {
        current.setRightSibling(sibling.getRightSibling());
        sibling.setParent(current);
        sibling.setRightSibling(current.getLeftChild());
        current.setLeftChild(sibling);
        current.addDegree(1);
      } else {
        current.setRightSibling(sibling.getLeftChild());
        current.setParent(sibling);
        sibling.setLeftChild(current);
        sibling.addDegree(1);
        current = sibling;
      }
    }
    this.root = current;
  }
}
