package algorithms.sedgewick.ch5_strings.sorts;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2017/12/21.
 */
@Getter
@Setter
public class IndexedEntry< I> {
  private int key;
  private I item;

  public IndexedEntry(int key, I item) {
    this.key = key;
    this.item = item;
  }
}
