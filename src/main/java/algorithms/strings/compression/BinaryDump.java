package algorithms.strings.compression;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import edu.princeton.cs.algs4.BinaryIn;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/6/11.
 */
public class BinaryDump {

  private static final char ON = '1';
  private static final char OFF = '0';

  public Pair<String, Integer> dump(InputStream inputStream, int lineWidth) {
    StringBuilder sb = new StringBuilder();
    int characters = 0;
    BinaryIn binaryIn = new BinaryIn(inputStream);
    while (!binaryIn.isEmpty()) {
      sb.append(binaryIn.readBoolean() ? ON : OFF);
      characters++;
      if (characters % lineWidth == 0) {
        sb.append("\n");
      }
    }
    return Pair.of(sb.toString(), characters);
  }

  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("usage: BinaryDump lineWidth");
      return;
    }
    int lineWidth = Integer.valueOf(args[0]);
    BinaryDump binaryDump = new BinaryDump();
    String text = "ATAGATGCATAGCGCATAGCTAGATGTGCTAGC";
    System.out.println(text);

    InputStream inputStream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
    Pair<String, Integer> pair = binaryDump.dump(inputStream, lineWidth);
    System.out.println(pair.getLeft());
    System.out.println(pair.getRight() + " bits");
    //

    System.out.println();
  }
}
