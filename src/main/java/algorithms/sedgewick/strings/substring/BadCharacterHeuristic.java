package algorithms.sedgewick.strings.substring;

import algorithms.sedgewick.strings.Alphabet;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/4/22.
 */
@Getter
@Setter
class BadCharacterHeuristic {

  private Alphabet alphabet;
  private int[] right;

  public BadCharacterHeuristic(Alphabet alphabet) {
    this.alphabet = alphabet;
  }

  public void compile(String pattern) {
    this.right = constructRight(pattern, alphabet);
  }

  public int getShift(int indexInPattern, char textChar) {
    int textCharIndex = alphabet.toIndex(textChar);
    int rightMostMatched = right[textCharIndex];

    if (indexInPattern - rightMostMatched <= 0) {
      return 1;
    } else {
      return indexInPattern - rightMostMatched;
    }
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
