package algorithms.strings.substring;

import algorithms.strings.alphabet.Alphabet;

/**
 * Created by Chen Li on 2018/4/12.
 * Knuth-Morris-Pratt algorithm for substring search
 */
public class KMPSearcher implements SubstringSearcher {

  private Alphabet alphabet;
  private String pattern;
  private int[][] dfa;

  public KMPSearcher(Alphabet alphabet) {
    this.alphabet = alphabet;
  }

  @Override
  public void compile(String pattern) {
    this.pattern = pattern;
    this.dfa = constructDFA(pattern);
  }

  @Override
  public int search(String text) {
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
    int radix = getRadix();
    int[][] dfa = new int[radix][patternLength];
    int x = 0;//restart state
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
    return alphabet.toChar(index);
  }

  int toIndex(char ch) {
    return alphabet.toIndex(ch);
  }

  int getRadix() {
    return alphabet.radix();
  }
}
