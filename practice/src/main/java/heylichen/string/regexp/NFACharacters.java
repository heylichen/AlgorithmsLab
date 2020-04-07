package heylichen.string.regexp;

import java.util.Arrays;

public class NFACharacters {
    private static final char LEFT_PARENTHESIS = '(';
    private static final char RIGHT_PARENTHESIS = ')';
    private static final char ASTERISK = '*';
    private static final char OR = '|';
    private static final char DOT = '.';
    private static final char[] EMPTY_ARRAY = new char[0];

    public char[] filterValidChars(char[] chars) {
        if (chars == null) {
            return EMPTY_ARRAY;
        }
        char[] tmp = new char[chars.length];
        int i = 0;
        for (char aChar : chars) {
            if (isValid(aChar)) {
                tmp[i] = aChar;
                i++;
            }
        }
        return Arrays.copyOf(tmp, i);
    }

    public boolean isMatchableChar(char ch) {
        return isAlphabet(ch) || isDot(ch);
    }

    public boolean isValid(char ch) {
        return isAlphabet(ch)
                || isOr(ch)
                || isAsterisk(ch)
                || isLeftParenthesis(ch)
                || isRightParenthesis(ch)
                || isDot(ch);
    }

    public boolean isAlphabet(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    public boolean isOr(char ch) {
        return ch == OR;
    }

    public boolean isAsterisk(char ch) {
        return ASTERISK == ch;
    }

    public boolean isLeftParenthesis(char ch) {
        return LEFT_PARENTHESIS == ch;
    }

    public boolean isRightParenthesis(char ch) {
        return RIGHT_PARENTHESIS == ch;
    }

    public boolean isDot(char ch) {
        return DOT == ch;
    }
}
