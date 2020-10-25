package heylichen.dynamicprogram;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.function.Function;

public class RodCut {
    private static Map<Integer, Integer> prices;

    static {
        prices = new HashMap<>();
        prices.put(1, 1);
        prices.put(2, 5);
        prices.put(3, 8);
        prices.put(4, 9);
        prices.put(5, 10);

        prices.put(6, 17);
        prices.put(7, 17);
        prices.put(8, 20);
        prices.put(9, 24);
        prices.put(10, 30);
    }

    //exponential !!!
    public Integer simpleRecursiveCut(int len) {
        if (len == 0) {
            return 0;
        }
        int maxR = -1;
        int tmpR = 0;
        for (int i = 1; i <= len; i++) {
            tmpR = prices.get(i) + simpleRecursiveCut(len - i);
            if (tmpR > maxR) {
                maxR = tmpR;
            }
        }
        return maxR;
    }


    public Integer recursiveCut(int len) {
        return recursiveCut(len, new HashMap<>());
    }

    private Integer recursiveCut(int len, Map<Integer, Integer> maxMap) {
        if (len == 0) {
            return 0;
        }
        int maxR = -1;
        int tmpR;
        for (int i = 1; i <= len; i++) {
            final int leftLen = len - i;
            Integer leftR = maxMap.computeIfAbsent(leftLen, k -> recursiveCut(leftLen, maxMap));
            tmpR = prices.get(i) + leftR;
            if (tmpR > maxR) {
                maxR = tmpR;
            }
        }
        return maxR;
    }

    public Integer nonRecursiveCut(int len) {
        Map<Integer, Integer> maxRevenueMap = new HashMap();
        maxRevenueMap.put(0, 0);
        int max;
        int tmp;
        for (int i = 1; i <= len; i++) {
            max = -1;
            for (int j = 1; j <= i; j++) {
                tmp = prices.get(j) + maxRevenueMap.get(i - j);
                if (tmp > max) {
                    max = tmp;
                }
            }
            maxRevenueMap.put(i, max);
        }
        return maxRevenueMap.get(len);
    }

    public RodCutSolution recursiveCutWithSolution(int len) {
        Map<Integer, List<Integer>> cutsMap = new HashMap<>();
        cutsMap.put(0, Collections.emptyList());
        RodCutSolution solution = new RodCutSolution(new HashMap<>(), cutsMap);
        recursiveCutWithSolution(len, new HashMap<>(), solution);
        return solution;
    }

    private Integer recursiveCutWithSolution(int len, Map<Integer, Integer> maxMap, RodCutSolution solution) {
        if (len == 0) {
            return 0;
        }
        int maxR = -1;
        int tmpR;
        int bestFirstCutLen = 0;
        for (int i = 1; i <= len; i++) {
            final int leftLen = len - i;
            Integer leftR = maxMap.computeIfAbsent(leftLen, k -> recursiveCutWithSolution(leftLen, maxMap, solution));
            tmpR = prices.get(i) + leftR;
            if (tmpR > maxR) {
                maxR = tmpR;
                bestFirstCutLen = i;
            }
        }

        if (!solution.getCutsMap().containsKey(len)) {
            computeBestCutsForLength(solution.getCutsMap(), len, bestFirstCutLen);
            solution.getMaxRevenues().put(len, maxR);
        }//else won't happen
        return maxR;
    }

    public RodCutSolution nonRecursiveCutWithSolution(int len) {
        Map<Integer, List<Integer>> cutsMap = new HashMap<>();
        cutsMap.put(0, Collections.emptyList());
        RodCutSolution solution = new RodCutSolution(new HashMap<>(), cutsMap);

        Map<Integer, Integer> maxRevenueMap = new HashMap();
        maxRevenueMap.put(0, 0);
        int max = 0;
        int tmp;
        int bestLeftCutLen = 0;
        for (int i = 1; i <= len; i++) {
            max = -1;
            for (int j = 1; j <= i; j++) {
                tmp = prices.get(j) + maxRevenueMap.get(i - j);
                if (tmp > max) {
                    max = tmp;
                    bestLeftCutLen = j;
                }
            }
            maxRevenueMap.put(i, max);
            solution.getMaxRevenues().put(i, max);
            computeBestCutsForLength(solution.getCutsMap(), i, bestLeftCutLen);
        }
        return solution;
    }

