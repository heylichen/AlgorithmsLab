package algorithms.fundamentals.sub3_collection.exercises.creative;

import algorithms.fundamentals.sub3_collection.impl.Node;
import algorithms.fundamentals.sub3_collection.IStack;

import java.util.Iterator;

/**
 * Created by lc on 2016/4/7.
 */
public class Steque<T> implements IStack<T> {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    @Override
    public void push(T item) {
        Node<T> newNode = new Node<T>();
        newNode.setItem(item);
        newNode.setNext(head);
        head = newNode;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    @Override
    public T pop() {
        if (head == null) {
            throw new IllegalStateException();
        }
        Node<T> ele = head;
        head = head.getNext();
        ele.setNext(null);
        size--;
        if (head == null) {
            tail = null;
        }
        return ele.getItem();
    }

    public void enqueue(T item) {
        Node<T> newNode = new Node<T>();
        newNode.setItem(item);
        newNode.setNext(null);
        if (tail != null) {
            tail.setNext(newNode);
            tail = newNode;
        } else {
            head = tail = newNode;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> node = head;

        public LinkedListIterator() {
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            T item = node.getItem();
            node = node.getNext();
            return item;
        }
    }
}
