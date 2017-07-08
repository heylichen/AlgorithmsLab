package algorithms.sedgewick.ch3_search.symboltable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node<K extends Comparable<K>, V> {
  private K key;
  private V value;
  private int count;
  private Node left = null;
  private Node right = null;

  public Node(K key, V value, int count) {
    this.key = key;
    this.value = value;
    this.count = count;
  }
}