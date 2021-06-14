package heylichen.leetcode;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongestSubstringTest {

    @Test
    public void lengthOfLongestSubstring() {
        LongestSubstring longestSubstring = new LongestSubstring();
        Assert.assertEquals(0,longestSubstring.lengthOfLongestSubstring(""));
        Assert.assertEquals(3,longestSubstring.lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(3,longestSubstring.lengthOfLongestSubstring("pwwkew"));
        Assert.assertEquals(17,longestSubstring.lengthOfLongestSubstring("1234567890abcdefg"));
    }

    @Test
    public void fasterTest() {
        FasterLongestSubstring longestSubstring = new FasterLongestSubstring();
        Assert.assertEquals(0,longestSubstring.lengthOfLongestSubstring(""));
        Assert.assertEquals(3,longestSubstring.lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(3,longestSubstring.lengthOfLongestSubstring("pwwkew"));
        Assert.assertEquals(17,longestSubstring.lengthOfLongestSubstring("1234567890abcdefg"));
    }
}