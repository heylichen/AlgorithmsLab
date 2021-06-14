package heylichen.leetcode;

import java.util.BitSet;

public class LongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        BitSet encountered = new BitSet(256);
        int maxLen = 0;
        int localMaxLen;
        int stringLen = s.length();
        for (int i = 0; i < stringLen; i++) {
            if (stringLen - i <= maxLen) {
                break;
            }
            localMaxLen = maxLenFromIndex(encountered, s, i);
            if (localMaxLen > maxLen) {
                maxLen = localMaxLen;
            }
        }

        return maxLen;
    }

    private int maxLenFromIndex(BitSet encountered, String s, int i) {
        encountered.clear();
        int maxLen = 0;
        for (int j = i; j < s.length(); j++) {
            char ch = s.charAt(j);
            if (!encountered.get(ch)) {
                encountered.set(ch);
                maxLen++;
            } else {
                break;
            }
        }
        return maxLen;
    }

}
