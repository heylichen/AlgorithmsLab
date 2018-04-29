package algorithms.sorting.compare;


import algorithms.sorting.Sort;

/**
 * Created by Chen Li on 2018/3/18.
 */
public interface TimerSortRunner<T extends Comparable<T>> {

  long timeTest(Sort<T> sort, T[] data, int times);
}
