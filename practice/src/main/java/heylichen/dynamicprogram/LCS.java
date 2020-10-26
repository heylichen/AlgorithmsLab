package heylichen.dynamicprogram;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class LCS {
    private String x;
    private String y;

    public static void main(String[] args) {
        String x = "ABCBDAB";
        String y = "BDCABA";

        LCS lcs = new LCS(x, y);
        int m = x.length() - 1;
        int n = y.length() - 1;
        Solution solution = lcs.calculate();
        System.out.println(solution.getLCSLen(m, n));
        System.out.println(solution.getLCS(m, n));
    }

    public Solution calculate() {
        int m = x.length();
        int n = y.length();
        int[][] maxLens = new int[m][n];
        for (int i1 = 0; i1 < m; i1++) {
            for (int i2 = 0; i2 < n; i2++) {
                maxLens[i1][i2] = Solution.NONE;
            }
        }

        int[][] backDirections = new int[m][n];
        for (int i1 = 0; i1 < m; i1++) {
            for (int i2 = 0; i2 < n; i2++) {
                backDirections[i1][i2] = Solution.NONE;
            }
        }

        Solution solution = new Solution(x, y, maxLens, backDirections);
        calculateRecursively(m - 1, n - 1, solution);
        return solution;
    }

    private int calculateRecursively(int i, int j, Solution solution) {
        if (i < 0 || j < 0) {
            return 0;
        }
        int len = solution.getLCSLen(i, j);
        if (len != Solution.NONE) {
            return len;
        }
        int maxLcsLen;
        if (x.charAt(i) == y.charAt(j)) {
            solution.setBack(i, j, Solution.BACK_LEFT_RIGHT);
            maxLcsLen = calculateRecursively(i - 1, j - 1, solution) + 1;
        } else {
            int len1 = calculateRecursively(i - 1, j, solution);
            int len2 = calculateRecursively(i, j - 1, solution);
            if (len1 < len2) {
                solution.setBack(i, j, Solution.BACK_RIGHT);
                maxLcsLen = len2;
            } else {
                solution.setBack(i, j, Solution.BACK_LEFT);
                maxLcsLen = len1;
            }
        }
        solution.setLCSLen(i, j, maxLcsLen);
        return maxLcsLen;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Solution {
        public static final int BACK_LEFT = 1;
        public static final int BACK_RIGHT = 2;
        public static final int BACK_LEFT_RIGHT = 3;

        public static int NONE = -1;
        private String x;
        private String y;
        private int[][] maxLens;
        private int[][] backDirections;

        public int getLCSLen(int i, int j) {
            return maxLens[i][j];
        }

        public void setLCSLen(int i, int j, int len) {
            maxLens[i][j] = len;
        }

        public int getBack(int i, int j) {
            return backDirections[i][j];
        }

        public void setBack(int i, int j, int len) {
            backDirections[i][j] = len;
        }

        public String getLCS(int i, int j) {
            StringBuilder sb = new StringBuilder();
            constructLCS(sb, i, j);
            return sb.toString();
        }

        private void constructLCS(StringBuilder sb, int i, int j) {
            if (i < 0 || j < 0) {
                return;
            }
            int direction = backDirections[i][j];
            switch (direction) {
                case BACK_RIGHT:
                    constructLCS(sb, i, j - 1);
                    break;
                case BACK_LEFT:
                    constructLCS(sb, i - 1, j);
                    break;
                case BACK_LEFT_RIGHT:
                    constructLCS(sb, i - 1, j - 1);
                    sb.append(x.charAt(i));
                    break;
                default:
                    return;
            }
        }
    }
}