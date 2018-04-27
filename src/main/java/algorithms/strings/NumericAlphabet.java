package algorithms.strings;

/**
 * Created by Chen Li on 2018/4/17.
 */
public class NumericAlphabet extends AbstractAlphabet {

  public static final char ZERO = '0';
  public static final char NINE = '9';

  @Override
  public char toChar(int index) {
    if (index >= 0 && index < radix()) {
      return (char) (index + ZERO);
    }
    throw new IllegalArgumentException(index + " is not a valid index in this alphabet");
  }

  @Override
  public int toIndex(char c) {
    if (c >= ZERO && c <= NINE) {
      return c - ZERO;
    }
    throw new IllegalArgumentException(c + " is not a valid character in this alphabet");
  }

  @Override
  public boolean contains(char c) {
    return (c >= ZERO && c <= NINE);
  }

  @Override
  public int radix() {
    return 10;
  }

  @Override
  public int lgRadix() {
    return 4;
  }

}
