package algorithms.strings.compression;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import algorithms.sorting.PriorityQueue;
import algorithms.sorting.heap.PriorityQueueFactory;
import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;

/**
 * Created by Chen Li on 2018/6/19.
 */
public class HuffmanCompression {


  public void compress(InputStream in, OutputStream out, CharNode trie) throws Exception {
    BinaryOut binaryOut = new BinaryOut(out);
    //write trie
    writeTrie(trie, binaryOut);
    //total bits
    int total = compressedBits(trie);
    binaryOut.write(total);
    //
    Map<Character, String> charMap = buildCharacterMap(trie);
    InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);

    while (reader.ready()) {
      char ch = (char) reader.read();
      String bits = charMap.get(ch);
      writeBits(bits, binaryOut);
    }
    binaryOut.close();
  }

  public void expand(InputStream in, OutputStream out) throws IOException {
    OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);

    BinaryIn binaryIn = new BinaryIn(in);
    CharNode trie = readTrie(binaryIn);
    int bits = binaryIn.readInt();
    int read = 0;

    CharNode node = trie;
    while (read < bits) {
      while (!node.isLeaf()) {
        boolean bit = binaryIn.readBoolean();
        read++;
        if (bit) {
          node = node.getRight();
        } else {
          node = node.getLeft();
        }
      }

      writer.write((int) node.getCharacter());
      node = trie;
    }
    writer.flush();
    writer.close();
  }


  private void writeBits(String bits, BinaryOut binaryOut) {
    for (int i = 0; i < bits.length(); i++) {
      boolean bit = bits.charAt(i) == '1';
      binaryOut.write(bit);
    }
  }

  public Map<Character, String> buildCharacterMap(CharNode node) {
    Map<Character, String> characterStringMap = new HashMap<>();
    buildCharacterMap(node, characterStringMap, "");
    return characterStringMap;
  }

  private void buildCharacterMap(CharNode node, Map<Character, String> characterStringMap, String prefix) {
    if (node.isLeaf()) {
      characterStringMap.put(node.getCharacter(), prefix);
      return;
    }
    buildCharacterMap(node.getLeft(), characterStringMap, prefix + "0");
    buildCharacterMap(node.getRight(), characterStringMap, prefix + "1");
  }

  public CharNode buildTrie(InputStream in) throws IOException {
    InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
    Map<Character, Integer> freqMap = new HashMap<>();
    while (reader.ready()) {
      char ch = (char) reader.read();
      if (ch == -1) {
        break;
      }

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
    return root;
  }

  public CharNode readTrie(BinaryIn binaryIn) {
    boolean isLeaf = binaryIn.readBoolean();
    if (!isLeaf) {
      CharNode left = readTrie(binaryIn);
      CharNode right = readTrie(binaryIn);
      return new CharNode(left, right, '\0', 1, false);
    } else {
      char ch = binaryIn.readChar();
      return new CharNode(null, null, ch, 1, true);
    }
  }

  public void writeTrie(CharNode node, BinaryOut binaryOut) {
    if (node.isLeaf()) {
      binaryOut.write(true);
      binaryOut.write(node.getCharacter());
    } else {
      binaryOut.write(false);
      writeTrie(node.getLeft(), binaryOut);
      writeTrie(node.getRight(), binaryOut);
    }
  }

  public int compressedBits(CharNode node) {
    if (node == null) {
      return 0;
    }
    if (node.isLeaf()) {
      return node.getFrequency();
    }
    return compressedBits(node.getLeft(), 1) + compressedBits(node.getRight(), 1);
  }

  private int compressedBits(CharNode node, int depth) {
    if (node.isLeaf()) {
      return node.getFrequency() * depth;
    }
    int nextDepth = depth + 1;
    return compressedBits(node.getLeft(), nextDepth) + compressedBits(node.getRight(), nextDepth);
  }
}
