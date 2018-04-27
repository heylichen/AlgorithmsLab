package algorithms.fundamentals.sub3_collection.exercises.creative;

import algorithms.fundamentals.sub3_collection.IBag;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by lc on 2016/4/6.
 * ALGORITHM 1.1 Pushdown (LIFO) stack (resizing array implementation)
 * This generic, iterable implementation of our IStack API is a model for collection ADTs that keep
 * items in an array. It resizes the array to keep the array size within a constant factor of the stack size.
 */
public class ArrayRandomBag<T> implements IBag<T>, Iterable<T> {
    private T[] elements;
    private static final int DEFAULT_SIZE = 8;
    private int size = 0;

    public ArrayRandomBag() {
        elements = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T item) {
        if (size == elements.length) {
            //resizing
            ensureCapacity();
        }
        elements[size++] = item;
    }

    private void ensureCapacity() {
        T[] newElements = (T[]) new Object[elements.length * 2 + 1];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
            elements[i] = null;
        }
        elements = newElements;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator(randomize());
    }

    private T[] randomize(){
        Random rand =new Random();
        for(int i= size-1; i>0; i--){
            int randIndex = rand.nextInt(i+1);
            if(randIndex!=i){
                T itemAtI = elements[i];
                elements[i] =  elements[randIndex];
                elements[randIndex] = itemAtI;
            }
        }

        return elements;
    }

    private class ArrayIterator implements Iterator<T> {
       private T[] eles ;
        private int index = 0;

        public ArrayIterator(T[] eles) {
            this.eles = eles;
        }

        @Override
        public boolean hasNext() {
            return index <size;
        }

        @Override
        public T next() {
            if (index >= size) {
                throw new ArrayIndexOutOfBoundsException("index:" + index);
            }
            return eles[index++];
        }
    }
}
