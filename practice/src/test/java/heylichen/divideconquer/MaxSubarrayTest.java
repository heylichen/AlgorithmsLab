package heylichen.divideconquer;

import org.junit.Test;

public class MaxSubarrayTest {

    @Test
    public void name() {
        MaxSubarray maxSubarray = new MaxSubarray(createDailyPrices());
        MaxSubarraySolution maxSubarraySolution = maxSubarray.calculate();
        System.out.println(maxSubarraySolution);
    }

    private int[] createDailyPrices() {
        return new int[]{100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};
    }
}