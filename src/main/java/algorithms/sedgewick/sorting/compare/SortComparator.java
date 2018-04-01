package algorithms.sedgewick.sorting.compare;

/**
 * Created by Chen Li on 2018/3/18.
 */
public interface SortComparator<T extends Comparable<T>, C extends SortCompareContext> {

  void compare(C context);

}

