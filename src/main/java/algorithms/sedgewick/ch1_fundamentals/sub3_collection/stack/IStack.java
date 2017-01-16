package algorithms.sedgewick.ch1_fundamentals.sub3_collection.stack;

/**
 * Created by lc on 2016/4/6.
 */
public interface IStack<T> extends Iterable<T> {
  public void push(T item);

  public T pop();

  public boolean isEmpty();

  public int size();
}
