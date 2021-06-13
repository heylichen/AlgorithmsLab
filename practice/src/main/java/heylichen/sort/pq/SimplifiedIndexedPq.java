package heylichen.sort.pq;

import java.util.HashMap;
import java.util.Map;

public class SimplifiedIndexedPq<K> {
    private PriorityComparator<K> priorityComparator;
    private IdKey<K>[] idKeys;
    private Map<Integer, Integer> idToIndexMap;
    private final int capacity;
    private int size;

    public SimplifiedIndexedPq(PriorityComparator<K> priorityComparator, int capacity) {
        this.priorityComparator = priorityComparator;
        this.capacity = capacity;
        this.size = 0;
        int actualSize = capacity + 1;
        idKeys = new IdKey[actualSize];
        idToIndexMap = new HashMap<>();
    }

    private static class IdKey<K> {
        private int id;
        private K key;

        public IdKey(int id, K key) {
            this.id = id;
            this.key = key;
        }
    }

    private boolean isPriorityHigherThan(int i, int j) {
        K keyI = idKeys[i].key;
        K keyJ = idKeys[j].key;
        return priorityComparator.higherThan(keyI, keyJ);
    }

    private void exchange(int i, int j) {
        IdKey<K> tmpJ = idKeys[j];
        idKeys[j] = idKeys[i];
        idKeys[i] = tmpJ;

        idToIndexMap.put(idKeys[j].id, j);
        idToIndexMap.put(idKeys[i].id, i);
    }

    private void swim(int i) {
        int current = i;
        int parentIndex = i / 2;
        while (current > 1) {
            if (!isPriorityHigherThan(current, parentIndex)) {
                break;
            }
            exchange(current, parentIndex);
            current = parentIndex;
            parentIndex = parentIndex / 2;
        }
    }

    private void sink(int i) {
        int current = i;
        int child = current * 2;
        while (child <= size) {
            if (child + 1 <= size && isPriorityHigherThan(child + 1, child)) {
                child += 1;
            }
            if (!isPriorityHigherThan(child, current)) {
                break;
            }
            exchange(current, child);
            current = child;
            child = current * 2;
        }
    }

    public void insert(int id, K key) {
        IdKey<K> idKey = new IdKey<>(id, key);
        int index = ++size;
        idKeys[index] = idKey;
        idToIndexMap.put(id, index);
        swim(index);
    }

    public void delete(int id) {
        Integer index = idToIndexMap.get(Integer.valueOf(id));
        if (index == null) {
            throw new IllegalArgumentException("id not exit!");
        }
        int lastIndex = size;
        boolean newKeyHigher = isPriorityHigherThan(lastIndex, index);
        exchange(index, lastIndex);

        //remove current last index
        idKeys[lastIndex] = null;
        size--;
        idToIndexMap.put(id, null);
        //restore heap
        if (newKeyHigher) {
            swim(index);
        } else {
            sink(index);
        }
    }

    public int delMin() {
        int id = minId();
        delete(id);
        return id;
    }

    public void change(int id, K newKey) {
        int index = idToIndexMap.get(id);
        K oldKey = idKeys[index].key;
        idKeys[index].key = newKey;

        if (priorityComparator.higherThan(newKey, oldKey)) {
            swim(index);
        } else {
            sink(index);
        }
    }

    public boolean contains(int id) {
        return idToIndexMap.get(id) != null;
    }

    public K min() {
        if (size < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return idKeys[1].key;
    }

    public int minId() {
        if (size < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return idKeys[1].id;
    }

    public boolean isEmpty() {
        return size <= 0;
    }
}