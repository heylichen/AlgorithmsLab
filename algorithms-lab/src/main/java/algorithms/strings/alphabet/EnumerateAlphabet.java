package algorithms.strings.alphabet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;

public class EnumerateAlphabet extends AbstractAlphabet {

    private final Map<Integer, Character> toCharMap;
    private final Map<Character, Integer> toIndexMap;

    private EnumerateAlphabet(Set<Character> characters) {
        if (characters == null || characters.isEmpty()) {
            throw new IllegalArgumentException("characters must not be empty!");
        }
        Map<Integer, Character> toCharMap = new LinkedHashMap<>();
        Map<Character, Integer> toIndexMap = new LinkedHashMap<>();
        int i = 0;
        for (Character character : characters) {
            toCharMap.put(i, character);
            toIndexMap.put(character, i);
            i++;
        }

        this.toCharMap = ImmutableMap.copyOf(toCharMap);
        this.toIndexMap = ImmutableMap.copyOf(toIndexMap);
    }

    public List<Character> characters() {
        return new ArrayList<>(this.toCharMap.values());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Set<Character> characters = new HashSet<>();

        public Builder appendCharRange(CharRange charRange) {
            return appendCharRange(charRange.begin, charRange.end);
        }

        public Builder appendCharRange(char begin, char end) {
            char current = begin;
            while (current <= end) {
                characters.add(current);
                current++;
            }
            return this;
        }

        public Builder appendString(String characters) {
            for (int i = 0; i < characters.length(); i++) {
                this.characters.add(characters.charAt(i));
            }
            return this;
        }

        public EnumerateAlphabet build() {
            return new EnumerateAlphabet(this.characters);
        }
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
            throw new IllegalArgumentException("char <" + c + "> not found");
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

    @Getter
    public static class CharRange {

        private final char begin;
        private final char end;

        public CharRange(char begin, char end) {
            this.begin = begin;
            this.end = end;
        }
    }
}
