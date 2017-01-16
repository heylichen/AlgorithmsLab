package algorithms.sedgewick.ch1_fundamentals.sub2_dataabs.exercises;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Li on 2017/1/6.
 * <p>
 * 1.2.2 Write an Interval1D client that takes an int value N as command-line argument,
 * reads N intervals (each defined by a pair of double values) from standard input,
 * and prints all pairs that intersect.
 *
 * 遍历寻找相交区间
 * 返回的数据结构有冗余
 */
public class SimpleIntersectedIntervals1DScanner {
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
    SimpleIntersectedIntervals1DScanner scanner = new SimpleIntersectedIntervals1DScanner();
    long start = System.currentTimeMillis();
    List<Pair<Interval1D, Interval1D>> intersectedPairs = scanner.findIntersectedIntervals(intervals);
    long end = System.currentTimeMillis();
    //report
    long ms = end - start;
    reportIntersected(intersectedPairs, ms);
  }

  public List<Pair<Interval1D, Interval1D>> findIntersectedIntervals(List<Interval1D> intervals) {
    List<Pair<Interval1D, Interval1D>> intersectedList = new ArrayList<>();
    for (int i = 0; i < intervals.size(); i++) {
      Interval1D a = intervals.get(i);
      List<Interval1D> list = new ArrayList<>();
      for (int j = i + 1; j < intervals.size(); j++) {
        Interval1D b = intervals.get(j);
        if (a.intersects(b)) {
          intersectedList.add(Pair.of(a, b));
        }
      }

    }
    return intersectedList;
  }

  public static void reportIntersected(List<Pair<Interval1D, Interval1D>> intersectedList, long ms) {
    if (!intersectedList.isEmpty()) {
      StdOut.printf("intersected interval pairs:%s, using %s ms.\n", intersectedList.size(), ms);
//      for (Pair<Interval1D, Interval1D> intersectedPair : intersectedPairs) {
//        StdOut.printf("%s, %s\n",
//            PointStringWrapper.wrap(intersectedPair.getKey(), 2),
//            PointStringWrapper.wrap(intersectedPair.getValue(), 2));
//      }
    } else {
      StdOut.printf("no intersected intervals found, using %s ms.", ms);
    }
  }
}
