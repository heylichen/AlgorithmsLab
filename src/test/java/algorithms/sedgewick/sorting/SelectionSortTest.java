package algorithms.sedgewick.sorting;

public class SelectionSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    return new SelectionSort();
  }
}
