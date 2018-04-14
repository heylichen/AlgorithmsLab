package algorithms.sedgewick.strings.substring;

/**
 * Created by Chen Li on 2018/4/12.
 * Knuth-Morris-Pratt algorithm for substring search
 */
public class KMPSubstringSearcher implements SubstringSearcher {

  private int radix = 26;

  public int search(String pattern, String text) {
    int[][] dfa = constructDFA(pattern);
    int j = 0;
    int patternLength = pattern.length();
    for (int i = 0; i < text.length(); i++) {
      char ch = text.charAt(i);
      j = dfa[toIndex(ch)][j];
      if (j == patternLength) {
        return i - patternLength + 1;
      }
    }
    return -1;
  }

  /**
   * a deterministic finite-state automaton (DFA)
   */
  int[][] constructDFA(String pattern) {
    int patternLength = pattern.length();
    int[][] dfa = new int[radix][patternLength];
    int x = 0;
    dfa[toIndex(pattern.charAt(0))][0] = 1;
    for (int i = 1; i < patternLength; i++) {
      char patternChar = pattern.charAt(i);
      for (int j = 0; j < radix; j++) {
        char ch = toChar(j);
        if (ch == patternChar) {
          dfa[j][i] = i + 1;
        } else {
          dfa[j][i] = dfa[j][x];
        }
      }
      int patternCharIndex = toIndex(patternChar);
      x = dfa[patternCharIndex][x];
    }
    return dfa;
  }

  char toChar(int index) {
    if (index < 0 || index >= radix) {
      throw new IllegalArgumentException("index out of bound!");
    }
    return (char) ('a' + index);
  }

  int toIndex(char ch) {
    return ch - 'a';
  }
}
