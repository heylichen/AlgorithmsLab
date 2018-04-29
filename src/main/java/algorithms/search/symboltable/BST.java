package algorithms.search.symboltable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import algorithms.search.AbstractOrderedST;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2017/6/28.
 * binary search tree implimentation
 */
@Getter
@Setter
public class BST<K extends Comparable<K>, V> extends AbstractOrderedST<K, V> {
  private Node<K, V> root;
  public static final String NODE_ITERATE_OPERATION_ON_NODE = "ON_NODE";
  public static final String NODE_ITERATE_OPERATION_ON_TREE = "ON_TREE";

  @Override
  public V get(K key) {
    Node<K, V> found = findNode(root, key);
    return found == null ? null : found.getValue();
  }

  private Node<K, V> findNode(Node<K, V> root, K key) {
    Node<K, V> current = root;
    Node<K, V> found = null;
    int compares = 0;
    while (current != null) {
      int compare = key.compareTo(current.getKey());
      compares++;
      if (compare == 0) {
        found = current;
        break;
      } else if (compare > 0) {
        current = current.getRight();
      } else {
        current = current.getLeft();
      }
    }
    addDataValue(compares);
    return found;
  }


  @Override
  public void put(K key, V value) {
    if (root == null) {
      root = new Node(key, value, 1);
      addDataValue(0);
      return;
    }
    int costs = 0;

    Stack<Node<K, V>> stack = new Stack<>();
    Node<K, V> current = root;
    Node<K, V> parent = null;
    int compare = 0;
    while (current != null) {
      stack.push(current);
      compare = key.compareTo(current.getKey());
      costs++;

      if (compare == 0) {
        current.setValue(value);
        addDataValue(costs);
        return;
      } else if (compare < 0) {
        parent = current;
        current = current.getLeft();
      } else {
        parent = current;
        current = current.getRight();
      }
    }
    current = new Node(key, value, 1);
    if (compare < 0) {
      parent.setLeft(current);
    } else {
      parent.setRight(current);
    }
    //recalculate
    while (!stack.isEmpty()) {
      Node node = stack.pop();
      node.setCount(size(node.getLeft()) + size(node.getRight()) + 1);
      costs++;
    }

    this.addDataValue(costs);
  }

  @Override
  public K min() {
    if (isEmpty()) {
      return null;
    }
    return minNode(root).getKey();
  }

  private Node<K, V> minNode(Node<K, V> current) {
    while (current.getLeft() != null) {
      current = current.getLeft();
    }
    return current;
  }

  @Override
  public K max() {
    if (isEmpty()) {
      return null;
    }
    Node<K, V> current = root;
    while (current.getRight() != null) {
      current = current.getRight();
    }
    return current.getKey();
  }

  @Override
  public K floor(K key) {
    if (isEmpty()) {
      return null;
    }
    Node<K, V> current = root;
    Node<K, V> lastSmallerNode = null;
    while (current != null) {
      int compare = current.getKey().compareTo(key);
      if (compare > 0) {
        current = current.getLeft();
      } else if (compare < 0) {
        lastSmallerNode = current;
        current = current.getRight();
      } else {
        lastSmallerNode = current;
        break;
      }
    }

    if (lastSmallerNode == null) {
      return null;
    } else {
      return lastSmallerNode.getKey();
    }
  }

  @Override
  public K ceiling(K key) {
    if (isEmpty()) {
      return null;
    }
    Node<K, V> current = root;
    Node<K, V> lastSmallerNode = null;
    while (current != null) {
      int compare = current.getKey().compareTo(key);
      if (compare < 0) {
        current = current.getRight();
      } else if (compare > 0) {
        lastSmallerNode = current;
        current = current.getLeft();
      } else {
        lastSmallerNode = current;
        break;
      }
    }

    if (lastSmallerNode == null) {
      return null;
    } else {
      return lastSmallerNode.getKey();
    }
  }

