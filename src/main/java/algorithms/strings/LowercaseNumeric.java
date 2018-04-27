package algorithms.strings;

/**
 * Created by Chen Li on 2018/4/7.
 */
public class LowercaseNumeric extends AbstractAlphabet {

  public static final char A = 'a';
  public static final char Z = 'z';
  public static final char ZERO = '0';
  public static final char NINE = '9';
  public static final int GAP = 87;

  @Override
  public char toChar(int index) {
    if (index >= 0 && index <= 9) {
      return (char) (index + ZERO);
    }
    if (index >= 10 && index <= 35) {
      return (char) (index + GAP);
    }
    throw new IllegalArgumentException(index + " is not a valid index in this alphabet");
  }

  @Override
  public int toIndex(char c) {
    if (c >= ZERO && c <= NINE) {
      return c - ZERO;
    }
    if (c >= A && c <= Z) {
      return c - GAP;
    }
    throw new IllegalArgumentException(c + " is not a valid character in this alphabet");
  }

  @Override
  public boolean contains(char c) {
    if (c >= ZERO && c <= NINE) {
      return true;
    }
    if (c >= A && c <= Z) {
      return true;
    }
    return false;
  }

  @Override
  public int radix() {
    return 36;
  }

  @Override
  public int lgRadix() {
    return 6;
  }
}
