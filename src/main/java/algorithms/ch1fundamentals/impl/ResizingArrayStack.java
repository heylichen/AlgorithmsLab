package algorithms.ch1fundamentals.impl;

import algorithms.ch1fundamentals.Stack;

import java.util.Iterator;

/**
 * Created by lc on 2016/4/6.
 * ALGORITHM 1.1 Pushdown (LIFO) stack (resizing array implementation)
 * This generic, iterable implementation of our Stack API is a model for collection ADTs that keep
 * items in an array. It resizes the array to keep the array size within a constant factor of the stack size.
 */
public class ResizingArrayStack<T> implements Stack<T> {
    private T[] elements;
    private static final int DEFAULT_SIZE = 8;
    private int size = 0;

    public ResizingArrayStack() {
        elements = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void push(T item) {
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
    public T pop() {
        if (size == 0) {
            throw new IllegalStateException("stack size ==0");
        }
        T e = elements[--size];
        elements[size] = null;
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
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {
        private int index = size;

        public ReverseArrayIterator() {
        }

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public T next() {
            index--;
            if (index < 0) {
                throw new ArrayIndexOutOfBoundsException("index:" + index);
            }
            return elements[index];
        }
    }
}
