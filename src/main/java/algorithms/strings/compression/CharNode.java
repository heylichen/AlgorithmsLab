package algorithms.strings.compression;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/6/18.
 */
@Getter
@Setter
public class CharNode implements Comparable<CharNode> {

  private CharNode left;
  private CharNode right;
  private char character;
  private int frequency;
  private boolean leaf;

  public CharNode(CharNode left, CharNode right, char character, int frequency, boolean leaf) {
    this.left = left;
    this.right = right;
    this.character = character;
    this.frequency = frequency;
    this.leaf = leaf;
  }

  private void incrementFreq(int frequency) {
    this.frequency += frequency;
  }

  @Override
  public int compareTo(CharNode o) {
    if (o == null) {
      return 1;
    }
    if (this.frequency == o.frequency) {
      return 0;
    } else if (this.frequency > o.frequency) {
      return 1;
    } else {
      return -1;
    }
  }
}
