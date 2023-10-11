package algorithms.sorting.lab;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @Author: lichen
 * @Date: 2019/3/4 20:21
 * @Version 1.0
 */
@Getter
@Setter
@Slf4j
public class MaxPq<T extends Comparable<T>> {
    private Object[] array;
    private int size;

    public MaxPq(int size) {
        this.size = 1;
        array = new Object[size + 1];
    }

    public T delMax() {
        T max = (T) array[1];
        exchange(1, size - 1);
        this.size--;
        sink(1);
        return max;
    }

    public boolean isEmpty() {
        return size <= 1;
    }

    public void insert(T k) {
        resize();
        array[size++] = k;
        swim(size - 1);
    }

    private void resize() {
        int needSize = array.length / 2;
        if (size < needSize) {
            return;
        }
        Object[] expandedArr = new Object[array.length * 2];
        System.arraycopy(array, 0, expandedArr, 0, this.size);
        log.info("resize from {} to {}", array.length, expandedArr.length);
        this.array = expandedArr;
    }

    private boolean less(int i, int j) {
        return ((T) array[i]).compareTo(((T) array[j])) < 0;
    }

    private void exchange(int i, int j) {
        Object tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private void swim(int i) {
        int currentIndex = i;
        int parent = parentOf(i);
        while (parent > 0 && less(parent, currentIndex)) {
            exchange(parent, currentIndex);
            currentIndex = parent;
            parent = parentOf(currentIndex);
        }
    }

    private void sink(int i) {
        int currentIndex = i;
        int child1 = firstChildOf(i);
        int child2 = child1 + 1;
        int lastIndex = size - 1;

        while (child1 <= lastIndex) {
            int childIndex = child1;
            if (child2 <= lastIndex && less(child1, child2)) {
                childIndex = child2;
            }
            if (less(childIndex, currentIndex)) {
                break;
            }
            exchange(currentIndex, childIndex);
            currentIndex = childIndex;
            child1 = firstChildOf(currentIndex);
            child2 = child1 + 1;
        }
    }

    private int parentOf(int index) {
        return index / 2;
    }

    private int firstChildOf(int index) {
        return index * 2;
    }

    public static void main(String[] args) {
//        Integer[] ints = new Integer[]{0, 3, 2, 1, 4};
//        MaxPq<Integer> maxPq = new MaxPq<>();
//        maxPq.setArray(ints);
//        maxPq.swim(4);
//        System.out.println();
//
//        maxPq.setArray(new Integer[]{0, 1, 4, 3, 2});
//        maxPq.sink(1);
//        maxPq.setSize(5);
//        System.out.println();

        MaxPq<Integer> maxPq = new MaxPq<>(1);
        Random random = new Random();
        int size = 20;
        for (int i = 1; i <= size; i++) {
            int k = random.nextInt(10000);
            maxPq.insert(k);
        }

        while (!maxPq.isEmpty()) {
            System.out.print(maxPq.delMax() + ", ");
        }
        System.out.println("");

    }
}
