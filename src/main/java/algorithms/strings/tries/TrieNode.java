package algorithms.strings.tries;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrieNode<V> {

  private V value;
  private TrieNode<V>[] next;

  public TrieNode(int r) {
    next = new TrieNode[r];
  }

  public TrieNode<V> next(int index) {
    return next[index];
  }

  public void setNext(int index, TrieNode<V> node) {
    if (index < 0 || index >= next.length) {
      throw new IndexOutOfBoundsException();
    }
    next[index] = node;
  }

  public boolean hasValue() {
    return value != null;
  }

  public boolean isChildrenEmpty() {
    for (TrieNode<V> child : next) {
      if (child != null) {
        return false;
      }
    }
    return true;
  }
}
