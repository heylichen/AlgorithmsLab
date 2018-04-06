package algorithms.sedgewick.strings.sorts;

/**
 * Created by Chen Li on 2018/4/6.
 */
public class StringOperations {

  public int charAt(String str, int d) {
    if (d >= str.length()) {
      return -1;
    } else {
      return str.charAt(d);
    }
  }

}
