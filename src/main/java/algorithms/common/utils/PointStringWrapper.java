package algorithms.common.utils;

import edu.princeton.cs.algs4.Interval1D;

/**
 * Created by Chen Li on 2017/1/7.
 */
public class PointStringWrapper {


  public static String wrap(Interval1D interval1D, int precision) {
    if (interval1D == null) {
      return null;
    }
    if (precision <= 0) {
      return "[" + interval1D.min() + "," + interval1D.max() + "]";
    }
    String format = "[%." + precision + "f, %." + precision + "f]";

    return String.format(format, interval1D.min(), interval1D.max());

  }
}
