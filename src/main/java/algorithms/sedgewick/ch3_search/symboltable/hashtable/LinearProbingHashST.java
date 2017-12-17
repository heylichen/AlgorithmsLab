package algorithms.sedgewick.ch3_search.symboltable.hashtable;

import algorithms.sedgewick.ch3_search.symboltable.AbstractST;

import java.util.Iterator;

/**
 * Created by Chen Li on 2017/12/17.
 */
public class LinearProbingHashST<K, V> extends AbstractST<K, V> {
  private int N = 0;//number of keys
  private int M; //size of the st
  private K[] keys;
  private V[] values;

  public LinearProbingHashST() {
    this(1000);
  }

  public LinearProbingHashST(int m) {
    this.M = m;
    this.keys = (K[]) new Object[M];
    this.values = (V[]) new Object[M];
  }


  @Override
  public V get(K key) {
    int index = hashToIndex(key);
    K theKey = keys[index];

    while (theKey != null) {
      if (theKey.equals(key)) {
        return values[index];
      }
      index = next(index);
      theKey = keys[index];
    }
    return null;
  }

  @Override
  public void put(K key, V value) {
    if (N >= M / 2) {
      resize(M * 2);
    }
    int index = hashToIndex(key);
    K theKey = keys[index];

    while (theKey != null) {
      if (theKey.equals(key)) {
        break;
      }
      index = next(index);
      theKey = keys[index];
    }

    if (theKey == null) {
      N++;
    }

    keys[index] = key;
    values[index] = value;
  }

  @Override
  public void delete(K key) {
    int index = hashToIndex(key);
    K theKey = keys[index];

    while (theKey != null) {
      if (theKey.equals(key)) {
        break;
      }
      index = next(index);
      theKey = keys[index];
    }

    if (theKey == null) {
      //not found
      return;
    }

    keys[index] = null;
    values[index] = null;

    index = next(index);
    theKey = keys[index];
    while (theKey != null) {
      K moveKey = theKey;
      V moveValue = values[index];

      keys[index] = null;
      values[index] = null;

      N--;
      put(moveKey, moveValue);
      index = next(index);
      theKey = keys[index];
    }
    N--;
    if (N > 0 && N <= M / 8) {
      resize(M / 2);
    }
  }

  @Override
  public int size() {
    return N;
  }

  @Override
  public Iterable<K> keys() {
    return new Iterable<K>() {
      @Override
      public Iterator<K> iterator() {
        return new ProbingIterator();
      }
    };
  }

  private int hashToIndex(K key) {
    return (key.hashCode() & 0x7fffffff) % M;
  }

  private int next(int index) {
    return (index + 1) % M;
  }

  private void resize(int newSize) {
    K[] oldKeys = this.keys;
    V[] oldValues = this.values;
    int oldM = this.M;

    this.keys = (K[]) new Object[newSize];
    this.values = (V[]) new Object[newSize];
    this.N = 0;
    this.M = newSize;
    for (int i = 0; i < oldM; i++) {
      if (oldKeys[i] != null) {
        put(oldKeys[i], oldValues[i]);
      }
    }

  }

  private class ProbingIterator implements Iterator<K> {
    private int accessed = 0;
    private int i = -1;

    @Override
    public K next() {
      i++;
      while (keys[i] == null && i < M) {
        i = LinearProbingHashST.this.next(i);
      }
      accessed++;
      return keys[i];
    }

    @Override
    public boolean hasNext() {
      return accessed < N;
    }
  }
}
