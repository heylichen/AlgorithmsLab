package heylichen.leetcode;

import org.junit.Test;

public class BinomialDistributionTest {
    private BinomialDistribution bd = new BinomialDistribution();

    @Test
    public void name() {
//        System.out.println(combination(BigInteger.valueOf(35),BigInteger.valueOf(11)));
//        System.out.println(binomialDistribution(35, 11, 0.1));

        System.out.println(bd.binomialDistributionGreaterOrEqual(35, 11, 0.1));
        System.out.println(bd.binomialDistributionGreaterOrEqual(120, 51, 0.1));
    }

    @Test
    public void ex() {
        System.out.println(bd.binomialDistributionGreaterOrEqual(13,8,0.1));
        System.out.println(bd.binomialDistribution(13,7,0.1));
    }
}
