package algorithms.strings.compression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/6/18.
 */
public class CharNodeTest {

  private HuffmanCompression huffmanCompression = new HuffmanCompression();

  @Test
  public void prefixFreeTrieConstructionTest() throws Exception {
    String text = "it was the best of times it was the worst of times\n";
    CharNode root = huffmanCompression.buildTrie(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8)));
    System.out.println(huffmanCompression.compressedBits(root));
  }

  @Test
  public void name() throws Exception {
    String text = "AAAEEFG";
    InputStream in = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
    CharNode root = huffmanCompression.buildTrie(in);
    System.out.println("ok");
  }

  @Test
  public void builMaptest() throws Exception {
    String text = "AAAEEFG";
    InputStream in = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
    CharNode root = huffmanCompression.buildTrie(in);
    Map<Character, String> map = huffmanCompression.buildCharacterMap(root);
    for (Map.Entry<Character, String> entry : map.entrySet()) {
      System.out.println(entry.getKey() + " : " + entry.getValue());
    }
  }

  @Test
  public void compressAndExpandTest() throws Exception {
    String text = "it was the best of times it was the worst of times\n";
    InputStream in1 = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
    CharNode trie = huffmanCompression.buildTrie(in1);

    //can not read the same InputStream twice, so create another instance
    InputStream in = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    huffmanCompression.compress(in, bos, trie);

    ByteArrayInputStream byteIn = new ByteArrayInputStream(bos.toByteArray());
    ByteArrayOutputStream expanded = new ByteArrayOutputStream();
    huffmanCompression.expand(byteIn, expanded);

    System.out.println(expanded.toString(StandardCharsets.UTF_8.name()));
  }

  @Test
  public void codeTest() throws Exception {
    String text = "AAAEEFG";
    InputStream in = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
    CharNode root = huffmanCompression.buildTrie(in);
    //
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    BinaryOut bo = new BinaryOut(bos);
    huffmanCompression.writeTrie(root, bo);
    bo.close();
    //
    ByteArrayInputStream byteIn = new ByteArrayInputStream(bos.toByteArray());
    BinaryIn bin = new BinaryIn(byteIn);
    CharNode node = huffmanCompression.readTrie(bin);
    //
    System.out.println("ok");
  }


}