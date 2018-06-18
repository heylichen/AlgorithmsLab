package algorithms.strings.compression;

import java.util.HashMap;
import java.util.Map;

import algorithms.sorting.PriorityQueue;
import algorithms.sorting.heap.PriorityQueueFactory;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/6/18.
 */
public class CharNodeTest {

  @Test
  public void prefixFreeTrieConstructionTest() {
    String text = "it was the best of times it was the worst of times\n";
    Map<Character, Integer> freqMap = new HashMap<>();
    for (int i = 0; i < text.length(); i++) {
      Character ch = text.charAt(i);
      Integer count = freqMap.computeIfAbsent(ch, k -> 0);
      freqMap.put(ch, count + 1);
    }

    PriorityQueue<CharNode> minPq = new PriorityQueueFactory().minPQ(freqMap.size());
    for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
      minPq.insert(new CharNode(null, null, entry.getKey(), entry.getValue(), true));
    }

    CharNode root = null;
    while (!minPq.isEmpty()) {
      CharNode charNode1 = minPq.pop();
      CharNode charNode2 = null;
      if (minPq.isEmpty()) {
        root = charNode1;
        break;
      } else {
        charNode2 = minPq.pop();
      }

      int freq = charNode1.getFrequency() + charNode2.getFrequency();
      CharNode merged = new CharNode(charNode1, charNode2, '\0', freq, false);
      minPq.insert(merged);
    }
    System.out.println("ok");
  }
}