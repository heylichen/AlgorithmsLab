package algorithms.dynamicprog;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/7/23.
 */
public class BottomUpLCSTest {

  @Test
  public void lcsTest() {
    String x = "ACCGGTCGAGTGCGCGGAAGCCGGCCGAA";
    String y = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";
    LongestCommonSubSequence lcs = new BottomUpLCS();
    String lcsString = lcs.find(x, y);
    Assert.assertEquals("GTCGTCGGAAGCCGGCCGAA", lcsString);

  }
}