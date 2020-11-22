package heylichen.divideconquer;


public class MaxSubarray {

    private final int[] dailyPriceChangeArr;

    public MaxSubarray(int[] dailyPriceArr) {
        this.dailyPriceChangeArr = MaxSubarrayInit.initPriceChangeArr(dailyPriceArr);

    }

    public MaxSubarraySolution calculate() {
        MaxSubarraySolution maxSubarraySolution = findMaxSubarray(1, dailyPriceChangeArr.length - 1);
        return maxSubarraySolution.decrementBegin();
    }

    private MaxSubarraySolution findMaxSubarray(int low, int high) {
        if (low == high) {
            return new MaxSubarraySolution(low, high, dailyPriceChangeArr[low]);
        }
        int mid = (low + high) / 2;
        MaxSubarraySolution left = findMaxSubarray(low, mid);
        MaxSubarraySolution right = findMaxSubarray(mid + 1, high);
        MaxSubarraySolution crossing = findMaxCrossingSubarray(low, mid, high);

        MaxSubarraySolution maxSubarraySolution = left.compareTo(right) > 0 ? left : right;
        maxSubarraySolution = maxSubarraySolution.compareTo(crossing) > 0 ? maxSubarraySolution : crossing;
        return maxSubarraySolution;
    }

    private MaxSubarraySolution findMaxCrossingSubarray(int low, int mid, int high) {
        int maxLeftSum = Integer.MIN_VALUE;
        int maxLeftIndex = mid;
        for (int leftSum = 0, i = maxLeftIndex; i >= low; i--) {
            leftSum += dailyPriceChangeArr[i];
            if (leftSum > maxLeftSum) {
                maxLeftSum = leftSum;
                maxLeftIndex = i;
            }
        }

        int maxRightSum = Integer.MIN_VALUE;
        int maxRightIndex = mid + 1;
        for (int rightSum = 0, i = maxRightIndex; i <= high; i++) {
            rightSum += dailyPriceChangeArr[i];
            if (rightSum > maxRightSum) {
                maxRightSum = rightSum;
                maxRightIndex = i;
            }
        }

        return new MaxSubarraySolution(maxLeftIndex, maxRightIndex, maxLeftSum + maxRightSum);
    }
}
