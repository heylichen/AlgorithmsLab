package heylichen.leetcode;

public class MaxProfit {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 1) {
            return 0;
        }
        int[] profits = new int[prices.length];
        for (int i = 0; i < prices.length - 1; i++) {
            profits[i] = prices[i + 1] - prices[i];
        }

        int max = maxProfit(profits, 0, prices.length - 1);
        return max <= 0 ? 0 : max;
    }

    private int maxProfit(int[] profits, int from, int to) {
        if (from == to) {
            return profits[from];
        }
        int middle = (from + to) / 2;
        int maxLeft = maxProfit(profits, from, middle);
        int maxRight = maxProfit(profits, middle + 1, to);
        int maxCross = maxCrossingProfit(profits, from, middle, to);
        int max = Math.max(maxLeft, maxCross);
        return Math.max(max, maxRight);
    }

    private int maxCrossingProfit(int[] profits, int from, int middle, int to) {
        int maxLeft = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int i = middle; i >= from; i--) {
            leftSum += profits[i];
            if (leftSum > maxLeft) {
                maxLeft = leftSum;
            }
        }

        int maxRight = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = middle + 1; i <= to; i++) {
            rightSum += profits[i];
            if (rightSum > maxRight) {
                maxRight = rightSum;
            }
        }
        return maxLeft + maxRight;
    }
}
