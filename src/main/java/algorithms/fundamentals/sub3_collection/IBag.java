package algorithms.fundamentals.sub3_collection;

/**
 * Created by lc on 2016/4/6.
 */
public interface IBag<T> extends Iterable<T> {
  public void add(T item);

  public boolean isEmpty();

  public int size();
}
