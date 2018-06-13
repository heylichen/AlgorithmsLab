package algorithms.strings.alphabet;

/**
 * Created by Chen Li on 2018/4/7.
 */
public abstract class AbstractAlphabet implements Alphabet {

  @Override
  public int[] toIndices(String s) {
    int[] result = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
      result[i] = toIndex(s.charAt(i));
    }
    return result;
  }

  @Override
  public String toChars(int[] indices) {
    if (indices == null || indices.length == 0) {
      return null;
    }
    char[] chars = new char[indices.length];
    for (int i = 0; i < indices.length; i++) {
      chars[i] = toChar(indices[i]);
    }
    return new String(chars);
  }

  @Override
  public int lgRadix() {
    int characterCount = radix();

    int bits = 0;
    while (characterCount > 0) {
      bits++;
      characterCount=  characterCount >>> 1;
    }
    return bits;
  }

}
