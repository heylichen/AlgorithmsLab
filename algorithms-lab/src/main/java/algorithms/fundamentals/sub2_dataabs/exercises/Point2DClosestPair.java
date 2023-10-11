package algorithms.fundamentals.sub2_dataabs.exercises;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Li on 2017/1/6.
 * <p>
 * 1.2.1 Write a Point2D client that takes an integer value N from the command line,
 * generates N random points in the unit square, and computes the distance separating
 * the closest pair of points.
 */
public class Point2DClosestPair {

  public static void main(String[] args) {
    if (args.length == 0) {
      StdOut.println("give a number");
      return;
    }
    int count = Integer.parseInt(args[0]);
    List<Point2D> point2Ds = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      point2Ds.add(new Point2D(StdRandom.uniform(), StdRandom.uniform()));
    }

    long start = System.currentTimeMillis();
    Point2D from = null;
    Point2D to = null;
    double shortest = Double.MAX_VALUE;
    for (int i = 0; i < point2Ds.size(); i++) {
      for (int j = i + 1; j < point2Ds.size(); j++) {
        Point2D a = point2Ds.get(i);
        Point2D b = point2Ds.get(j);
        double distanceSquared = a.distanceSquaredTo(b);
        if (distanceSquared < shortest) {
          shortest = distanceSquared;
          from = a;
          to = b;
        }
      }
    }
    long end = System.currentTimeMillis();


    StdOut.printf("shortest pair is from:%s to %s, distance:%s, using time:%s ms",
        from, to, from.distanceTo(to), (end - start));


//    for (Point2D point2D : point2Ds) {
//      point2D.draw();
//    }
//    StdDraw.setPenColor(Color.red);
//    from.drawTo(to);
  }
}
