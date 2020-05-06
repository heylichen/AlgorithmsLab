package heylichen.sort.pq;

import java.util.HashMap;
import java.util.Map;

public class IndexedPriorityQueue<K> {
    private final PriorityComparator<K> priorityComparator;
    private Object[] keys;
    private Map<Integer, Integer> idToIndexMap;
    private Map<Integer, Integer> indexToIdMap;
    private int size;

    public IndexedPriorityQueue(PriorityComparator<K> priorityComparator, int capacity) {
        this.priorityComparator = priorityComparator;
        this.keys = new Object[capacity + 1];
        this.size = 0;
        this.idToIndexMap = new HashMap<>(capacity + 1);
        this.indexToIdMap = new HashMap<>(capacity + 1);
    }

    public void insert(int id, K key) {
        keys[size + 1] = key;
        size++;
        idToIndexMap.put(id, size);
        indexToIdMap.put(size, id);
        swim(size);
    }

    public void change(int id, K key) {
        int index = idToIndexMap.get(id);
        K oldKey = (K) keys[index];
        keys[index] = key;
        if (priorityComparator.higherThan(key, oldKey)) {
            swim(index);
        } else {
            sink(index);
        }
    }

    public boolean contains(int id) {
        return idToIndexMap.get(id) != null;
    }

    public void delete(int id) {
        //get info
        int index = idToIndexMap.get(id);
        K key = (K) keys[index];

        int lastIndex = size;
        K lastKey = (K) keys[size];

        exchange(index, lastIndex);
        //remove last
        size--;
        int lastId = indexToIdMap.get(lastIndex);
        idToIndexMap.put(lastId, null);
        indexToIdMap.put(lastIndex, null);
        //restore heap order
        if (priorityComparator.higherThan(lastKey, key)) {
            swim(index);
        } else {
            sink(index);
        }
    }

    public K min() {
        if (size < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (K) keys[1];
    }

    public int minId() {
        if (size < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return indexToIdMap.get(1);
    }

    public int delMin() {
        int minId = indexToIdMap.get(1);
        delete(minId);
        return minId;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public int size() {
        return size;
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
        return priorityComparator.higherThan((K) keys[i], (K) keys[j]);
    }

    /**
     * key logic
     * @param i
     * @param j
     */
    private void exchange(int i, int j) {
        Object jValue = keys[j];
        keys[j] = keys[i];
        keys[i] = jValue;

        int idI = indexToIdMap.get(i);
        int idJ = indexToIdMap.get(j);
        indexToIdMap.put(i, idJ);
        indexToIdMap.put(j, idI);

        idToIndexMap.put(idI, j);
        idToIndexMap.put(idJ, i);
    }
}
