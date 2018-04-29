package algorithms.strings.alphabet;

/**
 * Created by Chen Li on 2018/4/7.
 */
public interface Alphabet {

  char toChar(int index);

  int toIndex(char c);

  boolean contains(char ch);

  /**
   * radix (number of characters in alphabet)
   */
  int radix();

  /**
   * number of bits to represent an index
   */
  int lgRadix();

  /**
   * convert s to base-R integer
   */
  int[] toIndices(String s);

  /**
   * convert base-R integer to string over this alphabet
   */
  String toChars(int[] indices);
}
