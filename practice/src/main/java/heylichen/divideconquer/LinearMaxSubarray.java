package heylichen.divideconquer;


public class LinearMaxSubarray {

    private final int[] dailyPriceChangeArr;

    public LinearMaxSubarray(int[] dailyPriceArr) {
        this.dailyPriceChangeArr = MaxSubarrayInit.initPriceChangeArr(dailyPriceArr);
    }

    /**
     * Start at the left end of the array, and progress toward the right, keeping track of the maximum subarray seen
     * so far.
     * Knowing a maximum subarray of A[1 .. j] , extend the answer to find a maximum subarray ending at index j + 1
     * by using the following observation:
     * a maximum subarray of A[1 .. j+1] is either a maximum subarray of A[1 .. j]
     *  or a subarray A[i .. j+1], for some 1<= i <=j+1.
     * Determine a maximum subarray of the form [1 .. j+1] in constant time based on knowing a maximum subarray ending
     * at index j.
     * @return
     */
    public MaxSubarraySolution calculate() {
        //so we keep track of two solutions
        //1. max subarray of A[1 .. j]
        MaxSubarraySolution maxHeadTail = new MaxSubarraySolution(1, 1, dailyPriceChangeArr[1]);
        //2. max subarray ending at index j.
        MaxSubarraySolution maxTail = new MaxSubarraySolution(1, 1, dailyPriceChangeArr[1]);
        if (dailyPriceChangeArr.length == 1) {
            return maxHeadTail;
        }

        for (int j = 2; j < dailyPriceChangeArr.length; j++) {
            //then we try to find the max subarray ending at index j+1
            if (maxTail.getSum() >= 0) {
                maxTail.setEnd(j);
                maxTail.addSum(dailyPriceChangeArr[j]);
            } else {
                maxTail.setBegin(j);
                maxTail.setEnd(j);
                maxTail.setSum(dailyPriceChangeArr[j]);
            }
            //compare to maximum subarray of A[1 .. j], determine a maximum subarray of A[1 .. j+1]
            if (maxHeadTail.compareTo(maxTail) < 0) {
                maxHeadTail.copy(maxTail);
            }
        }
        return maxHeadTail.decrementBegin();
    }


}
