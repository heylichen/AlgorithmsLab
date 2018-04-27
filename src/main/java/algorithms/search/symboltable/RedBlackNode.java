package algorithms.search.symboltable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedBlackNode<K extends Comparable<K>, V> extends Node<K,V> {
  public static final byte RED = 1;
  public static final byte BLACK = 2;

  private RedBlackNode left;
  private RedBlackNode right;
  private byte color;

  public RedBlackNode(K key, V value, int count, byte color) {
    super();
    this.setKey(key);
    this.setValue(value);
    this.setCount(count);
    this.color = color;
  }

}