package algorithms.strings.regexp;

import org.junit.Test;

public class RegexpTest {

  @Test
  public void compileTest() {
    Regexp regexp = new Regexp();
    regexp.compile("( ( A * B | A C ) D )");
    System.out.println("ok");
  }

  @Test
  public void recognizesTest() {
    Regexp regexp = new Regexp();
    regexp.compile("( C ( A * B | A C ) D )");
    System.out.println(regexp.recognizes("CAABD"));
    System.out.println(regexp.recognizes("CACD"));
    System.out.println(regexp.recognizes("ACBD"));
    System.out.println(regexp.recognizes("AABDA"));
  }
}