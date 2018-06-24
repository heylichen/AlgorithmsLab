package algorithms.strings.compression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.junit.Assert;
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

  @Test
  public void expandTest() throws Exception {
    doExpand("abcdsdfde");
    doExpand("aaa");
    doExpand("aaabbbaaabbbaaa");
  }

  private void doExpand(String text) throws UnsupportedEncodingException {
    Charset ascii = Charset.forName("US-ASCII");
    ByteArrayInputStream in = new ByteArrayInputStream(text.getBytes(ascii));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    lzwCompress.compress(in, out);
    //dump
//    ByteArrayInputStream forDump = new ByteArrayInputStream(out.toByteArray());
//    BinaryDump.dumpView(forDump, 12, 12 * 9);

    ByteArrayInputStream compressedIn = new ByteArrayInputStream(out.toByteArray());
    ByteArrayOutputStream expandOut = new ByteArrayOutputStream();
    lzwCompress.expand(compressedIn, expandOut);

    String expanded = expandOut.toString(ascii.name());
    Assert.assertEquals("string must not change!", text, expanded);
    System.out.println(expandOut.toString(ascii.name()));
  }
}