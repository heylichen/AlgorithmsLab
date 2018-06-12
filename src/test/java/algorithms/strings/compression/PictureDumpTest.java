package algorithms.strings.compression;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Chen Li on 2018/6/12.
 */
public class PictureDumpTest {

  @Test
  public void dumpPic() throws Exception {
//    doDump("algorithms/string/compression/q32x48.bin", 32, 48);
//    Thread.sleep(8000);
    doDump("algorithms/string/compression/q64x96.bin", 64, 96);
    Thread.sleep(8000);
  }

  private void doDump(String cp, int width, int height) throws Exception {
    ClassPathResource classPathResource = new ClassPathResource(cp);
    InputStream in = new FileInputStream(classPathResource.getFile());

    PictureDump binaryDump = new PictureDump();
    binaryDump.dump(in, width, height);
  }
}