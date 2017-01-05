package algorithms.ch1fundamentals;

/**
 * Created by lc on 2016/4/6.
 */
public interface Bag<T> extends Iterable<T> {
    public void add(T item);
    public boolean isEmpty();
    public int size();
}
