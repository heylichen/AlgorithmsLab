package algorithms.sedgewick.ch3_search.symboltable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * Created by Chen Li on 2017/6/19.
 * use array as it's internal data structure.
 * search using binary search.
 * auto resizing array when needed.
 */
public class BinarySearchST<K extends Comparable<K>, V> extends AbstractOrderedST<K, V> {
  public static final String ERR_MSG_NULL_KEY_ILLEGAL = "argument key must not be null!";
  private Logger logger = LoggerFactory.getLogger(BinarySearchST.class);
  private K[] keys;
  private V[] values;
  private int size;
  public static final int MIN_CAPACITY = 12;
  private boolean doCount = false;
  private int ops = 0;

  public BinarySearchST() {
    keys = (K[]) new Comparable[MIN_CAPACITY];
    values = (V[]) new Object[MIN_CAPACITY];
    size = 0;
  }

  @Override
  public V get(K key) {
    doCount = true;
    ops = 0;

    rejectNullKeyArgument(key);
    int index = rank(key);

    addDataValue(ops);
    ops = 0;
    doCount = false;

    if (index < size() && keys[index].compareTo(key) == 0) {
      return values[index];
    } else {
      return null;
    }
  }

  @Override
  public void put(K key, V value) {
    doCount = true;
    ops = 0;

    rejectNullKeyArgument(key);
    resize();
    int index = rank(key);
    if (index < size && keys[index].compareTo(key) == 0) {
      ops++;
      values[index] = value;
    } else {
      int tail = size - 1;
      while (tail >= 0 && tail >= index) {
        keys[tail + 1] = keys[tail];
        values[tail + 1] = values[tail];
        tail--;
        ops += 2;
      }
      keys[index] = key;
      values[index] = value;
      ops += 2;
      size++;
    }

    addDataValue(ops);
    ops = 0;
    doCount = false;
  }

  protected void addDataValue(double value) {
    if (visualAccumulator != null) {
      visualAccumulator.addDataValue(value);
    }
  }

  @Override
  public void delete(K key) {
    rejectNullKeyArgument(key);
    int index = rank(key);
    if (index < size && keys[index].compareTo(key) == 0) {
      //found key
      int i = index;
      while (i < size - 1) {
        keys[i] = keys[i + 1];
        values[i] = values[i + 1];
        i++;
      }
      size--;
      resize();
    }
  }

  private void resize() {
    int capacity = keys.length;
    if (size < capacity / 4) {
      ops += size;
      int shrinkSize = Math.max(capacity / 2, MIN_CAPACITY);
      resize(shrinkSize);
    } else if (size > capacity * 3 / 4) {
      ops += size;
      long start = System.currentTimeMillis();
      resize(capacity * 2);
      long end = System.currentTimeMillis();
      logger.info("size:{}, resizing from {} to {}, using {} ms", size, capacity, capacity * 2, (end - start));
    }
  }

  private void resize(int capacity) {
    K[] newKeys = (K[]) new Comparable[capacity];
    System.arraycopy(keys, 0, newKeys, 0, size);
    keys = newKeys;

    V[] newValues = (V[]) new Object[capacity];
    System.arraycopy(values, 0, newValues, 0, size);
    values = newValues;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public K min() {
    if (size > 0) {
      return keys[0];
    }
    return null;
  }

  @Override
  public K max() {
    if (size - 1 >= 0) {
      return keys[size - 1];
    }
    return null;
  }

  @Override
  public K floor(K key) {
    rejectNullKeyArgument(key);
    int rank = rank(key);
    if (rank < size && keys[rank].compareTo(key) == 0) {
      return keys[rank];
    }
    if (rank - 1 >= 0) {
      return keys[rank - 1];
    }
    return null;
  }

  @Override
  public K ceiling(K key) {
    rejectNullKeyArgument(key);
    int rank = rank(key);
    if (rank < size()) {
      return keys[rank];
    }
    return null;
  }

  private void rejectNullKeyArgument(K key) {
    if (key == null) {
      throw new IllegalArgumentException(ERR_MSG_NULL_KEY_ILLEGAL);
    }
  }

  /**
   * @param key
   * @return the number of keys less than the specified key.
   * the range is [0, size]
   */
  @Override
  public int rank(K key) {
    rejectNullKeyArgument(key);

    int left = 0;
    int right = size - 1;
    while (left <= right) {
      int middle = left + (right - left) / 2;
      int compare = key.compareTo(keys[middle]);
      ops++;
      if (compare == 0) {
        return middle;
      } else if (compare < 0) {
        right = middle - 1;
      } else {
        left = middle + 1;
      }
    }
    return left;
  }

  @Override
  public K select(int rank) {
    return keys[rank];
  }

  @Override
  public Iterable<K> keys(K low, K high) {
    rejectNullKeyArgument(low);
    rejectNullKeyArgument(high);
    if (size == 0) {
      return EMPTY_KEYS_ITERABLE;
    }
    int lowRank = rank(low);
    int highRank = rank(high);
    if (lowRank == size || lowRank > highRank) {
      return EMPTY_KEYS_ITERABLE;
    }
    int fromIndex = lowRank;

    if (highRank == size) {
      return new KeyIterable(fromIndex, size - 1);
    }
    if (high.equals(keys[highRank])) {
      return new KeyIterable(fromIndex, highRank);
    } else {
      if (highRank == 0) {
        return EMPTY_KEYS_ITERABLE;
      } else {
        return new KeyIterable(fromIndex, highRank - 1);
      }
    }
  }


  private class KeyIterable implements Iterable<K> {
    private int from;
    private int to;

    public KeyIterable(int from, int to) {
      this.from = from;
      this.to = to;
    }

    @Override
    public Iterator<K> iterator() {
      return new KeysIterator(from, to);
    }
  }

  private class KeysIterator implements Iterator<K> {
    private int from;
    private int to;

    public KeysIterator(int from, int to) {
      this.from = from;
      this.to = to;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
      return from <= to;
    }

    @Override
    public K next() {
      if (!hasNext()) {
        throw new IllegalStateException("no more elements!");
      }
      K key = keys[from++];
      return key;
    }
  }
}
