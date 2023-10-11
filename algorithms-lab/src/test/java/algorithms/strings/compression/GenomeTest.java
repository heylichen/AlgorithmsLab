package algorithms.strings.compression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

/**
 * Created by Chen Li on 2018/6/12.
 */
public class GenomeTest {

  @Test
  public void name() throws Exception {
    //String text = "AGC";
    String text = "ATAGATGCATAGCGCATAGCTAGATGTGCTAGC";
    InputStream genomeIn = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
    ByteArrayOutputStream genomeOut = new ByteArrayOutputStream();

    Genome genome = new Genome();
    genome.compress(genomeIn, genomeOut);

    InputStream compressed = new ByteArrayInputStream(genomeOut.toByteArray());
    ByteArrayOutputStream expandOs = new ByteArrayOutputStream();
    genome.expand(compressed, expandOs);

    String expanded = expandOs.toString(StandardCharsets.UTF_8.name());
    System.out.println(expanded);
//    BinaryDump binaryDump = new BinaryDump();
//    Pair<String, Integer> pair = binaryDump.dump(in, 32);
//    System.out.println(pair.getLeft());
//    System.out.println(pair.getRight() + " bits");
  }
}