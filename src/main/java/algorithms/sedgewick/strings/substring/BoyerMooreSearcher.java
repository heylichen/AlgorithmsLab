package algorithms.sedgewick.strings.substring;

import algorithms.sedgewick.strings.Alphabet;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Chen Li on 2018/4/15.
 */
@Slf4j
public class BoyerMooreSearcher implements SubstringSearcher {

  private String pattern;
  private GoodSuffixHeuristic goodSuffixHeuristic;
  private BadCharacterHeuristic badCharacterHeuristic;

  public BoyerMooreSearcher(Alphabet alphabet) {
    this.goodSuffixHeuristic = new GoodSuffixHeuristic();
    this.badCharacterHeuristic = new BadCharacterHeuristic(alphabet);
  }

  @Override
  public void compile(String pattern) {
    this.pattern = pattern;
    badCharacterHeuristic.compile(pattern);
    goodSuffixHeuristic.compile(pattern);
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
          i += shift(j, textChar);
          break;
        }
      }
      if (j < 0) {
        return i;
      }
    }
    return -1;
  }

  private int shift(int j, char textChar) {
    int shiftByGoodSuffixHeuristic = goodSuffixHeuristic.getShift(j);
    int shiftByBadCharacterHeuristic = badCharacterHeuristic.getShift(j, textChar);

    if (shiftByGoodSuffixHeuristic > shiftByBadCharacterHeuristic) {
      log.debug("good shift {} bad character shift {} , j={}",
                shiftByGoodSuffixHeuristic, shiftByBadCharacterHeuristic, j);
      return shiftByGoodSuffixHeuristic;
    } else {
      return shiftByBadCharacterHeuristic;
    }
  }
}
