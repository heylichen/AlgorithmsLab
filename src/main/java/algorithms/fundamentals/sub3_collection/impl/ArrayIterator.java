package algorithms.fundamentals.sub3_collection.impl;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {
    private T[] elements;
    private int start;
    private int end;

    /**
     * @param elements: elements to be iterated
     * @param start,    inclusive
     * @param end,      exclusive
     */
    public ArrayIterator(T[] elements, int start, int end) {
        this.elements = elements;
        this.start = start;
        this.end = end;
    }


    @Override
    public boolean hasNext() {
        return start < end ;
    }

    @Override
    public T next() {
        if (start >= end) {
            throw new ArrayIndexOutOfBoundsException("index:" + start);
        }
        return elements[start++];
    }
}