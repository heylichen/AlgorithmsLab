package algorithms.search.symboltable;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Stack;

/**
 * Created by Chen Li on 2017/8/19.
 */
@Getter
@Setter
public class BPlusTree<K extends Comparable<K>, V> {
  private BPlusTreeNode<K, V> root;
  private int t;

  public BPlusTree() {
  }

  public BPlusTree(int t) {
    this.t = t;
  }


  public void insert(K key, V value) {
    if (root == null) {
      BPlusTreeNode<K, V> newRoot = new BPlusTreeNode<>(t);
      newRoot.insertIntoLeaf(key, value);
      newRoot.setLeaf(true);
      root = newRoot;
      return;
    }
    Stack<Pair<BPlusTreeNode<K, V>, Integer>> ancestersStack = new Stack<>();
    Pair<BPlusTreeNode<K, V>, Stack<Pair<BPlusTreeNode<K, V>, Integer>>> pair = findTargetNode(key, ancestersStack);
    BPlusTreeNode<K, V> targetLeaf = pair.getLeft();
    Stack<Pair<BPlusTreeNode<K, V>, Integer>> ancesters = pair.getRight();

    targetLeaf.insertIntoLeaf(key, value);
    if (!targetLeaf.isOverflow()) {
      return;
    }
    //too many keys, split and insert into parent
    BPlusTreeNode<K, V> currentNode = targetLeaf;
    Pair<BPlusTreeNode<K, V>, Integer> parentNodeAndOffset = ancestersStack.isEmpty() ? null : ancesters.pop();
    while (parentNodeAndOffset != null && currentNode.isOverflow()) {
      BPlusTreeNode<K, V> parentNode = parentNodeAndOffset.getLeft();
      Integer offset = parentNodeAndOffset.getRight();

      Triple<BPlusTreeNode<K, V>, K, BPlusTreeNode<K, V>> leftAndKeyAndRight = currentNode.splitIfOverflow();
      parentNode.insertIntoInternalNode(offset, leftAndKeyAndRight.getMiddle(), null,
          leftAndKeyAndRight.getLeft(), leftAndKeyAndRight.getRight());
      //next round, go to parent and check
      currentNode = parentNode;
      parentNodeAndOffset = ancesters.isEmpty() ? null : ancesters.pop();
    }

    if (!currentNode.isOverflow()) {
      return;
    }

    BPlusTreeNode<K, V> newRoot = new BPlusTreeNode<>(t);
    Triple<BPlusTreeNode<K, V>, K, BPlusTreeNode<K, V>> leftAndKeyAndRight = currentNode.splitIfOverflow();
    newRoot.insertIntoInternalNode(0, leftAndKeyAndRight.getMiddle(), null,
        leftAndKeyAndRight.getLeft(), leftAndKeyAndRight.getRight());
    root = newRoot;
    return;
  }


  public void delete(K key) {
    Stack<Pair<BPlusTreeNode<K, V>, Integer>> ancestersStack = new Stack<>();
    Pair<BPlusTreeNode<K, V>, Stack<Pair<BPlusTreeNode<K, V>, Integer>>> pair =
        findTargetNode(key, ancestersStack);
    ancestersStack = pair.getRight();
    BPlusTreeNode<K, V> currentNode = pair.getLeft();
    int at = currentNode.rank(key);
    if (!currentNode.isKeyAt(key, at)) {
      return;
    }
    //found parentNode and index
    deleteFromNode(currentNode, key, at, ancestersStack);
  }

  public void deleteFromNode(BPlusTreeNode<K, V> node, K key, int at,
                             Stack<Pair<BPlusTreeNode<K, V>, Integer>> ancestersStack) {
    Pair<BPlusTreeNode<K, V>, Integer> parentAndIndex = ancestersStack.isEmpty() ? null : ancestersStack.pop();
    if (parentAndIndex == null) {
      //parentNode is root
      deleteFromRoot(node, at);
      return;
    }
  }

  private void deleteFromLeaf(BPlusTreeNode<K, V> node, K key, int at,
                              Stack<Pair<BPlusTreeNode<K, V>, Integer>> ancestersStack) {

    node.justDeleteOnLeaf(at);
    if (node.getSaturation() >= 0) {
      return;
    }
    if (ancestersStack == null || ancestersStack.isEmpty()) {
      return;
    }
    //leaf is below min degree
    Pair<BPlusTreeNode<K, V>, Integer> parentAndIndex = ancestersStack.pop();
    BPlusTreeNode<K, V> parent = parentAndIndex.getLeft();
    Integer index = parentAndIndex.getRight();


  }

  private void rebalanceLeaf(BPlusTreeNode<K, V> node, int at,
                              Stack<Pair<BPlusTreeNode<K, V>, Integer>> ancestersStack) {
//    // if sibling has more than enough, borrow
//    canBorrowFromSiblingLeaf();
//    // else merge parent
  }

