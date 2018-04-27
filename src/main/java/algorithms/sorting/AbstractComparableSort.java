package algorithms.sorting;

public abstract class AbstractComparableSort<T extends Comparable<T>> implements Sort<T> {

  private ComparableOperations operations = new ComparableOperations();

  protected boolean less(T a, T b) {
    return operations.less(a, b);
  }

  protected void exchange(T[] arr, int i, int j) {
    operations.exchange(arr, i, j);
  }


  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
