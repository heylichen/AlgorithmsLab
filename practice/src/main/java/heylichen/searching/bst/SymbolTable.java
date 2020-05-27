package heylichen.searching.bst;

import java.util.Set;

/**
 * @author lichen
 * @date 2020/5/27 15:32
 * @desc
 */
public interface SymbolTable<K extends Comparable<K>, V> {
    V get(K key);

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    void put(K key, V value);

    Set<K> keySet();

    void deleteMin();

    void deleteMax();

    void delete(K key);

    //---------------------------order operations -------------
    K min();

    K max();

    //largest key less than or equal to key
    K floor(K key);

    K ceiling(K key);

    //number of keys less than key
    int rank(K key);

    //key of rank k
    K select(int rank);
}
