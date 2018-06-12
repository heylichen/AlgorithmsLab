package algorithms.strings.compression;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Chen Li on 2018/6/12.
 */
public class BinaryDumpTest {

  @Test
  public void dumpPic() throws Exception {
    ClassPathResource classPathResource = new ClassPathResource("algorithms/string/compression/q32x48.bin");
    InputStream in = new FileInputStream(classPathResource.getFile());
    BinaryDump binaryDump = new BinaryDump();
    Pair<String, Integer> pair = binaryDump.dump(in, 32);
    System.out.println(pair.getLeft());
    System.out.println(pair.getRight() + " bits");
  }

}