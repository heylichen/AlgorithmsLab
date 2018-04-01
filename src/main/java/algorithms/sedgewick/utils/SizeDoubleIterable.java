package algorithms.sedgewick.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Li on 2018/4/1.
 */
public class SizeDoubleIterable {

  public List<Integer> toSizes(int start, int max) {
    List<Integer> result = new ArrayList<>();
    for (int i = start; i <= max; ) {
      result.add(i);
      i = i * 2;
    }
    return result;
  }
}
