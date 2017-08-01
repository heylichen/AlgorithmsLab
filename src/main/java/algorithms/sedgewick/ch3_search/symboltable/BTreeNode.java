package algorithms.sedgewick.ch3_search.symboltable;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2017/8/1.
 */
@Getter
@Setter
public class BTreeNode<K, V> {
  private K[] keys;
  private V[] values;
  private BTreeNode<K, V>[] children;
  private boolean leaf;

  public BTreeNode() {

  }

  public int getKeyCount() {
    return keys == null ? 0 : keys.length;
  }

}
