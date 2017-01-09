package algorithms.ch1fundamentals.exer.ex1_2dataabs;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Chen Li on 2017/1/6.
 * <p>
 * 1.2.2 Write an Interval1D client that takes an int value N as command-line argument,
 * reads N intervals (each defined by a pair of double values) from standard input,
 * and prints all pairs that intersect.
 *
 * 遍历寻找相交区间，对每一个区间，寻找它对应相交的区间时，使用二分法查找
 */
public class FastIntersectedInterval1Ds implements IntersectedIntervals1DScanner {
  private static final double RANGE_MIN = 0;
  private static final double RANGE_MAX = 10000;

  public static void main(String[] args) {
    if (args.length == 0) {
      StdOut.println("give a number");
      return;
    }
    //generating
    int count = Integer.parseInt(args[0]);
    List<Interval1D> intervals = new ArrayList<>();
    double tmp;
    for (int i = 0; i < count; i++) {
      double left = StdRandom.uniform(RANGE_MIN, RANGE_MAX);
      double right = StdRandom.uniform(RANGE_MIN, RANGE_MAX);
      if (left > right) {
        tmp = left;
        left = right;
        right = tmp;
      }
      intervals.add(new Interval1D(left, right));
    }
    //computing
    IntersectedIntervals1DScanner scanner = new FastIntersectedInterval1Ds();
    long start = System.currentTimeMillis();
    List<Pair<Interval1D, List<Interval1D>>> intersectedList = scanner.findIntersectedIntervals(intervals);
    long end = System.currentTimeMillis();


    //report
    long ms = end - start;
    IntersectedIntervals1DScanner.reportIntersected(intersectedList, ms);
  }

  public List<Pair<Interval1D, List<Interval1D>>> findIntersectedIntervals(List<Interval1D> intervals) {
    //sort
    Collections.sort(intervals, (a, b) -> {
      if (a.min() < b.min()) {
        return -1;
      } else if (a.min() > b.min()) {
        return 1;
      } else {
        return 0;
      }
    });

    List<Pair<Interval1D, List<Interval1D>>> intersectedList = new ArrayList<>();
    int count = intervals.size();
    for (int i = 0; i < count; i++) {
      int demarcationPoint = findDemarcationPoint(intervals.get(i), intervals, i + 1, count - 1);
      if (demarcationPoint != -1) {
        intersectedList.add(Pair.of(intervals.get(i), intervals.subList(i + 1, demarcationPoint + 1)));
      }
    }
    return intersectedList;
  }


  private static int findDemarcationPoint(Interval1D target, List<Interval1D> intervals, int from, int to) {
    if (from > to) {
      return -1;
    }

    int middle = (from + to) / 2;
    Interval1D middleInt = intervals.get(middle);
    Interval1D middleNextInt = middle + 1 < intervals.size() ? intervals.get(middle + 1) : null;

    if (target.intersects(middleInt)) {
      if (middleNextInt == null) {
        return middle;
      } else if (target.intersects(middleNextInt)) {
        return findDemarcationPoint(target, intervals, middle + 1, to);
      } else {
        return middle;
      }
    } else {
      return findDemarcationPoint(target, intervals, from, middle - 1);
    }
  }
}
