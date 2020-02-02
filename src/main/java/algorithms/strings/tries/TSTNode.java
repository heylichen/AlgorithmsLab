package algorithms.strings.tries;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TSTNode<V> {

  private V value;
  private char character;
  private TSTNode<V> less;
  private TSTNode<V> equal;
  private TSTNode<V> greater;

  public TSTNode(char character) {
    this.character = character;
  }

  public boolean hasValue() {
    return value != null;
  }
}
