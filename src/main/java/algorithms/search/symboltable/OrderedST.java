package algorithms.search.symboltable;

/**
 * Created by Chen Li on 2017/6/13.
 */
public interface OrderedST<K extends Comparable<K>, V> extends ST<K, V> {
  K min();

  K max();

  K floor(K key);

  K ceiling(K key);

  int rank(K key);

  K select(int rank);

  void deleteMin();

  void deleteMax();

  int size(K low, K high);

  Iterable<K> keys(K low, K high);
}
