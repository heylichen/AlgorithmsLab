package algorithms.strings.compression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

@Slf4j
public class LongRunsTest {

  @Test
  public void name() throws Exception {
    compessTest("algorithms/string/compression/q32x48.bin", 65, 32);
    compessTest("algorithms/string/compression/q64x96.bin", 65, 64);
  }


  private void compessTest(String cp, int runCount, int dumpWidth) throws Exception {
    ClassPathResource classPathResource = new ClassPathResource(cp);
    InputStream in = new FileInputStream(classPathResource.getFile());
    BinaryDump.dumpView(new FileInputStream(classPathResource.getFile()), dumpWidth);

    ByteArrayOutputStream os = new ByteArrayOutputStream();

    LongRuns longRuns = new LongRuns(runCount);//设置不同的值，会有不同的压缩比率
    longRuns.compress(in, os);

    ByteArrayInputStream compressedIn = new ByteArrayInputStream(os.toByteArray());
    ByteArrayOutputStream expand = new ByteArrayOutputStream();
    longRuns.expand(compressedIn, expand);

    ByteArrayInputStream expandIn = new ByteArrayInputStream(expand.toByteArray());
    BinaryDump.dumpView(expandIn, dumpWidth);

    //
    int originalBits = expand.toByteArray().length * 8;
    int after = os.toByteArray().length * 8;
    log.info("{} / {} : {}", after, originalBits, (double) after / (double) originalBits);
  }
}