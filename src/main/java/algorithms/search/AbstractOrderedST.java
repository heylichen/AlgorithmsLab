package algorithms.search;

/**
 * Created by Chen Li on 2017/6/13.
 */
public abstract class AbstractOrderedST<K extends Comparable<K>, V> extends AbstractST<K, V> implements OrderedST<K, V> {

  @Override
  public void deleteMin() {
    delete(min());
  }

  @Override
  public void deleteMax() {
    delete(max());
  }

  @Override
  public int size(K low, K high) {
    if (low.compareTo(high) > 0) {
      return 0;
    } else if (contains(high)) {
      return rank(high) - rank(low) + 1;
    } else {
      return rank(high) - rank(low);
    }
  }

  @Override
  public Iterable<K> keys() {
    if (isEmpty()) {
      return EMPTY_KEYS_ITERABLE;
    }
    return keys(min(), max());
  }
}
