package algorithms.strings.substring;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/4/22.
 */
@Getter
@Setter
class GoodSuffixHeuristic {

  private int[] shift;
  private int[] borderPosition;

  public void compile(String pattern) {
    int patternLength = pattern.length();
    shift = new int[patternLength + 1];
    borderPosition = new int[patternLength + 1];

    int i = patternLength;
    int j = patternLength + 1;
    borderPosition[i] = j;
    while (i > 0) {
      while (j <= patternLength && pattern.charAt(i - 1) != pattern.charAt(j - 1)) {
        if (shift[j] == 0) {
          shift[j] = j - i;
        }
        j = borderPosition[j];
      }
      i--;
      j--;
      borderPosition[i] = j;
    }

    //for case 2
    j = borderPosition[0];
    for (i = 0; i <= patternLength; i++) {
      if (shift[i] == 0) {
        shift[i] = j - i;
      }
      if (i == j) {
        j = borderPosition[i];
      }
    }
  }

  public int getShift(int indexInPattern) {
    return shift[indexInPattern];
  }
}
