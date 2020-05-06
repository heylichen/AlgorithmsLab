package heylichen.sort.pq;


public interface PriorityComparator<T> {
    /**
     * compare the priority of a to b
     * @param a
     * @param b
     * @return true if priority of a is higher than b,
     *  false if priority of a is less than or equal to b
     */
    boolean higherThan(T a, T b);
}