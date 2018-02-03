package algorithms.sedgewick.sorting;

public abstract class AbstractSort<T extends Comparable<T>> extends AbstractComparableOperator<T> implements Sort<T> {

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