  @Override
  public int rank(K key) {
    if (isEmpty()) {
      return 0;
    }
    Node<K, V> current = root;
    int rank = 0;
    while (current != null) {
      int compare = current.getKey().compareTo(key);
      int leftCount = size(current.getLeft());
      if (compare == 0) {
        rank += leftCount;
        break;
      } else if (compare > 0) {
        current = current.getLeft();
      } else {
        rank += leftCount + 1;
        current = current.getRight();
      }
    }
    return rank;
  }

  @Override
  public K select(int rank) {
    if (isEmpty()) {
      return null;
    }
    Node<K, V> current = root;
    int targetRank = rank;
    Node<K, V> found = null;
    while (current != null) {
      int leftCount = size(current.getLeft());
      if (leftCount == targetRank) {
        found = current;
        break;
      } else if (leftCount > targetRank) {
        current = current.getLeft();
      } else {
        targetRank = targetRank - 1 - leftCount;
        current = current.getRight();
      }
    }
    if (found == null) {
      return null;
    } else {
      return found.getKey();
    }
  }

  @Override
  public Iterable<K> keys(K low, K high) {
    if (isEmpty()) {
      return EMPTY_KEYS_ITERABLE;
    }
    if (low == null) {
      low = min();
    }
    if (high == null) {
      high = max();
    }
    if (low.compareTo(high) > 0) {
      return EMPTY_KEYS_ITERABLE;
    }
    Node<K, V> current = root;
    Stack<Pair<String, Node<K, V>>> stack = new Stack<>();
    List<K> values = new ArrayList<>();

    while (current != null) {
      if (current.getLeft() != null) {
        if (current.getRight() != null) {
          stack.push(Pair.of(NODE_ITERATE_OPERATION_ON_TREE, current.getRight()));
        }
        stack.push(Pair.of(NODE_ITERATE_OPERATION_ON_NODE, current));
        current = current.getLeft();
        continue;
      }
      values = processIfInRange(current.getKey(), low, high, values);
      //right
      if (current.getRight() != null) {
        current = current.getRight();
        continue;
      }
      if (stack.isEmpty()) {
        break;
      }
      //no right but stack not empty, pop and process
      Pair<String, Node<K, V>> pair = stack.pop();
      while (pair.getLeft().equals(NODE_ITERATE_OPERATION_ON_NODE)) {
        values = processIfInRange(pair.getRight().getKey(), low, high, values);
        if (stack.isEmpty()) {
          break;
        } else {
          pair = stack.pop();
        }
      }
      if (pair.getLeft().equals(NODE_ITERATE_OPERATION_ON_TREE)) {
        current = pair.getRight();
      } else {
        break;
      }
    }
    return values;
  }

  private List<K> processIfInRange(K key, K low, K high, List<K> values) {
    if (key.compareTo(low) >= 0 && key.compareTo(high) <= 0) {
      values.add(key);
    }
    return values;
  }


  /**
   * An answer to
   * this dilemma, first proposed by T. Hibbard in 1962,
   * is to delete a node x by replacing it with its successor.
   *
   * @param key
   */
  @Override
  public void delete(K key) {
    //find the node
    Stack<Node<K, V>> ancestorStack = new Stack<>();
    Pair<Node<K, V>, Stack<Node<K, V>>> foundPair = findNode(root, key, ancestorStack);
    Node<K, V> found = foundPair.getLeft();
    ancestorStack = foundPair.getRight();

    if (found == null) {
      //not contains the key
      return;
    }

    if (found.getLeft() == null || found.getRight() == null) {
      deleteNodeWithZeroOrOneChild(found, ancestorStack);
      return;
    }
    //has both left and right, replace current node with it's successor
    //find the successor
    Node<K, V> successorNode = deleteSuccessor(found);
    replaceNode(found, successorNode);
    boolean asLeft = isLeftChildOfParent(found, ancestorStack.peek());
    setParentAndCalculateNodesCount(successorNode, asLeft, ancestorStack);
  }

