package algorithms.ch1fundamentals.impl;

import algorithms.ch1fundamentals.Queue;

import java.util.Iterator;

/**
 * Created by lc on 2016/4/6.
 */
public class ResizingArrayQueue<T> implements Queue<T> {
    private T[] elements;
    private static final int DEFAULT_SIZE = 8;
    private int size = 0;

    public ResizingArrayQueue() {
        elements = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void enqueue(T item) {
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
    public T dequeue() {
        if (size == 0) {
            throw new IllegalStateException("stack size ==0");
        }
        T e = elements[0];
        elements[0] = null;
        //shrink
        for( int i =1; i< size;i++ ){
            elements[i-1] = elements[i];
        }
        size--;
        return e;
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
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int index = 0;

        public ArrayIterator() {
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
            return elements[index++];
        }
    }

    public static void main(String[] a){
        ResizingArrayQueue<String> arr = new ResizingArrayQueue<>();
        for(int i=0; i<10; i++){
            arr.enqueue("item"+i);
        }
        for(String item: arr){
             System.out.println(item);
        }

    }
}
