package algorithms.ch1fundamentals;

/**
 * Created by lc on 2016/4/6.
 */
public interface Stack<T> extends Iterable<T> {
    public void push(T item);
    public T pop();
    public boolean isEmpty();
    public int size();
}
