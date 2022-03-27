package heylichen.leetcode;

public class BinomialDistribution {
    private CombinationCalculator combinationCalculator = new CombinationCalculator();

    public double binomialDistributionGreaterOrEqual(int n, int k, double p) {
        double sum = 0;
        for (int i = k; i <= n; i++) {
            sum += binomialDistribution(n, i, p);
        }
        return sum;
    }

    public double binomialDistribution(int n, int k, double p) {
        return combinationCalculator.compute(n, k).doubleValue() * Math.pow(p, k) * Math.pow(1 - p, n - k);
    }
}
