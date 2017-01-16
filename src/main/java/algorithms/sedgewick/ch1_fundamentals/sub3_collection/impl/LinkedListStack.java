package algorithms.sedgewick.ch1_fundamentals.sub3_collection.impl;

import algorithms.sedgewick.ch1_fundamentals.sub3_collection.stack.IStack;

import java.util.Iterator;

/**
 * Created by lc on 2016/4/6.
 */
public class LinkedListStack<T> implements IStack<T> {
    private Node head = null;
    private int size = 0;

    @Override
    public void push(T item) {
        Node<T>  newNode = new Node<T>();
        newNode.setItem(item);
        newNode.setNext(head);
        head = newNode;
        size ++;
    }

    @Override
    public T pop() {
        if(head==null){
            throw new IllegalStateException();
        }
        Node<T>  ele = head;
        head = head.getNext();
        ele.setNext(null);
        size--;
        return ele.getItem();
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T>{
        private Node<T> node = head;
        public LinkedListIterator() {
        }

        @Override
        public boolean hasNext() {
            return node!=null;
        }

        @Override
        public T next() {
            T item =  node.getItem();
            node = node.getNext();
            return item;
        }
    }

    private static class Node<T> {
        private T item;
        private Node next;

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