  private void canBorrowFromSiblingLeaf(BPlusTreeNode<K, V> parentNode, int at, BPlusTreeNode<K, V> node){
    int leftSiblingIndex = at-1;
    if (leftSiblingIndex >= 0) {
      BPlusTreeNode<K, V> leftSibling = parentNode.getChildren()[leftSiblingIndex];
      if (leftSibling.getSaturation() > 0) {
        //borrow
        K movingKey = (K) leftSibling.getKeys()[leftSibling.getSize() - 1];
        leftSibling.decreaseSize(1);

        node.shiftRight(0);
        node.getKeys()[0] = movingKey;
        parentNode.getKeys()[leftSiblingIndex] = movingKey;
        return;
      }
    }

    int childrenSize = parentNode.getChildrenSize();
    int rightSiblingIndex = at + 1;
    if (rightSiblingIndex < childrenSize) {
      BPlusTreeNode<K, V> rightSibling = parentNode.getChildren()[rightSiblingIndex];
      //borrow
      K movingKey = (K) rightSibling.getKeys()[0];
      rightSibling.shiftLeft(1);
      rightSibling.decreaseSize(1);

      node.getKeys()[node.getSize()] = movingKey;
      node.increaseSize(1);
      parentNode.getKeys()[at] = movingKey;
      return;
    }

  }



  public void deleteFromRoot(BPlusTreeNode<K, V> node, int at) {
    int shiftFrom = at + 1;
    if (shiftFrom < node.size()) {
      node.shiftLeft(shiftFrom);
    }
    node.decreaseSize(1);
  }

//
//  public static <K extends Comparable<K>, V> void guardInternalChildNode(BPlusTreeNode<K, V> parentNode, int childIndex, BPlusTreeNode<K, V> childNode) {
//    BPlusTreeNode<K, V> siblingChildNode = null;
//    if (childIndex > 0) {
//      siblingChildNode = parentNode.getChildren()[childIndex - 1];
//      if (!siblingChildNode.isMinDegree()) {
//        K movingKey = (K) parentNode.getKeys()[childIndex - 1];
//        parentNode.getKeys()[childIndex - 1] = siblingChildNode.getKeys()[siblingChildNode.size() - 1];
//        BPlusTreeNode<K, V> movingChild = siblingChildNode.getChildren()[siblingChildNode.size()];
//        siblingChildNode.decreaseSize(1);
//
//        childNode.shiftRight(0);
//        childNode.increaseSize(1);
//        childNode.getChildren()[1] = childNode.getChildren()[0];
//
//        childNode.getKeys()[0] = movingKey;
//        childNode.getChildren()[0] = movingChild;
//        return;
//      }
//    }
//
//    if (childIndex < parentNode.size()) {
//      siblingChildNode = parentNode.getChildren()[childIndex + 1];
//      if (!siblingChildNode.isMinDegree()) {
//        K movingKey = (K) parentNode.getKeys()[childIndex];
//        parentNode.getKeys()[childIndex] = siblingChildNode.getKeys()[0];
//        BPlusTreeNode<K, V> movingChild = siblingChildNode.getChildren()[0];
//        siblingChildNode.shiftLeft(1);
//        siblingChildNode.decreaseSize(1);
//
//        childNode.getKeys()[childNode.getSize()] = movingKey;
//        childNode.getChildren()[childNode.getSize() + 1] = movingChild;
//        childNode.increaseSize(1);
//        return;
//      }
//    }
//
//    //if both sibling parentNode are at minimum degree, merge them
//  }


  public BPlusTreeNode<K, V> getNode(K key) {
    BPlusTreeNode<K, V> currentNode = findTargetNode(key, null).getLeft();
    int rank = currentNode.rank(key);
    if (currentNode.isKeyAt(key, rank)) {
      return currentNode;
    } else {
      return BPlusTreeNode.emptyNode();
    }
  }

  protected Pair<BPlusTreeNode<K, V>, Stack<Pair<BPlusTreeNode<K, V>, Integer>>>
  findTargetNode(K key,
                 Stack<Pair<BPlusTreeNode<K, V>, Integer>> ancestersStack) {
    if (root == null) {
      return Pair.of(BPlusTreeNode.emptyNode(), ancestersStack);
    }
    BPlusTreeNode<K, V> currentNode = root;

    while (!currentNode.isLeaf()) {
      int rank = currentNode.rank(key);
      if (currentNode.isKeyAt(key, rank)) {
        rank = rank + 1;
      }
      if (ancestersStack != null) {
        ancestersStack.push(Pair.of(currentNode, rank));
      }
      currentNode = currentNode.getChildren()[rank];
    }
    return Pair.of(currentNode, ancestersStack);

  }
}
