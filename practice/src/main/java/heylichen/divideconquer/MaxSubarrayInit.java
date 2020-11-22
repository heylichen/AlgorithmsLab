package heylichen.divideconquer;

final class MaxSubarrayInit {
    private MaxSubarrayInit() {
    }

    public static int[] initPriceChangeArr(int[] dailyPriceArr) {
        int[] changeArr = new int[dailyPriceArr.length];
        if (dailyPriceArr.length == 1) {
            return changeArr;
        }
        for (int i = 1; i < dailyPriceArr.length; i++) {
            changeArr[i] = dailyPriceArr[i] - dailyPriceArr[i - 1];
        }
        return changeArr;
    }
}
