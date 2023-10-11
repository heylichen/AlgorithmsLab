package algorithms.strings.compression.transform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class BurrowsWheeler {

  public StringRotation encode(String string) {
    int length = string.length() + 1;
    List<StringRotation> list = new ArrayList<>();
    //all rotations
    for (int i = 0; i < length; i++) {
      list.add(new StringRotation(string, i));
    }
    //sort
    Collections.sort(list);
    //last character
    StringBuilder sb = new StringBuilder();
    int eofIndex = -1;
    for (int i = 0; i < list.size(); i++) {
      Character optionalCharacter = list.get(i).charAt(length - 1);
      if (optionalCharacter != null) {
        sb.append(optionalCharacter);
      } else {
        eofIndex = i;
      }
    }
    String transformed = sb.substring(eofIndex) + sb.substring(0, eofIndex);
    return new StringRotation(transformed, length - 1 - eofIndex);
  }

  public String decode(StringRotation rotation) {
    //init
    List<Chars> chars = newCharsList(rotation.size);
    //prepend and sort
    for (int i = 0; i < rotation.size; i++) {
      chars = prependAndSort(rotation, chars);
    }
    //get the one
    Chars result = null;
    for (Chars aChar : chars) {
      if (aChar.charAt(aChar.size() - 1) == null) {
        result = aChar;
        break;
      }
    }
    return result.toString();
  }

  private List<Chars> newCharsList(int size) {
    List<Chars> chars = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      Chars ch = new Chars();
      chars.add(ch);
    }
    return chars;
  }

  private List<Chars> prependAndSort(StringRotation rotation, List<Chars> charsList) {
    for (int j = 0; j < rotation.size; j++) {
      Character optionalCharacter = rotation.charAt(j);
      Chars ch = charsList.get(j);
      ch.prepend(optionalCharacter);
    }
    Collections.sort(charsList);
    return charsList;
  }

  /**
   * specify a string, and a start character index of the rotation
   * always assume that the eof character is at the end,
   * for example, given the following string
   * banana$
   * to generate a rotation a$banana, use this code
   * StringRotation r = new StringRotation("banana",5);
   */
  public static class StringRotation extends AbstractCharSequence<StringRotation> {

    private final String string;
    private final int startIndex;
    @Getter
    @Setter
    private String eofCharacter = "$";
    private String view;
    private final int size;

    public StringRotation(String string, int startIndex) {
      this.string = string;
      this.startIndex = startIndex;
      this.size = string.length() + 1;
    }

    public Character charAt(int index) {
      if (index >= size()) {
        throw new IllegalArgumentException("index out of bound " + index);
      }
      index = (index + startIndex) % size();
      return index == size() - 1 ? null : string.charAt(index);
    }

    public int size() {
      return size;
    }

    public String toString() {
      if (view == null) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
          Character character = charAt(i);
          if (character != null) {
            sb.append(character);
          } else {
            sb.append(eofCharacter);
          }
        }
        view = sb.toString();
      }
      return view;
    }
  }

  /**
   * a characters list allows null characters
   * support prepend operation and implements Comparable
   */
  private static class Chars extends AbstractCharSequence<Chars> {

    private LinkedList<Character> chars = new LinkedList<>();

    public void prepend(Character character) {
      chars.addFirst(character);
    }

    public Character charAt(int index) {
      return chars.get(index);
    }

    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (Character aChar : chars) {
        if (aChar != null) {
          sb.append(aChar);
        }
      }
      return sb.toString();
    }

    public int size() {
      return chars.size();
    }
  }

  private interface CharSequence<T> extends Comparable<T> {

    Character charAt(int index);

    int size();
  }

  @Getter
  @Setter
  private abstract static class AbstractCharSequence<T extends CharSequence> implements CharSequence<T> {

    @Override
    public int compareTo(T another) {
      if (another == null) {
        throw new IllegalArgumentException("must not be null");
      }
      int length = size();
      int k = 0;
      while (k < length) {
        Character c1 = charAt(k);
        Character c2 = another.charAt(k);
        if (c1 == null) {
          return -1;
        }
        if (c2 == null) {
          return 1;
        }
        if (c1.equals(c2)) {
          k++;
        } else {
          return c1 - c2;
        }
      }
      return 0;
    }
  }
}