  private Node<K, V> deleteSuccessor(Node<K, V> node) {
    Stack<Node<K, V>> subAncestorStack = new Stack<>();
    subAncestorStack.push(node);
    Pair<Node<K, V>, Stack<Node<K, V>>> successorPair = minNode(node.getRight(), subAncestorStack);
    Node<K, V> successorNode = successorPair.getLeft();
    deleteNodeWithZeroOrOneChild(successorNode, subAncestorStack);
    return successorNode;
  }

  private Pair<Node<K, V>, Stack<Node<K, V>>> minNode(Node<K, V> current, Stack<Node<K, V>> ancestorStack) {
    while (current.getLeft() != null) {
      ancestorStack.push(current);
      current = current.getLeft();
    }
    return Pair.of(current, ancestorStack);
  }

  private void replaceNode(Node<K, V> replaced, Node<K, V> byNode) {
    byNode.setLeft(replaced.getLeft());
    byNode.setRight(replaced.getRight());
    calculateNodeCount(byNode);
  }

  /**
   * @param root
   * @param key
   * @param ancestorStack
   * @return Pair,
   * left: RedBlackNode if found, null if not found
   * right: the ancestor nodes of the found node.
   */
  private Pair<Node<K, V>, Stack<Node<K, V>>> findNode(Node<K, V> root, K key, Stack<Node<K, V>> ancestorStack) {
    Node<K, V> current = root;
    int compare = -2;
    while (current != null) {
      compare = key.compareTo(current.getKey());
      if (compare == 0) {
        break;
      } else if (compare > 0) {
        ancestorStack.push(current);
        current = current.getRight();
      } else {
        ancestorStack.push(current);
        current = current.getLeft();
      }
    }
    return Pair.of(current, ancestorStack);
  }

  private void deleteNodeWithZeroOrOneChild(Node<K, V> toDelete, Stack<Node<K, V>> ancestersStack) {
    Node<K, V> childNode = toDelete.getLeft() != null ? toDelete.getLeft() : toDelete.getRight();
    boolean asLeft = false;
    if (!ancestersStack.isEmpty()) {
      asLeft = isLeftChildOfParent(toDelete, ancestersStack.peek());
    }
    setParentAndCalculateNodesCount(childNode, asLeft, ancestersStack);
  }

  private boolean isLeftChildOfParent(Node<K, V> currentNode, Node<K, V> parent) {
    return parent != null && parent.getLeft() == currentNode;
  }

  private void setParentAndCalculateNodesCount(Node<K, V> node, boolean asLeft, Stack<Node<K, V>> ancestorsStack) {
    if (ancestorsStack == null || ancestorsStack.isEmpty()) {
      root = node;
      return;
    }
    //set the parent
    Node<K, V> parent = ancestorsStack.peek();
    if (asLeft) {
      parent.setLeft(node);
    } else {
      parent.setRight(node);
    }
    //fix node count up to root
    Node<K, V> current = null;
    while (!ancestorsStack.isEmpty()) {
      current = ancestorsStack.pop();
      calculateNodeCount(current);
    }
  }

  private void calculateNodeCount(Node<K, V> current) {
    if (current != null) {
      current.setCount(1 + size(current.getLeft()) + size(current.getRight()));
    }
  }

  @Override
  public int size() {
    return size(root);
  }

  protected int size(Node node) {
    return node == null ? 0 : node.getCount();
  }

  public Node<K, V> getRoot() {
    return root;
  }

  @Override
  public String toString() {
    return JSON.toJSONString(getRoot());
  }

  public static void main(String[] args) {
    Random rand = new Random();

    BST<Integer, Integer> bst = new BST<>();
    for (int i = 0; i < 256; i++) {
      Integer v = rand.nextInt(1000) + 1;
      bst.put(v, v);
    }

    System.out.println(JSON.toJSONString(bst.getRoot()));
  }
}
