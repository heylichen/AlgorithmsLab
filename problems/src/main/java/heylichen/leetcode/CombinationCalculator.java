package heylichen.leetcode;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class CombinationCalculator {

    private Map<String, BigInteger> data;

    public BigInteger compute(int n, int m) {
        if (n < m) {
            throw new IllegalArgumentException(n + " must be >=" + m);
        }
        data = new HashMap<>();
        return doCompute(n, m);
    }

    private BigInteger doCompute(int n, int m) {
        if (n == m) {
            return BigInteger.ONE;
        }
        if (m == 1 || m == n - 1) {
            return BigInteger.valueOf(n);
        }
        String key = n + "_" + m;
        return data.computeIfAbsent(key, k -> doCompute(n - 1, m - 1).add(doCompute(n - 1, m)));
    }
}
