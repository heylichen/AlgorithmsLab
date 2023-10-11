package algorithms.strings.substring;

import algorithms.strings.alphabet.Alphabet;
import lombok.Getter;
import lombok.Setter;

/**
 * 练习KMP实现，验证对KMP的理解
 */
@Setter
@Getter
public class PracticeKMPSearcher implements SubstringSearcher {

    private Alphabet alphabet;
    private int dfa[][];
    private int patternSize;

    @Override
    public void compile(String pattern) {
        int alphabetSize = alphabet.radix();
        int patternSize = pattern.length();
        this.patternSize = patternSize;
        dfa = new int[alphabetSize][patternSize];
        //init
        int restartState = 0;
        int firstPatternChar = alphabet.toIndex(pattern.charAt(0));
        dfa[firstPatternChar][0] = 1;

        for (int j = 1; j < patternSize; j++) {
            int patternChar = alphabet.toIndex(pattern.charAt(j));
            copyColumn(dfa, restartState, j);
            dfa[patternChar][j] = j + 1;
            restartState = dfa[patternChar][restartState];
            //restartState为何如此计算？可以将restartState的维护单独拿出来考虑，是一个用已知dfa查询substring的过程，试试归纳法
            //pattern位置为 j+1的restartState = dfa[pat.charAt(j)][restartState]
        }
    }

    private void copyColumn(int[][] dfa, int fromColumn, int toColumn) {
        for (int[] row : dfa) {
            row[toColumn] = row[fromColumn];
        }
    }

    @Override
    public int search(String text) {
        int i = 0;
        int j = 0;
        int textLength = text.length();
        while (i < textLength && j < patternSize) {
            j = dfa[alphabet.toIndex(text.charAt(i))][j];
            i++;
        }
        if (j == patternSize) {
            return i - patternSize;
        }
        return -1;
    }
}
