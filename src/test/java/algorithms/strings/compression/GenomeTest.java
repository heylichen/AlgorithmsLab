package algorithms.strings.compression;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import edu.princeton.cs.algs4.BinaryOut;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Chen Li on 2018/6/12.
 */
public class GenomeTest {

  @Test
  public void name() throws Exception{

    String text = "ATAGATGCATAGCGCATAGCTAGATGTGCTAGC";
    Genome genome = new Genome();
    ClassPathResource classPathResource = new ClassPathResource("algorithms/string/compression/tmp");
    InputStream in = new FileInputStream(classPathResource.getFile());
    OutputStream out = new FileOutputStream(classPathResource.getFile());

    BinaryOut bo = new BinaryOut(out);
    genome.compress(text, bo);
    bo.close();


    String result = genome.expand(in);
    System.out.println(result);
  }
}