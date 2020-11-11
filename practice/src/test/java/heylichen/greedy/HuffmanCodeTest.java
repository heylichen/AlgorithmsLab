package heylichen.greedy;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HuffmanCodeTest {
    @Test
    public void testHuffmanCode() {
        Map<Character, Integer> map = createCharFreq();
        HuffmanCode hc = new HuffmanCode();
        String tree = hc.buildAndViewPrefixCode(createCharFreq());
        System.out.println(tree);
    }

    private Map<Character, Integer> createCharFreq() {
        Map<Character, Integer> charFreqMap = new HashMap<>();
        charFreqMap.put('a', 45);
        charFreqMap.put('b', 13);
        charFreqMap.put('c', 12);
        charFreqMap.put('d', 16);
        charFreqMap.put('e', 9);
        charFreqMap.put('f', 5);
        return charFreqMap;
    }
}