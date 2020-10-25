package heylichen.dynamicprogram;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class MatrixChainMultiplication {

    public static void main(String[] args) {
        MatrixChainMultiplication mc = new MatrixChainMultiplication();
        int[] p = new int[]{30, 35, 15, 5, 10, 20, 25};
        Solution solution = mc.calculateRecursively(p);
        System.out.println(solution.getMinCost(2, 5) + " best : " + solution.bestSplits(2, 5));

        solution = mc.calculate(p);
        System.out.println(solution.getMinCost(2, 5) + " best : " + solution.bestSplits(2, 5));
    }


    public Solution calculate(int[] p) {
        int n = p.length - 1;
        Solution solution = initSolution(p);
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;

                int min = Integer.MAX_VALUE;
                int tmp = 0;
                int bestK = 0;
                for (int k = i; k < j; k++) {
                    int min1 = solution.getMinCost(i, k);
                    int min2 = solution.getMinCost(k + 1, j);
                    tmp = min1 + min2 + p[i - 1] * p[k] * p[j];
                    if (tmp < min) {
                        min = tmp;
                        bestK = k;
                    }
                }

                solution.setBestSplit(i, j, bestK);
                solution.setMinCost(i, j, min);
            }
        }
        return solution;
    }


    public Solution calculateRecursively(int[] p) {
        int n = p.length - 1;
        Solution solution = initSolution(p);
        calculateRecursively(p, 1, n, solution);
        return solution;
    }

    private Solution initSolution(int[] p) {
        int arrLen = p.length;

        int[][] m = new int[arrLen][arrLen];
        int[][] s = new int[arrLen][arrLen];
        //init
        for (int i = 0; i < arrLen; i++) {
            for (int i1 = 0; i1 < arrLen; i1++) {
                //single matrix, no calculation
                int value = i == i1 ? 0 : Solution.NONE;
                m[i][i1] = value;

                int k = i == i1 ? i : Solution.NONE;
                s[i][i1] = k;
            }
        }

        return new Solution(m, s);
    }

    private int calculateRecursively(int[] p, int i, int j, Solution solution) {
        if (i == j) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int tmp = 0;
        int bestK = 0;
        for (int k = i; k < j; k++) {
            int min1 = solution.getMinCost(i, k);
            if (min1 == Solution.NONE) {
                min1 = calculateRecursively(p, i, k, solution);
            }

            int min2 = solution.getMinCost(k + 1, j);
            if (min2 == Solution.NONE) {
                min2 = calculateRecursively(p, k + 1, j, solution);
            }
            tmp = min1 + min2 + p[i - 1] * p[k] * p[j];
            if (tmp < min) {
                min = tmp;
                bestK = k;
            }
        }
        solution.setMinCost(i, j, min);
        solution.setBestSplit(i, j, bestK);
        return solution.getMinCost(i, j);
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Solution {
        private int[][] m;
        private int[][] splits;
        public static final int NONE = Integer.MAX_VALUE;

        public int getMinCost(int i, int j) {
            return m[i][j];
        }

        public void setMinCost(int i, int j, int cost) {
            m[i][j] = cost;
        }

        public void setBestSplit(int i, int j, int k) {
            splits[i][j] = k;
        }

        public String bestSplits(int i, int j) {
            StringBuilder sb = new StringBuilder();
            bestSplits(i, j, sb);
            return sb.toString();
        }

        private void bestSplits(int i, int j, StringBuilder sb) {
            if (i == j) {
                sb.append(i);
                return;
            }
            int k = splits[i][j];
            sb.append("(");
            bestSplits(i, k, sb);
            sb.append(", ");
            bestSplits(k + 1, j, sb);
            sb.append(")");
        }
    }
}
