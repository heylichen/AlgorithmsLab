package heylichen.leetcode;

import java.util.HashMap;
import java.util.Map;

public class FasterLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int maxLen = 0;
        int localMaxLen;
        int stringLen = s.length();
        Map<Character, Integer> encountered = new HashMap<>(256);

        for (int i = 0; i < stringLen; i++) {
            if (stringLen - i <= maxLen) {
                break;
            }

            encountered.clear();
            localMaxLen = 0;
            for (int j = i; j < s.length(); j++) {
                char ch = s.charAt(j);
                Integer encounteredIndex = encountered.get(ch);
                if (encounteredIndex == null) {
                    encountered.put(ch, j);
                    localMaxLen++;
                } else {
                    int skip = encounteredIndex.intValue() - i;
                    i = i + skip;
                    break;
                }
            }

            if (localMaxLen > maxLen) {
                maxLen = localMaxLen;
            }
        }
        return maxLen;
    }


}
