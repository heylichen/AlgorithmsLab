package algorithms.strings.compression.transform;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoveToFrontTest {

  private String alphabet;

  @Before
  public void setUp() throws Exception {
    this.alphabet = createAlphabet('a', 'z');
    this.alphabet += " ,.!";
    this.alphabet += createAlphabet('A', 'Z');
  }

  @Test
  public void moveToFrontTest() {
    MoveToFront mtf = new MoveToFront();
    String text = "what a wonderful world!";
    List<Integer> encoded = mtf.encode(text, alphabet);
    String decoded = mtf.decode(encoded, alphabet);
    Assert.assertEquals("", text, decoded);
  }

  private String createAlphabet(char begin, char end) {
    StringBuilder sb = new StringBuilder();
    char current = begin;
    while (current <= end) {
      sb.append(current);
      current++;
    }
    return sb.toString();
  }
}