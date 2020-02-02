package algorithms.strings.tries.practice200201;

import org.junit.Test;

public class TernarySearchTrieTest {
    @Test
    public void testPutGet() {
        TernarySearchTrie<Integer> trieST = newSt();
        System.out.println(trieST.get("she"));
        System.out.println(trieST.get("sell"));
        System.out.println(trieST.get("sea"));
        System.out.println(trieST.get("shells"));

        System.out.println(trieST.get("shellsa"));
    }

    @Test
    public void testKeys() {
        TernarySearchTrie<Integer> trieST = newSt();
        for (String key : trieST.keys()) {
            System.out.println(key);
        }
        System.out.println();
        for (String key : trieST.keysWithPrefix("se")) {
            System.out.println(key);
        }
    }


    @Test
    public void testLongestPrefix() {
        TernarySearchTrie<Integer> trieST = newSt();

        System.out.println(trieST.longestPrefixOf("abc"));
        System.out.println(trieST.longestPrefixOf("shells"));
        System.out.println(trieST.longestPrefixOf("shellsabc"));
    }

    @Test
    public void testKeysThatMatch() {
        TernarySearchTrie<Integer> trieST = newSt();

        System.out.println(trieST.keysThatMatch("sh."));
        System.out.println(trieST.keysThatMatch("sh.lls"));
        System.out.println(trieST.keysThatMatch("s.."));
        System.out.println(trieST.keysThatMatch("s..a"));
    }


    private TernarySearchTrie<Integer> newSt() {
        TernarySearchTrie<Integer> trieST = new TernarySearchTrie<>();
        trieST.put("she", 1);
        trieST.put("sell", 2);
        trieST.put("sea", 3);
        trieST.put("shells", 4);
        return trieST;
    }
}
