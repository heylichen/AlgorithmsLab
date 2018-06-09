package algorithms.strings.substring;

import algorithms.strings.alphabet.Alphabet;
import lombok.Getter;
import lombok.Setter;

/**
 * 练习KMP实现，验证对KMP的理解
 */
@Setter
@Getter
public class PracticeKMPSearcher implements SubstringSearcher {

  private Alphabet alphabet;
  private int dfa[][];
  private int patternSize;

  @Override
  public void compile(String pattern) {
    int alphabetSize = alphabet.radix();
    int patternSize = pattern.length();
    this.patternSize = patternSize;
    dfa = new int[alphabetSize][patternSize];
    //init
    int restartState = 0;
    int firstPatternChar = alphabet.toIndex(pattern.charAt(0));
    dfa[firstPatternChar][0] = 1;
    //
    int j = 1;
    int patternChar = 0;
    int previousPatternChar = 0;
    while (j < patternSize) {
      patternChar = alphabet.toIndex(pattern.charAt(j));
      copyColumn(dfa, restartState, j);
      dfa[patternChar][j] = j + 1;

      j++;
      previousPatternChar = alphabet.toIndex(pattern.charAt(j - 1));
      restartState = dfa[previousPatternChar][restartState];
    }
  }

  private void copyColumn(int[][] dfa, int fromColumn, int toColumn) {
    for (int[] row : dfa) {
      row[toColumn] = row[fromColumn];
    }
  }

  @Override
  public int search(String text) {
    int i = 0;
    int j = 0;
    int textLength = text.length();
    while (i < textLength && j < patternSize) {
      j = dfa[alphabet.toIndex(text.charAt(i))][j];
      i++;
    }
    if (j == patternSize) {
      return i - patternSize;
    }
    return -1;
  }
}
