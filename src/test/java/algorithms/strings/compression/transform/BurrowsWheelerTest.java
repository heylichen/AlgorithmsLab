package algorithms.strings.compression.transform;

import org.junit.Assert;
import org.junit.Test;

public class BurrowsWheelerTest {

  private BurrowsWheeler burrowsWheeler = new BurrowsWheeler();

  @Test
  public void encodeTest() {
    String text = "banana";
    System.out.println( burrowsWheeler.encode(text));
    Assert.assertEquals("", "annb$aa", burrowsWheeler.encode(text).toString());

    text = "appellee";
    System.out.println( burrowsWheeler.encode(text));
    Assert.assertEquals("", "e$elplepa", burrowsWheeler.encode(text).toString());
  }

  @Test
  public void decodeTest() {
    String text = "appellee";
    BurrowsWheeler.StringRotation rotation = burrowsWheeler.encode(text);
    String decoded = burrowsWheeler.decode(rotation);
    Assert.assertEquals("", text, decoded);
    System.out.println(decoded);
  }
}