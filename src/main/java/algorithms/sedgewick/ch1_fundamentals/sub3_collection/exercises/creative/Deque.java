package algorithms.sedgewick.ch1_fundamentals.sub3_collection.exercises.creative;

import algorithms.sedgewick.ch1_fundamentals.sub3_collection.impl.DoubleNode;

/**
 * Created by lc on 2016/4/6.
 */
public class Deque<T> {
    private DoubleNode<T> left = null;
    private DoubleNode<T> right = null;
    private int size = 0;

    public void pushLeft(T item) {
        DoubleNode<T> newNode = new DoubleNode<>(item, null, null);
        size++;
        if (isEmpty()) {
            left = right = newNode;
        } else {
            newNode.insertBefore(left);
            left = newNode;
        }
    }

    public void pushRight(T item) {
        DoubleNode<T> newNode = new DoubleNode<>(item, null, null);
        size++;
        if (isEmpty()) {
            left = right = newNode;
        } else {
            newNode.appendTo(right);
            right = newNode;
        }
    }

    public T popLeft() {
        if (isEmpty()) {
            throw new IllegalStateException("can not pop when empty!");
        }
        DoubleNode<T> oldNode = left;
        left = left.getNext();
        if (left == null) {
            right = null;
        } else {
            nullNodeRefFromOneSide(oldNode);
        }
        T item = oldNode.getItem();
        return item;
    }

    public T popRight() {
        if (isEmpty()) {
            throw new IllegalStateException("can not pop when empty!");
        }
        DoubleNode<T> oldNode = right;
        right = right.getPrevious();
        if (right == null) {
            left = null;
        } else {
            nullNodeRefFromOneSide(oldNode);
        }
        T item = oldNode.getItem();
        return item;
    }

    private void nullNodeRefFromOneSide(DoubleNode<T> node) {
        if (node != null) {
            if (node.getPrevious() != null && node.getNext() != null) {
                throw new UnsupportedOperationException("this method should be used on node on eithe left or right side of the queue, but not in the middle!");
            }

            if (node.getPrevious() != null) {
                node.getPrevious().setNext(null);
            }
            if (node.getNext() != null) {
                node.getNext().setPrevious(null);
            }
            node.setPrevious(null);
            node.setNext(null);
        }
    }


    public boolean isEmpty() {
        return left == null;
    }


    public int size() {
        return size;
    }


}
