package algorithms.strings.alphabet;

public class ABCAlphabet extends AbstractAlphabet {

  private char start = 'A';
  private char end = 'C';

  @Override
  public char toChar(int index) {
    return (char) (start + index);
  }

  @Override
  public int toIndex(char c) {
    return c - start;
  }

  @Override
  public boolean contains(char ch) {
    return ch >= start && ch <= end;
  }

  @Override
  public int radix() {
    return 3;
  }

  @Override
  public int lgRadix() {
    return 2;
  }
}
