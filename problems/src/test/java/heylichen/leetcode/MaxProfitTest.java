package heylichen.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class MaxProfitTest {
    private MaxProfit maxProfit = new MaxProfit();

    @Test
    public void name() {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        Assert.assertEquals(5, maxProfit.maxProfit(prices));

        prices = new int[]{7,6,4,3,1};
        Assert.assertEquals(0, maxProfit.maxProfit(prices));
    }
}