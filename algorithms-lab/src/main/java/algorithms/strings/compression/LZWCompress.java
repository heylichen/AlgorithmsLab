package algorithms.strings.compression;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Created by Chen Li on 2018/6/24.
 * input 8 bit character
 * output 12 bit codeword
 */
public class LZWCompress {

  public void compress(InputStream in, OutputStream outputStream) {
    BinaryIn binaryIn = new BinaryIn(in);
    BinaryOut binaryOut = new BinaryOut(outputStream);
    TSTNode root = initTable();
    int codeWord = 257;
    int lastCodeWord = 4096;

    Integer unmatchedCharacter = null;
    while (true) {
      LongestMatch longestMatch = findLongestMatch(root, binaryIn, unmatchedCharacter);
      if (longestMatch == null) {
        break;
      }
      TSTNode lastEqualNode = longestMatch.lastMatchedNode;
      unmatchedCharacter = longestMatch.unmatchedCharacter;
      binaryOut.write(lastEqualNode.codeword, 12);
      if (codeWord < lastCodeWord && unmatchedCharacter != null) {
        lastEqualNode.equal = putNode(lastEqualNode.equal, unmatchedCharacter, codeWord);
        codeWord++;
      }
    }
    binaryOut.write(getEOF(), 12);
    binaryOut.close();
  }

  private int getEOF() {
    return 256;
  }

  //found longest match key, as chars, next char is unmatched character
  private LongestMatch findLongestMatch(TSTNode node, BinaryIn binaryIn, Integer bufferedChar) {
    if ((binaryIn == null || binaryIn.isEmpty()) && bufferedChar == null) {
      return null;
    }
    TSTNode lastMatchedNode = null;
    Integer unmatchedCharacter = null;
    Integer character = bufferedChar == null ? binaryIn.readInt(8) : bufferedChar;
    while (character != null) {
      node = getNode(node, character);
      if (node != null) {
        //chars.add(character);
        lastMatchedNode = node;
        node = node.equal;

        if (binaryIn.isEmpty()) {
          break;
        } else {
          character = binaryIn.readInt(8);
        }
      } else {
        unmatchedCharacter = character;
        break;
      }
    }
    LongestMatch longestMatch = new LongestMatch();
    longestMatch.setLastMatchedNode(lastMatchedNode);
    longestMatch.setUnmatchedCharacter(unmatchedCharacter);
    return longestMatch;
  }

  private TSTNode getNode(TSTNode node, int ch) {
    if (node == null || ch == node.character) {
      return node;
    }
    if (ch < node.character) {
      return getNode(node.less, ch);
    } else {
      return getNode(node.greater, ch);
    }
  }

  public void expand(InputStream in, OutputStream outputStream) {
    BinaryIn binaryIn = new BinaryIn(in);
    BinaryOut binaryOut = new BinaryOut(outputStream);
    Map<Integer, List<Integer>> map = initExpandMap();
    List<Integer> lastString = null;
    int codeWord = 257;//only change this when a new codeword is added, fixed a bug about this
    int lastCodeWord = 4096;

    while (!binaryIn.isEmpty()) {
      int compressedCode = binaryIn.readInt(12);
      if (compressedCode == getEOF()) {
        break;
      }

      List<Integer> expanded = map.get(compressedCode);
      if (expanded == null) {
        //in case "aaa"
        expanded = merge(lastString, lastString.get(0));
        map.put(compressedCode, expanded);
        codeWord = compressedCode;
        codeWord++;
      } else if (lastString != null && codeWord < lastCodeWord) {
        map.put(codeWord, merge(lastString, expanded.get(0)));
        codeWord++;
      }
      write(binaryOut, expanded);
      lastString = expanded;
    }

    binaryOut.close();
  }

  private List<Integer> merge(final List<Integer> string, final Integer ch) {
    List<Integer> newString = new ArrayList<>();
    newString.addAll(string);
    newString.add(ch);

    return newString;
  }

  private void write(BinaryOut binaryOut, List<Integer> chars) {
    if (CollectionUtils.isEmpty(chars)) {
      return;
    }
    for (Integer aChar : chars) {
      binaryOut.write(aChar, 8);
    }
  }

  private Map<Integer, List<Integer>> initExpandMap() {
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < 256; i++) {
      map.put(i, Arrays.asList(i));
    }
    return map;
  }

  private TSTNode initTable() {
    return balancedInit(0, 255, null);
  }

  private TSTNode balancedInit(int from, int end, TSTNode node) {
    int middleValue = (from + end) / 2;
    node = putNode(node, middleValue, middleValue);

    if (from <= middleValue - 1) {
      node.less = balancedInit(from, middleValue - 1, node.less);
    }

    if (middleValue + 1 <= end) {
      node.greater = balancedInit(middleValue + 1, end, node.greater);
    }
    return node;
  }

  private TSTNode putNode(TSTNode node, int character, Integer value) {
    if (node == null) {
      node = new TSTNode();
      node.character = character;
      node.codeword = value;
      return node;
    }

    if (character == node.character) {
      node.codeword = value;
    } else if (character > node.character) {
      TSTNode right = putNode(node.greater, character, value);
      node.greater = right;
    } else {
      TSTNode right = putNode(node.less, character, value);
      node.less = right;
    }
    return node;
  }


  @Getter
  @Setter
  private static class TSTNode {

    private int character;
    private TSTNode less;
    private TSTNode equal;
    private TSTNode greater;
    private Integer codeword;

  }

  @Getter
  @Setter
  private static class LongestMatch {

    private TSTNode lastMatchedNode;
    private Integer unmatchedCharacter;
    private List<Integer> matchedChars;
  }
}
