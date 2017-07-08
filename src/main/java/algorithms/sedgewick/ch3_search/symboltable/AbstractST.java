package algorithms.sedgewick.ch3_search.symboltable;

/**
 * Created by Chen Li on 2017/6/26.
 */
public abstract class AbstractST<K, V> implements ST<K, V> {
  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public boolean contains(K key) {
    return key != null && get(key) != null;
  }
}
