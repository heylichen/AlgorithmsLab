package heylichen.sort.pq;

/**
 * start index is 1
 * highest priority is index 1
 *
 * @param <T>
 */
public class PriorityQueue<T> {

    private final PriorityComparator<T> priorityComparator;
    private Object[] keys;
    private int size;

    public PriorityQueue(PriorityComparator<T> priorityComparator, int capacity) {
        this.priorityComparator = priorityComparator;
        this.keys = new Object[capacity + 1];
        this.size = 0;
    }

    public void insert(T key) {
        keys[size + 1] = key;
        size++;
        swim(size);
    }

    public T min() {
        if (size < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) keys[1];
    }

    public T delMin() {
        T key = min();
        exchange(1, size);
        size--;
        sink(1);
        return key;
    }

    private void swim(int i) {
        int current = i;
        int parent = i / 2;
        while (parent >= 1 && isPriorityHigherThan(current, parent)) {
            exchange(current, parent);
            current = parent;
            parent = current / 2;
        }
    }

    private void sink(int i) {
        int current = i;
        int childLeft = current * 2;
        int childRight = childLeft + 1;
        int highest = current;
        while (current <= size && childLeft <= size) {
            if (isPriorityHigherThan(childLeft, highest)) {
                highest = childLeft;
            }
            if (childRight <= size && isPriorityHigherThan(childRight, highest)) {
                highest = childRight;
            }
            if (highest == current) {
                break;
            } else {
                exchange(current, highest);
                current = highest;
                childLeft = current * 2;
                childRight = childLeft + 1;
            }
        }
    }

    @SuppressWarnings("cast")
    private boolean isPriorityHigherThan(int i, int j) {
        return priorityComparator.higherThan((T) keys[i], (T) keys[j]);
    }

    private void exchange(int i, int j) {
        Object jValue = keys[j];
        keys[j] = keys[i];
        keys[i] = jValue;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public int size() {
        return size;
    }
}