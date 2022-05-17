package algorithms.kd;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Entry<T> {
  private Point key;
  private T value;

  public Entry(Point key, T value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public String toString() {
    return key.toString() + ":" + value;
  }
}
