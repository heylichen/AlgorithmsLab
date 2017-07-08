package algorithms.sedgewick.ch3_search.symboltable;

import java.util.Iterator;

/**
 * Created by Chen Li on 2017/6/13.
 * symbol table API
 */
public interface ST<K, V> {

  V get(K key);

  boolean contains(K key);

  void put(K key, V value);

  void delete(K key);

  boolean isEmpty();

  int size();

  Iterable<K> keys();

  Iterable EMPTY_KEYS_ITERABLE = new Iterable() {
    @Override
    public Iterator iterator() {
      return new Iterator() {
        @Override
        public boolean hasNext() {
          return false;
        }

        @Override
        public Object next() {
          throw new UnsupportedOperationException("has no more elements!");
        }
      };
    }
  };
}
