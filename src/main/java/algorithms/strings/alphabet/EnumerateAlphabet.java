package algorithms.strings.alphabet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;

public class EnumerateAlphabet extends AbstractAlphabet {

  private final Map<Integer, Character> toCharMap;
  private final Map<Character, Integer> toIndexMap;

  public EnumerateAlphabet(String language) {
    if (StringUtils.isBlank(language)) {
      throw new IllegalArgumentException("language must not be empty!");
    }
    Set<Character> charset = new HashSet<>();
    for (int i = 0; i < language.length(); i++) {
      charset.add(language.charAt(i));
    }
    Map<Integer, Character> toCharMap = new HashMap<>();
    Map<Character, Integer> toIndexMap = new HashMap<>();
    int i = 0;
    for (Character character : charset) {
      toCharMap.put(i, character);
      toIndexMap.put(character, i);
      i++;
    }

    this.toCharMap = ImmutableMap.copyOf(toCharMap);
    this.toIndexMap = ImmutableMap.copyOf(toIndexMap);
  }

  @Override
  public char toChar(int index) {
    Character ch = toCharMap.get(index);
    if (ch == null) {
      throw new IllegalArgumentException("index not found");
    }
    return ch;
  }

  @Override
  public int toIndex(char c) {
    Integer index = toIndexMap.get(c);
    if (index == null) {
      throw new IllegalArgumentException("c not found");
    }
    return index;
  }

  @Override
  public boolean contains(char ch) {
    return toIndexMap.containsKey(ch);
  }

  @Override
  public int radix() {
    return toIndexMap.size();
  }
}
