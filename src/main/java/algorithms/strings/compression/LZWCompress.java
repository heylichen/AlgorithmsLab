package algorithms.strings.compression;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
import lombok.Getter;
import lombok.Setter;

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

    TSTNode node = root;
    TSTNode lastEqualNode = null;
    List<Integer> chars = new ArrayList<>();
    while (!binaryIn.isEmpty()) {
      lastEqualNode = null;

      Integer lastCharacter = null;
      while (!binaryIn.isEmpty() || lastCharacter != null) {
        int character = 0;
        if (lastCharacter == null) {
          character = binaryIn.readInt(8);
        } else {
          character = lastCharacter;
          lastCharacter = null;
        }

        node = getNode(node, character);
        if (node != null) {
          chars.add(character);
          lastEqualNode = node;
          node = node.equal;
        } else {
          //found longest match key, as chars, next char is character
          binaryOut.write(lastEqualNode.codeword, 12);
          if (codeWord < lastCodeWord) {
            lastEqualNode.equal = putNode(lastEqualNode.equal, character, codeWord);
            codeWord++;
          }
          lastCharacter = character;
          //refresh
          node = root;
          chars.clear();
        }
      }
    }
    if (lastEqualNode != null) {
      binaryOut.write(lastEqualNode.codeword, 12);
    }
    binaryOut.close();
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
  public static class TSTNode {

    private int character;
    private TSTNode less;
    private TSTNode equal;
    private TSTNode greater;
    private Integer codeword;

  }
}
