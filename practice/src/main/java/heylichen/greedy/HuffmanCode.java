package heylichen.greedy;

import heylichen.utils.SquareTreePrinter;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCode {

    private Map<Character, String> genCode(Map<Character, Integer> charFreqMap) {
        CharFreqNode root = buildPrefixCode(charFreqMap);
        return null;
    }

    public String buildAndViewPrefixCode(Map<Character, Integer> charFreqMap) {
        CharFreqNode root = buildPrefixCode(charFreqMap);
        SquareTreePrinter<CharFreqNode> printer = new SquareTreePrinter<>(k -> {
            if (k.getCh() == null) {
                return k.getFreq() + "";
            } else {
                return k.getCh().toString()+":"+k.getFreq();
            }
        }, CharFreqNode::getLeft, CharFreqNode::getRight);
        return printer.print(root);
    }

    private CharFreqNode buildPrefixCode(Map<Character, Integer> charFreqMap) {
        PriorityQueue<CharFreqNode> q = new PriorityQueue<>(charFreqMap.size(), Comparator.comparing(CharFreqNode::getFreq));
        for (Map.Entry<Character, Integer> entry : charFreqMap.entrySet()) {
            CharFreqNode cf = new CharFreqNode(entry.getKey(), entry.getValue());
            q.add(cf);
        }

        for (int i = 0; i < charFreqMap.size() - 1; i++) {
            CharFreqNode left = q.poll();
            CharFreqNode right = q.poll();
            CharFreqNode z = new CharFreqNode(null, left.freq + right.freq);
            z.left = left;
            z.right = right;
            q.offer(z);
        }
        return q.poll();
    }

    @Getter
    public static class CharFreqNode {
        private Character ch;
        private int freq;
        @Setter
        private CharFreqNode left;
        @Setter
        private CharFreqNode right;

        public CharFreqNode(Character ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }
}
