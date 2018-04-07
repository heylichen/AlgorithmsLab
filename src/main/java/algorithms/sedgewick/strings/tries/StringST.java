package algorithms.sedgewick.strings.tries;

/**
 * Created by Chen Li on 2018/4/7.
 */
public interface StringST<V> {

  void put(String key, V value);

  V get(String key);

  void delete(String key);

  boolean contains(String key);

  boolean isEmpty();

  int size();

  Iterable<String> keys();

  /**
   * the longest key that is a prefx of s
   */
  String longestPrefixOf(String s);

  /**
   * all the keys having s as a prefx
   */
  Iterable<String> keysWithPrefix(String s);

  /**
   * all the keys that match s
   * (where . matches any character)
   */
  Iterable<String> keysThatMatch(String s);
}
