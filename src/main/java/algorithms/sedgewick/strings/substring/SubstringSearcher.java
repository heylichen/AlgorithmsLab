package algorithms.sedgewick.strings.substring;

/**
 * Created by Chen Li on 2018/4/12.
 */
public interface SubstringSearcher {

  void compile(String pattern);

  int search(String text);
}
