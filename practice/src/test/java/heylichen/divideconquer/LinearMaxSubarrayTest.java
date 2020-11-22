package heylichen.divideconquer;

import org.junit.Test;

public class LinearMaxSubarrayTest {

    @Test
    public void name() {
        LinearMaxSubarray maxSubarray = new LinearMaxSubarray(createDailyPrices());
        MaxSubarraySolution solution = maxSubarray.calculate();
        System.out.println(solution);
    }

    private int[] createDailyPrices() {
        return new int[]{100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};
    }
}