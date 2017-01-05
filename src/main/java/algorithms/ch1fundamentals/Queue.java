package algorithms.ch1fundamentals;

/**
 * Created by lc on 2016/4/6.
 */
public interface Queue<T> extends Iterable<T> {
    public void enqueue(T item);
    public T dequeue();
    public boolean isEmpty();
    public int size();
}
