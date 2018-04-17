package algorithms.sedgewick.strings.substring;

import algorithms.sedgewick.strings.Alphabet;

/**
 * Created by Chen Li on 2018/4/17.
 */
public class RabinKarpSearcher implements SubstringSearcher {

  private Alphabet alphabet;
  private String pattern;
  private long prime;
  private long radix;
  private long patternHash;


  public RabinKarpSearcher(Alphabet alphabet, long prime) {
    this.alphabet = alphabet;
    this.prime = prime;
    this.radix = alphabet.radix();
  }

  @Override
  public void compile(String pattern) {
    this.pattern = pattern;
    this.patternHash = hash(pattern, pattern.length());
  }

  private long hash(String pattern, int length) {
    long result = 0;
    for (int i = 0; i < length; i++) {
      result = result * radix % prime + indexAt(pattern, i) % prime;
    }
    return result;
  }


  @Override
  public int search(String text) {
    int patternLength = pattern.length();
    if (text == null || text.length() < patternLength) {
      return -1;
    }

    long textHash = hash(text, patternLength);
    if (textHash == patternHash) {
      return 0;
    }
    long f = (int) Math.pow(radix, patternLength - 1);
    f = f % prime;
    long meta = f;
    for (int i = patternLength; i < text.length(); i++) {
      int oldDigit = indexAt(text, i - patternLength);
      //to avoid overflow
      textHash = (textHash + prime - (oldDigit * meta) % prime) % prime;
      textHash = (textHash * radix) % prime + indexAt(text, i) % prime;
      if (textHash == patternHash) {
        return i - patternLength + 1;
      }
    }
    return -1;
  }

  private int indexAt(String string, int index) {
    return alphabet.toIndex(string.charAt(index));
  }
}
