package algorithms.strings.compression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import org.junit.Test;

/**
 * Created by Chen Li on 2018/6/24.
 */
public class LZWCompressTest {

  private LZWCompress lzwCompress = new LZWCompress();
  @Test
  public void name() {
//    LZWCompress lzwCompress = new LZWCompress();
//
//    LZWCompress.TSTNode node = lzwCompress.initTable();
//    System.out.println("ok");
  }

  @Test
  public void compressTest() {
    Charset ascii = Charset.forName("US-ASCII");
    ByteArrayInputStream in = new ByteArrayInputStream("abab".getBytes(ascii));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    lzwCompress.compress(in, out);

    ByteArrayInputStream compressedIn = new ByteArrayInputStream(out.toByteArray());
    BinaryDump.dumpView(compressedIn, 12, 36);
  }


}