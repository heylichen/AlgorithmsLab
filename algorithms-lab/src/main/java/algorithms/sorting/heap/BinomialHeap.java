package algorithms.sorting.heap;

import java.util.Deque;
import java.util.LinkedList;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/7/28.
 */
@Getter
@Setter
public class BinomialHeap<K extends Comparable<K>> {

  private BinomialHeapNode<K> root;
  public static final BinomialHeap EMPTY_HEAP = new BinomialHeap();


  public static final <K extends Comparable<K>> BinomialHeap<K> emptyHeap() {
    return (BinomialHeap<K>) EMPTY_HEAP;
  }

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
    if (this.root == null) {
      this.root = another.getRoot();
      return;
    }
    //merge root nodes
    root = mergeNodes(this.getRoot(), another.getRoot());
    keepDegree();
  }

  public BinomialHeapNode<K> deleteMin() {
    //find min and it's parent
    Pair<BinomialHeapNode<K>, BinomialHeapNode<K>> leftAndMinNode = findMinNode(root);
    BinomialHeapNode<K> minNode = leftAndMinNode.getRight();
    BinomialHeapNode<K> left = leftAndMinNode.getRight();
    //remove min node from root nodes
    if (minNode == root) {
      root = minNode.getRightSibling();
    } else {
      left.setRightSibling(minNode.getRightSibling());
    }
    minNode.setRightSibling(null);
    minNode.setDegree(0);
    //make a new heap out of minNode's children
    BinomialHeap<K> childrenHeap = makeHeapOutOfChildren(minNode.getLeftChild());
    minNode.setLeftChild(null);

    merge(childrenHeap);
    return minNode;
  }

  /**
   * every child of a binomial heap root node is a binomial tree
   * @param leftChild
   * @return
   */
  private BinomialHeap<K> makeHeapOutOfChildren(BinomialHeapNode<K> leftChild) {
    if (leftChild == null) {
      return BinomialHeap.emptyHeap();
    }
    BinomialHeapNode<K> currentChild = leftChild;
    Deque<BinomialHeapNode<K>> stack = new LinkedList<>();
    while (currentChild != null) {
      currentChild.setParent(null);
      stack.push(currentChild);
      currentChild = currentChild.getRightSibling();
    }
    BinomialHeapNode<K> reorderedChildRoot = null;
    BinomialHeapNode<K> reorderedChild = null;
    //reverse order
    for (BinomialHeapNode<K> n : stack) {
      if (reorderedChild == null) {
        reorderedChild = n;
        reorderedChildRoot = n;
      } else {
        reorderedChild.setRightSibling(n);
        reorderedChild = n;
      }
    }
    reorderedChild.setRightSibling(null);
    BinomialHeap<K> childrenHeap = new BinomialHeap<K>();
    childrenHeap.setRoot(reorderedChildRoot);
    return childrenHeap;
  }

  private Pair<BinomialHeapNode<K>, BinomialHeapNode<K>> findMinNode(BinomialHeapNode<K> fromNode) {
    BinomialHeapNode<K> minNode = root;
    BinomialHeapNode<K> lastNode = null;
    BinomialHeapNode<K> left = null;
    BinomialHeapNode<K> current = minNode.getRightSibling();
    while (current != null) {
      if (current.compareDegreeTo(minNode) < 0) {
        left = lastNode;
        minNode = current;
      }
      lastNode = current;
      current = current.getRightSibling();
    }
    return Pair.of(left, minNode);
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
