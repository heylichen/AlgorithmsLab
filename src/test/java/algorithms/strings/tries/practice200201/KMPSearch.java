package algorithms.strings.tries.practice200201;

import algorithms.strings.alphabet.Alphabet;

public class KMPSearch {
    private final Alphabet alphabet;
    private final int dfa[][];
    private final int patternChars;

    public KMPSearch(Alphabet alphabet, String pattern) {
        this.alphabet = alphabet;
        this.patternChars = pattern.length();
        dfa = new int[alphabet.radix()][patternChars];
        init(pattern);
    }

    private void init(String pattern) {
        initState0(pattern);

        int alphabetChars = alphabet.radix();
        int backupState = 0;
        for (int j = 1; j < patternChars; j++) {
            char patternChar = pattern.charAt(j);
            for (int i = 0; i < alphabetChars; i++) {
                char textChar = alphabet.toChar(i);
                if (textChar == patternChar) {
                    dfa[i][j] = j + 1;
                } else {
                    dfa[i][j] = dfa[i][backupState];
                }
            }

            int patternCharIndex = alphabet.toIndex(patternChar);
            backupState = dfa[patternCharIndex][backupState];
        }
    }

    private void initState0(String pattern) {
        int alphabetChars = alphabet.radix();
        char firstPatternChar = pattern.charAt(0);
        for (int i = 0; i < alphabetChars; i++) {
            char textChar = alphabet.toChar(i);
            if (textChar != firstPatternChar) {
                dfa[i][0] = 0;
            } else {
                dfa[i][0] = 1;
            }
        }
    }

    public int search(String text) {
        int textChars = text.length();
        int i = 0, j = 0;
        for (; i < textChars && j < patternChars; i++) {
            char ch = text.charAt(i);
            int charIndex = alphabet.toIndex(ch);
            j = dfa[charIndex][j];
        }
        if (j == patternChars) {
            return i - patternChars;
        } else {
            return -1;
        }

    }
}