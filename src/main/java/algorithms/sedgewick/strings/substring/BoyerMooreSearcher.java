package algorithms.sedgewick.strings.substring;

import algorithms.sedgewick.strings.Alphabet;

/**
 * Created by Chen Li on 2018/4/15.
 */
public class BoyerMooreSearcher implements SubstringSearcher {

  private Alphabet alphabet;
  private String pattern;
  private int[] right;

  public BoyerMooreSearcher(Alphabet alphabet) {
    this.alphabet = alphabet;
  }

  @Override
  public void compile(String pattern) {
    this.pattern = pattern;
    this.right = constructRight(pattern, alphabet);
  }

  @Override
  public int search(String text) {
    int patternRight = pattern.length() - 1;
    int textRight = text.length() - pattern.length();
    for (int i = 0; i <= textRight; ) {
      int j = patternRight;
      for (; j >= 0; ) {
        char textChar = text.charAt(i + j);
        char patternChar = pattern.charAt(j);
        if (textChar == patternChar) {
          j--;
        } else {
          int textCharIndex = alphabet.toIndex(textChar);
          int rightMostMatched = right[textCharIndex];
          if (j - rightMostMatched <= 0) {
            i = i + 1;
          } else {
            i = i + j - rightMostMatched;
          }
          break;
        }
      }
      if (j < 0) {
        return i;
      }
    }
    return -1;
  }

  private int[] constructRight(String pattern, Alphabet alphabet) {
    int radix = alphabet.radix();
    int[] right = new int[radix];
    for (int i = 0; i < radix; i++) {
      right[i] = -1;
    }
    for (int i = 0; i < pattern.length(); i++) {
      char ch = pattern.charAt(i);
      int index = alphabet.toIndex(ch);
      right[index] = i;
    }
    return right;
  }
}
