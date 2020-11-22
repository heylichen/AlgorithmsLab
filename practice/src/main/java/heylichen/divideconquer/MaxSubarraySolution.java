package heylichen.divideconquer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaxSubarraySolution implements Comparable<MaxSubarraySolution> {
    private int begin;
    private int end;
    private int sum;

    public MaxSubarraySolution(int begin, int end, int sum) {
        this.begin = begin;
        this.end = end;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "begin=" + begin +
                ", end=" + end +
                ", sum=" + sum +
                '}';
    }

    public void addSum(int sum) {
        this.sum += sum;
    }

    @Override
    public int compareTo(MaxSubarraySolution o) {
        return sum - o.getSum();
    }

    public void copy(MaxSubarraySolution o) {
        begin = o.getBegin();
        end = o.getEnd();
        sum = o.getSum();
    }

    public MaxSubarraySolution decrementBegin() {
        begin--;
        return this;
    }
}
