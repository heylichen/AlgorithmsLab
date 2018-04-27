package algorithms.fundamentals.sub2_dataabs.exercises;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdOut;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Created by Chen Li on 2017/1/8.
 */
public interface IntersectedIntervals1DScanner {
  List<Pair<Interval1D, List<Interval1D>>> findIntersectedIntervals(List<Interval1D> intervals);

  static void reportIntersected(List<Pair<Interval1D, List<Interval1D>>> intersectedList, long ms) {
    if (!intersectedList.isEmpty()) {
      int totalCount = 0;
      for (Pair<Interval1D, List<Interval1D>> interval1DListPair : intersectedList) {
        totalCount += interval1DListPair.getValue().size();
      }
      StdOut.printf("intersected interval pairs:%s, using %s ms.\n", totalCount, ms);
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
