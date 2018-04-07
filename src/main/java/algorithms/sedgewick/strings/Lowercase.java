package algorithms.sedgewick.strings;

/**
 * Created by Chen Li on 2018/4/7.
 */
public class Lowercase extends AbstractAlphabet {

  public static final char A = 'a';
  public static final char Z = 'z';

  @Override
  public char toChar(int index) {
    if (index >= 0 && index <= 26) {
      return (char) (index + A);
    }
    throw new IllegalArgumentException(index + " is not a valid index in this alphabet");
  }

  @Override
  public int toIndex(char c) {
    if (c >= A && c <= Z) {
      return c - A;
    }
    throw new IllegalArgumentException(c + " is not a valid character in this alphabet");
  }

  @Override
  public boolean contains(char c) {
    return (c >= A && c <= Z);
  }

  @Override
  public int radix() {
    return 26;
  }

  @Override
  public int lgRadix() {
    return 5;
  }

}