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
        RodCutSolution solution = new RodCutSolution(0, cutsMap);
        Integer maxRevenue = recursiveCutWithSolution(len, new HashMap<>(), solution);
        solution.setMaxRevenue(maxRevenue);
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
        }//else won't happen
        return maxR;
    }

    public RodCutSolution nonRecursiveCutWithSolution(int len) {
        Map<Integer, List<Integer>> cutsMap = new HashMap<>();
        cutsMap.put(0, Collections.emptyList());
        RodCutSolution solution = new RodCutSolution(0, cutsMap);

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
            computeBestCutsForLength(solution.getCutsMap(), i, bestLeftCutLen);
        }
        solution.setMaxRevenue(max);
        return solution;
    }

    private void computeBestCutsForLength(Map<Integer, List<Integer>> bestCutsByLengths, int length, int bestLeftCutLen) {
        List<Integer> partBestCuts = bestCutsByLengths.get(length - bestLeftCutLen);
        List<Integer> copy = new ArrayList<>(partBestCuts.size() + 1);
        copy.add(bestLeftCutLen);
        copy.addAll(partBestCuts);
        bestCutsByLengths.put(length, copy);
    }


    public static void main(String[] args) {
        RodCut rodCut = new RodCut();
//        test(rodCut::simpleRecursiveCut);
//        test(rodCut::recursiveCut);
//        test(rodCut::nonRecursiveCut);

        testWithSolution(rodCut::recursiveCutWithSolution);
        testWithSolution(rodCut::nonRecursiveCutWithSolution);
    }

    private static void testWithSolution(Function<Integer, RodCutSolution> func) {
        RodCutSolution solution = func.apply(9);
        System.out.println("max revenue: " + solution.getMaxRevenue() + " cut solution in lengths: " + solution.getCutsMap().get(9));
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
        private Integer maxRevenue;
        private Map<Integer, List<Integer>> cutsMap;
    }
}
