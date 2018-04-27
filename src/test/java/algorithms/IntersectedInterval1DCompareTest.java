package algorithms;

import algorithms.fundamentals.sub2_dataabs.exercises.FastIntersectedInterval1Ds;
import algorithms.fundamentals.sub2_dataabs.exercises.IntersectedIntervals1DScanner;
import algorithms.fundamentals.sub2_dataabs.exercises.SimpleIntersectedIntervals1DScanner;
import algorithms.fundamentals.sub2_dataabs.exercises.SimpleIntersectedIntervals1DScanner2;
import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdRandom;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

import static algorithms.fundamentals.sub2_dataabs.exercises.IntersectedIntervals1DScanner.reportIntersected;

/**
 * Created by Chen Li on 2017/1/8.
 */
public class IntersectedInterval1DCompareTest {
  public static List<Interval1D> genIntervals(int count, double rangeMin, double rangeMax) {
    List<Interval1D> intervals = new ArrayList<>();
    double tmp;
    for (int i = 0; i < count; i++) {
      double left = StdRandom.uniform(rangeMin, rangeMax);
      double right = StdRandom.uniform(rangeMin, rangeMax);
      if (left > right) {
        tmp = left;
        left = right;
        right = tmp;
      }
      intervals.add(new Interval1D(left, right));
    }
    return intervals;
  }


  public static void main(String[] args) {
    List<Interval1D> intervals = genIntervals(10000, 0, 10000);
    long start = 0;
    long end = 0;

    /**
     * 10000个区间运行比较。
     * a:   intersected interval pairs:33540433, using 17787 ms.
     b:   intersected interval pairs:33540433, using 2689 ms.
     c:   intersected interval pairs:33540433, using 64 ms.
     * 比较结果：
     *  a,b,c 三种算法返回的结果一致
     *  b与a 比较，仅仅是返回的数据结构不同，通过去除数据结构中的冗余，不仅减少了内存空间的占用，也将消耗时间减少至6或7倍。
     *  c与b 比较，改变查找算法，将消耗的时间减少100倍数量级。
     */
    //a. 简单遍历，返回的数据结构有冗余
    start = System.currentTimeMillis();
    SimpleIntersectedIntervals1DScanner simpleScanner0 = new SimpleIntersectedIntervals1DScanner();
    List<Pair<Interval1D, Interval1D>> intersectedList2 = simpleScanner0.findIntersectedIntervals(intervals);
    end = System.currentTimeMillis();
    SimpleIntersectedIntervals1DScanner.reportIntersected(intersectedList2, (end - start));

    //b. 简单遍历，返回的数据结构无冗余
    start = System.currentTimeMillis();
    IntersectedIntervals1DScanner simpleScanner = new SimpleIntersectedIntervals1DScanner2();
    List<Pair<Interval1D, List<Interval1D>>> intersectedList = simpleScanner.findIntersectedIntervals(intervals);
    end = System.currentTimeMillis();
    reportIntersected(intersectedList, (end - start));

    //c.遍历寻找相交区间，对每一个区间，寻找它对应相交的区间时，使用二分法查找
    start = System.currentTimeMillis();
    IntersectedIntervals1DScanner fastScanner = new FastIntersectedInterval1Ds();
    intersectedList = fastScanner.findIntersectedIntervals(intervals);
    end = System.currentTimeMillis();
    reportIntersected(intersectedList, (end - start));
  }


}
