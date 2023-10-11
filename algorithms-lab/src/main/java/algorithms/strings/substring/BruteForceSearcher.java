package algorithms.strings.substring;

/**
 * Created by Chen Li on 2018/4/12.
 */
public class BruteForceSearcher implements SubstringSearcher {

  private String pattern;

  @Override
  public void compile(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public int search(String text) {
    int textLength = text.length();
    int patternLength = pattern.length();

    for (int i = 0; i <= textLength - patternLength; i++) {
      int j = 0;
      for (; j < patternLength; ) {
        if (text.charAt(i + j) == pattern.charAt(j)) {
          j++;
        } else {
          break;
        }
      }
      if (j == patternLength) {
        return i;
      }
    }
    return -1;
  }
}