    private void computeBestCutsForLength(Map<Integer, List<Integer>> bestCutsByLengths, int length, int bestLeftCutLen) {
        List<Integer> partBestCuts = bestCutsByLengths.get(length - bestLeftCutLen);
        List<Integer> copy = new ArrayList<>(partBestCuts.size() + 1);
        copy.add(bestLeftCutLen);
        copy.addAll(partBestCuts);
        bestCutsByLengths.put(length, copy);
    }


    public RodCutLazySolution recursiveCutWithLazySolution(int len) {
        int arrSize = len + 1;
        RodCutLazySolution solution = new RodCutLazySolution(new int[arrSize], new int[arrSize]);
        recursiveCutWithLazySolution(len, solution);
        return solution;
    }

    private int recursiveCutWithLazySolution(int len, RodCutLazySolution solution) {
        if (len == 0) {
            return 0;
        }

        int maxR = 0;
        int bestFirstCutLen = 0;
        for (int i = 1; i <= len; i++) {
            final int leftLen = len - i;
            int bestLeftLenR = solution.getMaxRevenue(leftLen);
            if (bestLeftLenR == RodCutLazySolution.NOT_INITIALIZED) {
                bestLeftLenR = recursiveCutWithLazySolution(leftLen, solution);
            }
            int tmpR = prices.get(i) + bestLeftLenR;
            if (tmpR > maxR) {
                maxR = tmpR;
                bestFirstCutLen = i;
            }
        }
        solution.setMaxRevenue(len, maxR);
        solution.setBestFirstCut(len, bestFirstCutLen);
        return maxR;
    }

    public static void main(String[] args) {
        RodCut rodCut = new RodCut();
//        test(rodCut::simpleRecursiveCut);
//        test(rodCut::recursiveCut);
//        test(rodCut::nonRecursiveCut);

//        testWithSolution(rodCut::recursiveCutWithSolution);
//        testWithSolution(rodCut::nonRecursiveCutWithSolution);

        testWithLazySolution(rodCut::recursiveCutWithLazySolution);
    }

    private static void testWithSolution(Function<Integer, RodCutSolution> func) {
        int len = 9;
        RodCutSolution solution = func.apply(len);
        System.out.println("max revenue: " + solution.getMaxRevenues().get(len) + " cut solution in lengths: " + solution.getCutsMap().get(len));
    }

    private static void testWithLazySolution(Function<Integer, RodCutLazySolution> func) {
        int len = 9;
        RodCutLazySolution solution = func.apply(len);
        System.out.println("max revenue: " + solution.getMaxRevenue(len) + " cut solution in lengths: " + solution.getBestCuts(len));

        solution.printAll();
    }

    private static void test(Function<Integer, Integer> func) {
        long start = System.currentTimeMillis();
        System.out.println(func.apply(9));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class RodCutSolution {
        private Map<Integer, Integer> maxRevenues;
        private Map<Integer, List<Integer>> cutsMap;
    }

    @Getter
    @Setter
    public static class RodCutLazySolution {
        public static final int NOT_INITIALIZED = -1;
        private int[] maxRevenues;
        private int[] bestFirstCuts;

        public RodCutLazySolution(int[] maxRevenues, int[] bestFirstCuts) {
            this.maxRevenues = maxRevenues;
            this.bestFirstCuts = bestFirstCuts;
            maxRevenues[0] = 0;
            bestFirstCuts[0] = 0;
            for (int i = 1; i < maxRevenues.length; i++) {
                maxRevenues[i] = NOT_INITIALIZED;
            }

            for (int i = 1; i < bestFirstCuts.length; i++) {
                bestFirstCuts[i] = NOT_INITIALIZED;
            }
        }

        public int getMaxRevenue(int i) {
            return maxRevenues[i];
        }

        public void setMaxRevenue(int i, int r) {
            maxRevenues[i] = r;
        }

        public void setBestFirstCut(int len, int firstCutLen) {
            bestFirstCuts[len] = firstCutLen;
        }

        public List<Integer> getBestCuts(int len) {
            List<Integer> cuts = new ArrayList<>(len);
            int cut = 0;
            int leftCut = len;
            while (leftCut > 0) {
                cut = bestFirstCuts[leftCut];
                cuts.add(cut);
                leftCut = leftCut - cut;
            }
            return cuts;
        }

        private void printAll() {
            int length = bestFirstCuts.length;
            for (int i = 0; i < length; i++) {
                System.out.println(maxRevenues[i] + " " + bestFirstCuts[i]);
            }

        }
    }
}
