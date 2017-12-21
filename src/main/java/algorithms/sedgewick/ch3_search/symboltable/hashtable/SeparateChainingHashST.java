package algorithms.sedgewick.ch3_search.symboltable.hashtable;

import algorithms.sedgewick.ch3_search.symboltable.AbstractST;
import algorithms.sedgewick.ch3_search.symboltable.SequentialSearchST;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Chen Li on 2017/10/30.
 */
public class SeparateChainingHashST<K, V> extends AbstractST<K, V> {
  public static final int DEFAULT_TABLE_SIZE = 997;
  @Getter
  @Setter
  private SequentialSearchST<K, V>[] stArray;
  private int tableSize;

  public SeparateChainingHashST() {
    this(997);
  }

  public SeparateChainingHashST(int tableSize) {
    this.tableSize = tableSize;
    stArray = new SequentialSearchST[tableSize];
    for (int i = 0; i < tableSize; i++) {
      stArray[i] = new SequentialSearchST();
    }
  }

  public void init(){
    if (this.visualAccumulator != null) {
      for (int i = 0; i < tableSize; i++) {
        stArray[i].setVisualAccumulator(this.visualAccumulator);
      }
    }
  }

  private int hash(K key) {
    return (key.hashCode() & 0x7fffffff) % tableSize;
  }

  @Override
  public V get(K key) {
    SequentialSearchST<K, V> st = stArray[hash(key)];
    return st.get(key);
  }

  @Override
  public void put(K key, V value) {
    SequentialSearchST<K, V> st = stArray[hash(key)];
    st.put(key, value);
  }

  @Override
  public void delete(K key) {
    SequentialSearchST<K, V> st = stArray[hash(key)];
    st.delete(key);
  }

  @Override
  public int size() {
    int count = 0;
    for (SequentialSearchST<K, V> kvSequentialSearchST : stArray) {
      count += kvSequentialSearchST.size();
    }
    return count;
  }

  @Override
  public Iterable<K> keys() {
    return new Iterable<K>() {
      @Override
      public Iterator<K> iterator() {
        return new SeparateChainingHashIterator();
      }
    };
  }

  private class SeparateChainingHashIterator<K> implements Iterator<K> {
    private Iterator<K>[] iterators;
    private int i = 0;

    public SeparateChainingHashIterator() {
      iterators = (Iterator<K>[]) new Iterator[tableSize];
      SequentialSearchST<K, V>[] arr = (SequentialSearchST<K, V>[]) stArray;
      for (int j = 0; j < arr.length; j++) {
        Iterable<K> iterable = arr[j].keys();
        iterators[j] = iterable.iterator();
      }
    }

    @Override
    public K next() {
      return iterators[i].next();
    }

    @Override
    public boolean hasNext() {
      while (i < tableSize) {
        if (iterators[i].hasNext()) {
          return true;
        } else {
          i++;
        }
      }
      return false;
    }
  }

  public Map<Integer, Integer> sizeDistribute() {
    Map<Integer, Integer> distributeMap = new HashMap<>();
    for (int i = 0; i < tableSize; i++) {
      Integer size = stArray[i].size();

      Integer oldSize = distributeMap.get(size);
      if (oldSize == null) {
        oldSize = 0;
      }
      distributeMap.put(size, oldSize + 1);
    }
    return distributeMap;
  }
}
