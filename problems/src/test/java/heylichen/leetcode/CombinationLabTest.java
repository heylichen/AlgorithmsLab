package heylichen.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class CombinationLabTest {

    @Test
    public void name() {
        CombinationCalculator lab = new CombinationCalculator();
        Assert.assertEquals(BigInteger.valueOf(417225900),lab.compute(35, 11));
        Assert.assertEquals(BigInteger.valueOf(1),lab.compute(12, 12));
        Assert.assertEquals(BigInteger.valueOf(12),lab.compute(12, 1));
    }
}